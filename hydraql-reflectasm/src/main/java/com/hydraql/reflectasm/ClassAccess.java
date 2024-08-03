package com.hydraql.reflectasm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.reflect.Modifier.isStatic;
import static org.objectweb.asm.Opcodes.ACONST_NULL;

/**
 * @author leojie@apache.org 2024/8/3 17:32
 */
public class ClassAccess {
    public final static Object[] NO_ARGUMENTS = new Object[0];
    public final static Object STATIC_INSTANCE = null;
    public final ClassAccessor classAccessor;
    private final ClassInfo info;

    protected ClassAccess(ClassInfo info, ClassAccessor classAccessor) {
        this.info = info;
        this.classAccessor = classAccessor;
    }


    public static ClassAccess get(Class<?> type) {
        List<Method> methods = new ArrayList<>();
        ArrayList<Constructor<?>> constructors = new ArrayList<>();
        ArrayList<Field> fields = new ArrayList<>();
        collectMembers(type, methods, fields, constructors);
        int n = methods.size();
        ClassInfo classInfo = new ClassInfo();
        classInfo.methods = dump(methods);
        classInfo.fields = dump(fields);
        classInfo.constructors = dump(constructors);
        classInfo.methodModifiers = new int[n];
        classInfo.parameterTypes = new Class[n][];
        classInfo.returnTypes = new Class[n];
        classInfo.methodNames = new String[n];
        for (int i = 0; i < n; i++) {
            Method m = methods.get(i);
            classInfo.methodModifiers[i] = m.getModifiers();
            classInfo.parameterTypes[i] = m.getParameterTypes();
            classInfo.returnTypes[i] = m.getReturnType();
            classInfo.methodNames[i] = m.getName();
        }
        n = constructors.size();
        classInfo.constructorModifiers = new int[n];
        classInfo.constructorParameterTypes = new Class[n][];
        for (int i = 0; i < n; i++) {
            Constructor<?> c = constructors.get(i);
            classInfo.constructorModifiers[i] = c.getModifiers();
            classInfo.constructorParameterTypes[i] = c.getParameterTypes();
        }
        n = fields.size();
        classInfo.fieldModifiers = new int[n];
        classInfo.fieldNames = new String[n];
        classInfo.fieldTypes = new Class[n];
        for (int i = 0; i < n; i++) {
            Field f = fields.get(i);
            classInfo.fieldNames[i] = f.getName();
            classInfo.fieldTypes[i] = f.getType();
            classInfo.fieldModifiers[i] = f.getModifiers();
        }
        classInfo.isNonStaticMemberClass = type.getEnclosingClass() != null
                && type.isMemberClass()
                && !isStatic(type.getModifiers());


        String className = type.getName();
        final String accessClassName = "ReflectASM.ClassAccess." + className;
        //String accessClassName = "reflectasm." + className + "__ClassAccess__";
        //if (accessClassName.startsWith("java.")) accessClassName = "reflectasm." + accessClassName;
        Class<?> accessClass;
        final AccessClassLoader loader = AccessClassLoader.get(type);
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (loader) {
            try {
                accessClass = loader.loadClass(accessClassName);
            } catch (ClassNotFoundException ignored) {
                String accessClassNameInternal = accessClassName.replace('.', '/');
                String classNameInternal = className.replace('.', '/');
                final byte[] bytes = byteCode(classInfo, methods, fields, accessClassNameInternal, classNameInternal);
                try {
                    accessClass = UnsafeHolder.theUnsafe.defineClass(accessClassName, bytes, 0, bytes.length, loader, type.getProtectionDomain());
                } catch (Throwable ignored1) {
                    accessClass = loader.defineClass(accessClassName, bytes);
                }
            }
        }
        try {
            ClassAccessor access = (ClassAccessor) accessClass.newInstance();
//            System.out.println(access.toString());
            return new ClassAccess(classInfo, access);
        } catch (Exception ex) {
            throw new RuntimeException("Error constructing method access class: " + accessClassName, ex);
        }
    }

