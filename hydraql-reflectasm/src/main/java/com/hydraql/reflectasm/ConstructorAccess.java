package com.hydraql.reflectasm;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author leojie@apache.org 2024/7/23 21:56
 */
public abstract class ConstructorAccess<T> {
    boolean isNonStaticMemberClass;

    public boolean isNonStaticMemberClass () {
        return isNonStaticMemberClass;
    }

    /** Constructor for top-level classes and static nested classes.
     * <p>
     * If the underlying class is a inner (non-static nested) class, a new instance will be created using <code>null</code> as the
     * this$0 synthetic reference. The instantiated object will work as long as it actually don't use any member variable or method
     * fron the enclosing instance. */
    abstract public T newInstance ();

    /** Constructor for inner classes (non-static nested classes).
     * @param enclosingInstance The instance of the enclosing type to which this inner instance is related to (assigned to its
     *           synthetic this$0 field). */
    abstract public T newInstance (Object enclosingInstance);

    static public <T> ConstructorAccess<T> get (Class<T> type) {
        Class enclosingType = type.getEnclosingClass();
        boolean isNonStaticMemberClass = enclosingType != null && type.isMemberClass() && !Modifier.isStatic(type.getModifiers());

        String className = type.getName();
        String accessClassName = className + "ConstructorAccess";
        if (accessClassName.startsWith("java.")) accessClassName = "reflectasm." + accessClassName;

        Class accessClass;
        AccessClassLoader loader = AccessClassLoader.get(type);
        synchronized (loader) {
            accessClass = loader.loadAccessClass(accessClassName);
            if (accessClass == null) {
                String accessClassNameInternal = accessClassName.replace('.', '/');
                String classNameInternal = className.replace('.', '/');
                String enclosingClassNameInternal;
                Constructor<T> constructor = null;
                int modifiers = 0;
                if (!isNonStaticMemberClass) {
                    enclosingClassNameInternal = null;
                    try {
                        constructor = type.getDeclaredConstructor((Class[])null);
                        modifiers = constructor.getModifiers();
                    } catch (Exception ex) {
                        throw new RuntimeException("Class cannot be created (missing no-arg constructor): " + type.getName(), ex);
                    }
                    if (Modifier.isPrivate(modifiers)) {
                        throw new RuntimeException("Class cannot be created (the no-arg constructor is private): " + type.getName());
                    }
                } else {
                    enclosingClassNameInternal = enclosingType.getName().replace('.', '/');
                    try {
                        constructor = type.getDeclaredConstructor(enclosingType); // Inner classes should have this.
                        modifiers = constructor.getModifiers();
                    } catch (Exception ex) {
                        throw new RuntimeException(
                                "Non-static member class cannot be created (missing enclosing class constructor): " + type.getName(), ex);
                    }
                    if (Modifier.isPrivate(modifiers)) {
                        throw new RuntimeException(
                                "Non-static member class cannot be created (the enclosing class constructor is private): " + type.getName());
                    }
                }
                String superclassNameInternal = Modifier.isPublic(modifiers)
                        ? "com/hydraql/reflectasm/PublicConstructorAccess"
                        : "com/hydraql/reflectasm/ConstructorAccess";

                ClassWriter cw = new ClassWriter(0);
                cw.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, accessClassNameInternal, null, superclassNameInternal, null);

                insertConstructor(cw, superclassNameInternal);
                insertNewInstance(cw, classNameInternal);
                insertNewInstanceInner(cw, classNameInternal, enclosingClassNameInternal);

                cw.visitEnd();
                accessClass = loader.defineAccessClass(accessClassName, cw.toByteArray());
            }
        }
        ConstructorAccess<T> access;
        try {
            access = (ConstructorAccess<T>)accessClass.newInstance();
        } catch (Throwable t) {
            throw new RuntimeException("Exception constructing constructor access class: " + accessClassName, t);
        }
        if (!(access instanceof PublicConstructorAccess) && !AccessClassLoader.areInSameRuntimeClassLoader(type, accessClass)) {
            // Must test this after the try-catch block, whether the class has been loaded as if has been defined.
            // Throw a Runtime exception here instead of an IllegalAccessError when invoking newInstance()
            throw new RuntimeException((!isNonStaticMemberClass
                    ? "Class cannot be created (the no-arg constructor is protected or package-protected, and its ConstructorAccess could not be defined in the same class loader): "
                    : "Non-static member class cannot be created (the enclosing class constructor is protected or package-protected, and its ConstructorAccess could not be defined in the same class loader): ")
                    + type.getName());
        }
        access.isNonStaticMemberClass = isNonStaticMemberClass;
        return access;
    }

    static private void insertConstructor (ClassWriter cw, String superclassNameInternal) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, superclassNameInternal, "<init>", "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    static void insertNewInstance (ClassWriter cw, String classNameInternal) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "newInstance", "()Ljava/lang/Object;", null, null);
        mv.visitCode();
        mv.visitTypeInsn(Opcodes.NEW, classNameInternal);
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, classNameInternal, "<init>", "()V");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }

    static void insertNewInstanceInner (ClassWriter cw, String classNameInternal, String enclosingClassNameInternal) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "newInstance", "(Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        mv.visitCode();
        if (enclosingClassNameInternal != null) {
            mv.visitTypeInsn(Opcodes.NEW, classNameInternal);
            mv.visitInsn(Opcodes.DUP);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitTypeInsn(Opcodes.CHECKCAST, enclosingClassNameInternal);
            mv.visitInsn(Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;");
            mv.visitInsn(Opcodes.POP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, classNameInternal, "<init>", "(L" + enclosingClassNameInternal + ";)V");
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(4, 2);
        } else {
            mv.visitTypeInsn(Opcodes.NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(Opcodes.DUP);
            mv.visitLdcInsn("Not an inner class.");
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "(Ljava/lang/String;)V");
            mv.visitInsn(Opcodes.ATHROW);
            mv.visitMaxs(3, 2);
        }
        mv.visitEnd();
    }
}