    private static <M extends Member> Map<M, Integer> dump(List<M> members) {
        Map<M, Integer> map = new HashMap<>();
        int i = -1;
        for (M member : members) {
            map.put(member, ++i);
        }
        return Collections.unmodifiableMap(map);
    }

    private static void collectMembers(Class<?> type, List<Method> methods, List<Field> fields, List<Constructor<?>> constructors) {
        if (type.isInterface()) {
            recursiveAddInterfaceMethodsToList(type, methods);
            return;
        }
        boolean search = true;
        for (Constructor<?> constructor : type.getDeclaredConstructors()) {
            //if (isPrivate(constructor.getModifiers())) continue;
            int length = constructor.getParameterTypes().length;
            if (search) {
                switch (length) {
                    case 0:
                        constructors.add(0, constructor);
                        search = false;
                        break;
                    case 1:
                        constructors.add(0, constructor);
                        break;
                    default:
                        constructors.add(constructor);
                        break;
                }
            }
        }
        Class<?> nextClass = type;
        while (nextClass != Object.class) {
            addNonPrivate(fields, nextClass.getDeclaredFields());
            addNonPrivate(methods, nextClass.getDeclaredMethods());
            nextClass = nextClass.getSuperclass();
        }
    }

    private static void recursiveAddInterfaceMethodsToList(Class<?> interfaceType, List<Method> methods) {
        addNonPrivate(methods, interfaceType.getDeclaredMethods());
        for (Class<?> nextInterface : interfaceType.getInterfaces()) {
            recursiveAddInterfaceMethodsToList(nextInterface, methods);
        }
    }

    private static <E extends Member> void addNonPrivate(List<E> list, E[] arr) {
        Collections.addAll(list, arr);
    }

    private static byte[] byteCode(ClassInfo info, List<Method> methods, List<Field> fields,
                                   String accessClassNameInternal, String classNameInternal) {

        //final String baseName = "java/lang/Object";
        final String baseName = "sun/reflect/MagicAccessorImpl";

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, accessClassNameInternal, null, baseName,
                new String[]{ClassAccessor.class.getName().replace('.', '/')});

        MethodVisitor mv;
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, baseName, "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();


        //***********************************************************************************************
        // constructor access
        insertNewInstance(cw, classNameInternal, info);
        insertNewRawInstance(cw, classNameInternal);


        //***********************************************************************************************
        // method access
        insertInvoke(cw, classNameInternal, methods);

        //***********************************************************************************************
        // field access
        insertGetObject(cw, classNameInternal, fields);
        insertSetObject(cw, classNameInternal, fields);
        insertGetPrimitive(cw, classNameInternal, fields, Type.BOOLEAN_TYPE, "getBoolean", Opcodes.IRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.BOOLEAN_TYPE, "setBoolean", Opcodes.ILOAD);
        insertGetPrimitive(cw, classNameInternal, fields, Type.BYTE_TYPE, "getByte", Opcodes.IRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.BYTE_TYPE, "setByte", Opcodes.ILOAD);
        insertGetPrimitive(cw, classNameInternal, fields, Type.SHORT_TYPE, "getShort", Opcodes.IRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.SHORT_TYPE, "setShort", Opcodes.ILOAD);
        insertGetPrimitive(cw, classNameInternal, fields, Type.INT_TYPE, "getInt", Opcodes.IRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.INT_TYPE, "setInt", Opcodes.ILOAD);
        insertGetPrimitive(cw, classNameInternal, fields, Type.LONG_TYPE, "getLong", Opcodes.LRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.LONG_TYPE, "setLong", Opcodes.LLOAD);
        insertGetPrimitive(cw, classNameInternal, fields, Type.DOUBLE_TYPE, "getDouble", Opcodes.DRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.DOUBLE_TYPE, "setDouble", Opcodes.DLOAD);
        insertGetPrimitive(cw, classNameInternal, fields, Type.FLOAT_TYPE, "getFloat", Opcodes.FRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.FLOAT_TYPE, "setFloat", Opcodes.FLOAD);
        insertGetPrimitive(cw, classNameInternal, fields, Type.CHAR_TYPE, "getChar", Opcodes.IRETURN);
        insertSetPrimitive(cw, classNameInternal, fields, Type.CHAR_TYPE, "setChar", Opcodes.ILOAD);

        cw.visitEnd();
        return cw.toByteArray();

    }

    private static void insertNewRawInstance(ClassWriter cw, String classNameInternal) {
        MethodVisitor mv;
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "newInstance", "()Ljava/lang/Object;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(Opcodes.NEW, classNameInternal);
            mv.visitInsn(Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, classNameInternal, "<init>", "()V", false);
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "newObjectInstance", "()Ljava/lang/Object;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(Opcodes.NEW, classNameInternal);
            //mv.visitInsn(DUP);
            //classNameInternal = "java/lang/Object";
            //mv.visitMethodInsn(INVOKESPECIAL, classNameInternal, "<init>", "()V");
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
    }

    private static void insertNewInstance(ClassWriter cw, String classNameInternal, ClassInfo info) {
        MethodVisitor mv;
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_VARARGS, "newInstance",
                "(I[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        mv.visitCode();

        int n = info.constructorModifiers.length;

        if (n != 0) {
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            Label[] labels = new Label[n];
            for (int i = 0; i < n; i++)
                labels[i] = new Label();
            Label defaultLabel = new Label();
            mv.visitTableSwitchInsn(0, labels.length - 1, defaultLabel, labels);

            StringBuilder buffer = new StringBuilder(128);
            for (int i = 0; i < n; i++) {
                mv.visitLabel(labels[i]);
                if (i == 0)
                    mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{classNameInternal}, 0, null);
                else
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

                mv.visitTypeInsn(Opcodes.NEW, classNameInternal);
                mv.visitInsn(Opcodes.DUP);

                buffer.setLength(0);
                buffer.append('(');

                Class<?>[] paramTypes = info.constructorParameterTypes[i];
                for (int paramIndex = 0; paramIndex < paramTypes.length; paramIndex++) {
                    mv.visitVarInsn(Opcodes.ALOAD, 2);
                    mv.visitIntInsn(Opcodes.BIPUSH, paramIndex);
                    mv.visitInsn(Opcodes.AALOAD);
                    Type paramType = Type.getType(paramTypes[paramIndex]);
                    unbox(mv, paramType);
                    buffer.append(paramType.getDescriptor());
                }
                buffer.append(")V");
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, classNameInternal, "<init>", buffer.toString(), false);
                mv.visitInsn(Opcodes.ARETURN);
            }
            mv.visitLabel(defaultLabel);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
        mv = insertThrowExceptionForConstructorNotFound(mv);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private static void insertInvoke(ClassWriter cw, String classNameInternal, List<Method> methods) {
        MethodVisitor mv;
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_VARARGS, "invoke",
                "(Ljava/lang/Object;I[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        mv.visitCode();

        int n = methods.size();

        if (n != 0) {
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            Label[] labels = new Label[n];
            for (int i = 0; i < n; i++)
                labels[i] = new Label();
            Label defaultLabel = new Label();
            mv.visitTableSwitchInsn(0, labels.length - 1, defaultLabel, labels);

            StringBuilder buffer = new StringBuilder(128);
            for (int i = 0; i < n; i++) {
                Method method = methods.get(i);
                boolean isInterface = method.getDeclaringClass().isInterface();
                boolean isStatic = isStatic(method.getModifiers());

                mv.visitLabel(labels[i]);
                if (i == 0)
                    mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{classNameInternal}, 0, null);
                else
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

                if (!isStatic) {
                    mv.visitVarInsn(Opcodes.ALOAD, 1);
                    mv.visitTypeInsn(Opcodes.CHECKCAST, classNameInternal);
                }

                buffer.setLength(0);
                buffer.append('(');

                String methodName = method.getName();
                Class<?>[] paramTypes = method.getParameterTypes();
                Class<?> returnType = method.getReturnType();
                for (int paramIndex = 0; paramIndex < paramTypes.length; paramIndex++) {
                    mv.visitVarInsn(Opcodes.ALOAD, 3);
                    mv.visitIntInsn(Opcodes.BIPUSH, paramIndex);
                    mv.visitInsn(Opcodes.AALOAD);
                    Type paramType = Type.getType(paramTypes[paramIndex]);
                    unbox(mv, paramType);
                    buffer.append(paramType.getDescriptor());
                }

                buffer.append(')');
                buffer.append(Type.getDescriptor(returnType));
                final int inv = isInterface ? Opcodes.INVOKEINTERFACE : (isStatic ? Opcodes.INVOKESTATIC : Opcodes.INVOKEVIRTUAL);
                mv.visitMethodInsn(inv, classNameInternal, methodName, buffer.toString(), isInterface);

                final Type retType = Type.getType(returnType);
                box(mv, retType);
                mv.visitInsn(Opcodes.ARETURN);
            }

            mv.visitLabel(defaultLabel);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
        mv = insertThrowExceptionForMethodNotFound(mv);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private static void insertSetObject(ClassWriter cw, String classNameInternal, List<Field> fields) {
        int maxStack = 6;
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "set", "(Ljava/lang/Object;ILjava/lang/Object;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ILOAD, 2);

        if (!fields.isEmpty()) {
            maxStack--;
            Label[] labels = new Label[fields.size()];
            for (int i = 0, n = labels.length; i < n; i++)
                labels[i] = new Label();
            Label defaultLabel = new Label();
            mv.visitTableSwitchInsn(0, labels.length - 1, defaultLabel, labels);

            for (int i = 0, n = labels.length; i < n; i++) {
                Field field = fields.get(i);
                Type fieldType = Type.getType(field.getType());
                boolean st = isStatic(field.getModifiers());

                mv.visitLabel(labels[i]);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                if (!st) {
                    mv.visitVarInsn(Opcodes.ALOAD, 1);
                    mv.visitTypeInsn(Opcodes.CHECKCAST, classNameInternal);
                }
                mv.visitVarInsn(Opcodes.ALOAD, 3);

                unbox(mv, fieldType);
                // field.getDeclaringClass().getName().replace('.', '/')
                mv.visitFieldInsn(st ? Opcodes.PUTSTATIC : Opcodes.PUTFIELD, classNameInternal, field.getName(), fieldType.getDescriptor());
                mv.visitInsn(Opcodes.RETURN);
            }

            mv.visitLabel(defaultLabel);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
        mv = insertThrowExceptionForFieldNotFound(mv);
        mv.visitMaxs(maxStack, 4);
        mv.visitEnd();
    }

    private static void insertGetObject(ClassWriter cw, String classNameInternal, List<Field> fields) {
        int maxStack = 6;
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "get", "(Ljava/lang/Object;I)Ljava/lang/Object;", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ILOAD, 2);

        if (!fields.isEmpty()) {
            maxStack--;
            Label[] labels = new Label[fields.size()];
            for (int i = 0, n = labels.length; i < n; i++)
                labels[i] = new Label();
            Label defaultLabel = new Label();
            mv.visitTableSwitchInsn(0, labels.length - 1, defaultLabel, labels);

            for (int i = 0, n = labels.length; i < n; i++) {
                Field field = fields.get(i);
                mv.visitLabel(labels[i]);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                if (isStatic(field.getModifiers())) {
                    mv.visitFieldInsn(Opcodes.GETSTATIC, classNameInternal, field.getName(), Type.getDescriptor(field.getType()));
                } else {
                    mv.visitVarInsn(Opcodes.ALOAD, 1);
                    mv.visitTypeInsn(Opcodes.CHECKCAST, classNameInternal);
                    // field.getDeclaringClass().getName().replace('.', '/')
                    mv.visitFieldInsn(Opcodes.GETFIELD, classNameInternal, field.getName(), Type.getDescriptor(field.getType()));
                }
                Type fieldType = Type.getType(field.getType());
                box(mv, fieldType);
                mv.visitInsn(Opcodes.ARETURN);
            }
            mv.visitLabel(defaultLabel);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
        mv = insertThrowExceptionForFieldNotFound(mv);
        mv.visitMaxs(maxStack, 3);
        mv.visitEnd();
    }

    static private void insertSetPrimitive(ClassWriter cw, String classNameInternal, List<Field> fields, Type primitiveType, String setterMethodName, int loadValueInstruction) {
        int maxStack = 6;
        int maxLocals = 5;
        final String typeNameInternal = primitiveType.getDescriptor();
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, setterMethodName, "(Ljava/lang/Object;I" + typeNameInternal + ")V", null,
                null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ILOAD, 2);

        if (!fields.isEmpty()) {
            maxStack--;
            Label[] labels = new Label[fields.size()];
            Label labelForInvalidTypes = new Label();
            boolean hasAnyBadTypeLabel = false;
            for (int i = 0, n = labels.length; i < n; i++) {
                if (Type.getType(fields.get(i).getType()).equals(primitiveType))
                    labels[i] = new Label();
                else {
                    labels[i] = labelForInvalidTypes;
                    hasAnyBadTypeLabel = true;
                }
            }
            Label defaultLabel = new Label();
            mv.visitTableSwitchInsn(0, labels.length - 1, defaultLabel, labels);

            for (int i = 0, n = labels.length; i < n; i++) {
                if (!labels[i].equals(labelForInvalidTypes)) {
                    Field field = fields.get(i);
                    mv.visitLabel(labels[i]);
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                    if (isStatic(field.getModifiers())) {
                        mv.visitVarInsn(loadValueInstruction, 3);
                        mv.visitFieldInsn(Opcodes.PUTSTATIC, classNameInternal, field.getName(), typeNameInternal);
                    } else {
                        mv.visitVarInsn(Opcodes.ALOAD, 1);
                        mv.visitTypeInsn(Opcodes.CHECKCAST, classNameInternal);
                        mv.visitVarInsn(loadValueInstruction, 3);
                        // field.getDeclaringClass().getName().replace('.', '/')
                        mv.visitFieldInsn(Opcodes.PUTFIELD, classNameInternal, field.getName(), typeNameInternal);
                    }
                    mv.visitInsn(Opcodes.RETURN);
                }
            }
            // Rest of fields: different type
            if (hasAnyBadTypeLabel) {
                mv.visitLabel(labelForInvalidTypes);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv = insertThrowExceptionForFieldType(mv, primitiveType.getClassName());
            }
            // Default: field not found
            mv.visitLabel(defaultLabel);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
        mv = insertThrowExceptionForFieldNotFound(mv);
        mv.visitMaxs(maxStack, maxLocals);
        mv.visitEnd();
    }

    private static void insertGetPrimitive(ClassWriter cw, String classNameInternal, List<Field> fields, Type primitiveType, String getterMethodName, int returnValueInstruction) {
        int maxStack = 6;
        final String typeNameInternal = primitiveType.getDescriptor();
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, getterMethodName, "(Ljava/lang/Object;I)" + typeNameInternal, null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ILOAD, 2);

        if (!fields.isEmpty()) {
            maxStack--;
            Label[] labels = new Label[fields.size()];
            Label labelForInvalidTypes = new Label();
            boolean hasAnyBadTypeLabel = false;
            for (int i = 0, n = labels.length; i < n; i++) {
                if (Type.getType(fields.get(i).getType()).equals(primitiveType))
                    labels[i] = new Label();
                else {
                    labels[i] = labelForInvalidTypes;
                    hasAnyBadTypeLabel = true;
                }
            }
            Label defaultLabel = new Label();
            mv.visitTableSwitchInsn(0, labels.length - 1, defaultLabel, labels);

            for (int i = 0, n = labels.length; i < n; i++) {
                Field field = fields.get(i);
                if (!labels[i].equals(labelForInvalidTypes)) {
                    mv.visitLabel(labels[i]);
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                    if (isStatic(field.getModifiers())) {
                        mv.visitFieldInsn(Opcodes.GETSTATIC, classNameInternal, field.getName(), typeNameInternal);
                    } else {
                        mv.visitVarInsn(Opcodes.ALOAD, 1);
                        mv.visitTypeInsn(Opcodes.CHECKCAST, classNameInternal);
                        // field.getDeclaringClass().getName().replace('.', '/')
                        mv.visitFieldInsn(Opcodes.GETFIELD, classNameInternal, field.getName(), typeNameInternal);
                    }
                    mv.visitInsn(returnValueInstruction);
                }
            }
            // Rest of fields: different type
            if (hasAnyBadTypeLabel) {
                mv.visitLabel(labelForInvalidTypes);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv = insertThrowExceptionForFieldType(mv, primitiveType.getClassName());
            }
            // Default: field not found
            mv.visitLabel(defaultLabel);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }
        mv = insertThrowExceptionForFieldNotFound(mv);
        mv.visitMaxs(maxStack, 3);
        mv.visitEnd();
    }

    private static MethodVisitor insertThrowExceptionForConstructorNotFound(MethodVisitor mv) {
        String promptMsg = "Constructor not found: ";
        return insertThrowException(mv, promptMsg);
    }

    private static MethodVisitor insertThrowExceptionForMethodNotFound(MethodVisitor mv) {
        String promptMsg = "Method not found: ";
        return insertThrowException(mv, promptMsg);
    }


    private static MethodVisitor insertThrowExceptionForFieldNotFound(MethodVisitor mv) {
        String promptMsg = "Field not found: ";
        return insertThrowException(mv, promptMsg);
    }

    private static MethodVisitor insertThrowExceptionForFieldType(MethodVisitor mv, String fieldType) {
        String promptMsg = "Field not declared as " + fieldType + ": ";
        return insertThrowException(mv, promptMsg);
    }

    private static MethodVisitor insertThrowException(MethodVisitor mv, String promptMsg) {
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/IllegalArgumentException");
        mv.visitInsn(Opcodes.DUP);
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitLdcInsn(promptMsg);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V", false);
        mv.visitInsn(Opcodes.ATHROW);
        return mv;
    }

    private static void box(MethodVisitor mv, Type type) {
        switch (type.getSort()) {
            case Type.VOID:
                mv.visitInsn(ACONST_NULL);
                break;
            case Type.BOOLEAN:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
                break;
            case Type.BYTE:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
                break;
            case Type.CHAR:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
                break;
            case Type.SHORT:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
                break;
            case Type.INT:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
                break;
            case Type.FLOAT:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
                break;
            case Type.LONG:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
                break;
            case Type.DOUBLE:
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
                break;
        }
    }

    private static void unbox(MethodVisitor mv, Type type) {
        switch (type.getSort()) {
            case Type.BOOLEAN:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Boolean");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
                break;
            case Type.BYTE:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Byte");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
                break;
            case Type.CHAR:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Character");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Character", "charValue", "()C", false);
                break;
            case Type.SHORT:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Short");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
                break;
            case Type.INT:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Integer");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
                break;
            case Type.FLOAT:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Float");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
                break;
            case Type.LONG:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Long");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
                break;
            case Type.DOUBLE:
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Double");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
                break;
            case Type.ARRAY:
                mv.visitTypeInsn(Opcodes.CHECKCAST, type.getDescriptor());
                break;
            case Type.OBJECT:
                mv.visitTypeInsn(Opcodes.CHECKCAST, type.getInternalName());
                break;
        }
    }

    @Override
    public String toString() {
        return classAccessor.toString();
    }

    public boolean isNonStaticMemberClass() {
        return info.isNonStaticMemberClass;
    }

    public Class<?>[][] getConstructorParameterTypes() {
        return info.constructorParameterTypes;
    }

    public int[] getMethodModifiers() {
        return info.methodModifiers;
    }

    public int[] getFieldModifiers() {
        return info.fieldModifiers;
    }

    public int[] getConstructorModifiers() {
        return info.constructorModifiers;
    }

    public int indexOfConstructor(Constructor<?> constructor) {
        final Integer integer = info.constructors.get(constructor);
        if (null != integer) return integer;
        throw new IllegalArgumentException("Unable to find constructor: " + constructor);
    }

    public int indexOfMethod(Method method) {
        final Integer integer = info.methods.get(method);
        if (null != integer) return integer;
        throw new IllegalArgumentException("Unable to find method: " + method);
    }

    /**
     * Returns the index of the first method with the specified name.
     */
    public int indexOfMethod(String methodName) {
        for (int i = 0, n = info.methodNames.length; i < n; i++)
            if (info.methodNames[i].equals(methodName)) return i;
        throw new IllegalArgumentException("Unable to find public method: " + methodName);
    }

    /**
     * Returns the index of the first method with the specified name and param types.
     */
    public int indexOfMethod(String methodName, Class<?>... paramTypes) {
        final String[] methodNames = info.methodNames;
        for (int i = 0, n = methodNames.length; i < n; i++)
            if (methodNames[i].equals(methodName) && Arrays.equals(paramTypes, info.parameterTypes[i])) return i;
        throw new IllegalArgumentException("Unable to find public method: " + methodName + " " + Arrays.toString(paramTypes));
    }

    /**
     * Returns the index of the first method with the specified name and the specified number of arguments.
     */
    public int indexOfMethod(String methodName, int paramsCount) {
        final String[] methodNames = info.methodNames;
        final Class<?>[][] parameterTypes = info.parameterTypes;
        for (int i = 0, n = methodNames.length; i < n; i++) {
            if (methodNames[i].equals(methodName) && parameterTypes[i].length == paramsCount) return i;
        }
        throw new IllegalArgumentException("Unable to find public method: " + methodName + " with " + paramsCount + " params.");
    }

    public String[] getMethodNames() {
        return info.methodNames;
    }

    public Class<?>[][] getParameterTypes() {
        return info.parameterTypes;
    }

    public Class<?>[] getReturnTypes() {
        return info.returnTypes;
    }

    public String[] getFieldNames() {
        return info.fieldNames;
    }

    public Class<?>[] getFieldTypes() {
        return info.fieldTypes;
    }

    public int getFieldCount() {
        return info.fieldTypes.length;
    }

    public Field[] getFields() {
        return info.fields.keySet().toArray(new Field[0]);
    }

    public int indexOfField(String fieldName) {
        String[] fieldNames = info.fieldNames;
        for (int i = 0, n = fieldNames.length; i < n; i++)
            if (fieldNames[i].equals(fieldName)) return i;
        throw new IllegalArgumentException("Unable to find public field: " + fieldName);
    }

    public int indexOfField(Field field) {
        final Integer integer = info.fields.get(field);
        if (integer != null) return integer;
        throw new IllegalArgumentException("Unable to find field: " + field);
    }

    public Object newInstance(int constructorIndex, Object... args) {
        return classAccessor.newInstance(constructorIndex, args);
    }

    public Object newInstance() {
        return classAccessor.newInstance();
    }

    public Object newObjectInstance() {
        return classAccessor.newObjectInstance();
    }

    public Object newInstance(Object instance, int constructorIndex, Object... args) {
        return classAccessor.newInstance(instance, constructorIndex, args);
    }

    public Object invoke(Object instance, int methodIndex, Object... args) {
        return classAccessor.invoke(instance, methodIndex, args);
    }

    public void set(Object instance, int fieldIndex, Object value) {
        classAccessor.set(instance, fieldIndex, value);
    }

    public void setBoolean(Object instance, int fieldIndex, boolean value) {
        classAccessor.setBoolean(instance, fieldIndex, value);
    }

    public void setByte(Object instance, int fieldIndex, byte value) {
        classAccessor.setByte(instance, fieldIndex, value);
    }

    public void setShort(Object instance, int fieldIndex, short value) {
        classAccessor.setShort(instance, fieldIndex, value);
    }

    public void setInt(Object instance, int fieldIndex, int value) {
        classAccessor.setInt(instance, fieldIndex, value);
    }

    public void setLong(Object instance, int fieldIndex, long value) {
        classAccessor.setLong(instance, fieldIndex, value);
    }

    public void setDouble(Object instance, int fieldIndex, double value) {
        classAccessor.setDouble(instance, fieldIndex, value);
    }

    public void setFloat(Object instance, int fieldIndex, float value) {
        classAccessor.setFloat(instance, fieldIndex, value);
    }

    public void setChar(Object instance, int fieldIndex, char value) {
        classAccessor.setChar(instance, fieldIndex, value);
    }

    public Object get(Object instance, int fieldIndex) {
        return classAccessor.get(instance, fieldIndex);
    }

    public char getChar(Object instance, int fieldIndex) {
        return classAccessor.getChar(instance, fieldIndex);
    }

    public boolean getBoolean(Object instance, int fieldIndex) {
        return classAccessor.getBoolean(instance, fieldIndex);
    }

    public byte getByte(Object instance, int fieldIndex) {
        return classAccessor.getByte(instance, fieldIndex);
    }

    public short getShort(Object instance, int fieldIndex) {
        return classAccessor.getShort(instance, fieldIndex);
    }

    public int getInt(Object instance, int fieldIndex) {
        return classAccessor.getInt(instance, fieldIndex);
    }

    public long getLong(Object instance, int fieldIndex) {
        return classAccessor.getLong(instance, fieldIndex);
    }

    public double getDouble(Object instance, int fieldIndex) {
        return classAccessor.getDouble(instance, fieldIndex);
    }

    public float getFloat(Object instance, int fieldIndex) {
        return classAccessor.getFloat(instance, fieldIndex);
    }

    public interface ClassAccessor {
        Object newInstance(int constructorIndex, Object... args);

        Object newInstance();

        Object newObjectInstance();

        Object newInstance(Object instance, int constructorIndex, Object... args);

        Object invoke(Object instance, int methodIndex, Object... args);

        void set(Object instance, int fieldIndex, Object value);

        void setBoolean(Object instance, int fieldIndex, boolean value);

        void setByte(Object instance, int fieldIndex, byte value);

        void setShort(Object instance, int fieldIndex, short value);

        void setInt(Object instance, int fieldIndex, int value);

        void setLong(Object instance, int fieldIndex, long value);

        void setDouble(Object instance, int fieldIndex, double value);

        void setFloat(Object instance, int fieldIndex, float value);

        void setChar(Object instance, int fieldIndex, char value);

        Object get(Object instance, int fieldIndex);

        char getChar(Object instance, int fieldIndex);

        boolean getBoolean(Object instance, int fieldIndex);

        byte getByte(Object instance, int fieldIndex);

        short getShort(Object instance, int fieldIndex);

        int getInt(Object instance, int fieldIndex);

        long getLong(Object instance, int fieldIndex);

        double getDouble(Object instance, int fieldIndex);

        float getFloat(Object instance, int fieldIndex);
    }

    public static class UnsafeHolder {

        public final static Unsafe theUnsafe;

        static {
            try {
                Field uf = Unsafe.class.getDeclaredField("theUnsafe");
                uf.setAccessible(true);
                theUnsafe = (Unsafe) uf.get(null);
            } catch (Exception e) {
                throw new AssertionError(e);
            }
        }
    }

    public Map<Field, Integer> fields() {
        return Collections.unmodifiableMap(info.fields);
    }

    public final static class ClassInfo {
        public String[] fieldNames;
        public Class<?>[] fieldTypes;
        public String[] methodNames;
        public Class<?>[][] parameterTypes;
        public Class<?>[] returnTypes;
        public int[] fieldModifiers;
        public int[] methodModifiers;
        public int[] constructorModifiers;
        public Class<?>[][] constructorParameterTypes;
        public boolean isNonStaticMemberClass;
        public Map<Method, Integer> methods;
        public Map<Field, Integer> fields;
        public Map<Constructor<?>, Integer> constructors;
    }

}
