/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hydraql.reflectasm;

import com.hydraql.reflectasm.exceptions.ReflectionException;
import com.hydraql.reflectasm.invoker.AmbiguousMethodInvoker;
import com.hydraql.reflectasm.invoker.GetFieldInvoker;
import com.hydraql.reflectasm.invoker.Invoker;
import com.hydraql.reflectasm.invoker.MethodInvoker;
import com.hydraql.reflectasm.invoker.SetFieldInvoker;
import com.hydraql.reflectasm.property.PropertyNamer;
import com.hydraql.reflectasm.util.MapUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.ReflectPermission;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leojie@apache.org 2024/8/11 13:54
 */
public class Reflector {
  private final Class<?> clazz;
  private final MethodAccess methodAccess;
  private final FieldAccess fieldAccess;
  private final Map<String, Invoker> setMethods = new HashMap<>();
  private final Map<String, Invoker> getMethods = new HashMap<>();
  private final Map<String, Class<?>> setTypes = new HashMap<>();
  private final Map<String, Class<?>> getTypes = new HashMap<>();
  private Constructor<?> defaultConstructor;

  public Reflector(Class<?> clazz) {
    this.clazz = clazz;
    this.methodAccess = MethodAccess.get(clazz);
    this.fieldAccess = FieldAccess.get(clazz);
    this.addDefaultConstructor(clazz);
    Method[] classMethods = getClassMethods(clazz);
    addGetMethods(classMethods);
    addSetMethods(classMethods);
    addFields();
  }

  private void addDefaultConstructor(Class<?> clazz) {
    Constructor<?>[] constructors = clazz.getDeclaredConstructors();
    Arrays.stream(constructors).filter(constructor -> constructor.getParameterTypes().length == 0)
        .findAny().ifPresent(constructor -> this.defaultConstructor = constructor);
  }

  private void addFields() {
    Field[] fields = this.getFields();
    for (Field field : fields) {
      if (isNotGeneralProperty(field)) {
        continue;
      }
      if (!setMethods.containsKey(field.getName())) {
        addSetField(field);
      }
      if (!getMethods.containsKey(field.getName())) {
        addGetField(field);
      }
    }
  }

  private void addSetField(Field field) {
    if (isValidPropertyName(field.getName())) {
      setMethods.put(field.getName(), new SetFieldInvoker(field));
      Type fieldType = TypeParameterResolver.resolveFieldType(field, clazz);
      setTypes.put(field.getName(), typeToClass(fieldType));
    }
  }

  private void addGetField(Field field) {
    if (isValidPropertyName(field.getName())) {
      getMethods.put(field.getName(), new GetFieldInvoker(field));
      Type fieldType = TypeParameterResolver.resolveFieldType(field, clazz);
      getTypes.put(field.getName(), typeToClass(fieldType));
    }
  }

  private void addGetMethods(Method[] methods) {
    Map<String, List<Method>> conflictingGetters = new HashMap<>();
    Arrays.stream(methods).filter(
      m -> m.getParameterTypes().length == 0 && PropertyNamer.isGetter(m.getName())).forEach(
        m -> addMethodConflict(conflictingGetters, PropertyNamer.methodToProperty(m.getName()), m));
    resolveGetterConflicts(conflictingGetters);
  }

  private void resolveGetterConflicts(Map<String, List<Method>> conflictingGetters) {
    for (Map.Entry<String, List<Method>> entry : conflictingGetters.entrySet()) {
      Method winner = null;
      String propName = entry.getKey();
      boolean isAmbiguous = false;
      for (Method candidate : entry.getValue()) {
        if (winner == null) {
          winner = candidate;
          continue;
        }
        Class<?> winnerType = winner.getReturnType();
        Class<?> candidateType = candidate.getReturnType();
        if (candidateType.equals(winnerType)) {
          if (!boolean.class.equals(candidateType)) {
            isAmbiguous = true;
            break;
          }
          if (candidate.getName().startsWith("is")) {
            winner = candidate;
          }
        } /*
           * else if (candidateType.isAssignableFrom(winnerType)) { // OK getter type is descendant
           * }
           */ else if (winnerType.isAssignableFrom(candidateType)) {
          winner = candidate;
        } else {
          isAmbiguous = true;
          break;
        }
      }
      addGetMethod(propName, winner, isAmbiguous);
    }
  }

  private void addGetMethod(String name, Method method, boolean isAmbiguous) {
    int methodIndex = methodAccess.getIndex(method);
    MethodInvoker invoker = isAmbiguous
        ? new AmbiguousMethodInvoker(methodAccess, methodIndex, MessageFormat.format(
          "Illegal overloaded getter method with ambiguous type for property ''{0}'' in class ''{1}''. This breaks the JavaBeans specification and can cause unpredictable results.",
          name, method.getDeclaringClass().getName()))
        : new MethodInvoker(methodAccess, methodIndex);
    getMethods.put(name, invoker);
    Type returnType = TypeParameterResolver.resolveReturnType(method, clazz);
    getTypes.put(name, typeToClass(returnType));
  }

  private void addSetMethods(Method[] methods) {
    Map<String, List<Method>> conflictingSetters = new HashMap<>();
    Arrays.stream(methods).filter(
      m -> m.getParameterTypes().length == 1 && PropertyNamer.isSetter(m.getName())).forEach(
        m -> addMethodConflict(conflictingSetters, PropertyNamer.methodToProperty(m.getName()), m));
    resolveSetterConflicts(conflictingSetters);
  }

  private void addMethodConflict(Map<String, List<Method>> conflictingMethods, String name,
      Method method) {
    if (isValidPropertyName(name)) {
      List<Method> list = MapUtil.computeIfAbsent(conflictingMethods, name, k -> new ArrayList<>());
      list.add(method);
    }
  }

  private void resolveSetterConflicts(Map<String, List<Method>> conflictingSetters) {
    for (Map.Entry<String, List<Method>> entry : conflictingSetters.entrySet()) {
      String propName = entry.getKey();
      List<Method> setters = entry.getValue();
      Class<?> getterType = getTypes.get(propName);
      boolean isGetterAmbiguous = getMethods.get(propName) instanceof AmbiguousMethodInvoker;
      boolean isSetterAmbiguous = false;
      Method match = null;
      for (Method setter : setters) {
        if (!isGetterAmbiguous && setter.getParameterTypes()[0].equals(getterType)) {
          // should be the best match
          match = setter;
          break;
        }
        if (!isSetterAmbiguous) {
          match = pickBetterSetter(match, setter, propName);
          isSetterAmbiguous = match == null;
        }
      }
      if (match != null) {
        addSetMethod(propName, match);
      }
    }
  }

  private Method pickBetterSetter(Method setter1, Method setter2, String property) {
    if (setter1 == null) {
      return setter2;
    }
    Class<?> paramType1 = setter1.getParameterTypes()[0];
    Class<?> paramType2 = setter2.getParameterTypes()[0];
    if (paramType1.isAssignableFrom(paramType2)) {
      return setter2;
    }
    if (paramType2.isAssignableFrom(paramType1)) {
      return setter1;
    }
    int methodIndex = methodAccess.getIndex(setter1);
    MethodInvoker invoker = new AmbiguousMethodInvoker(methodAccess, methodIndex,
        MessageFormat.format(
          "Ambiguous setters defined for property ''{0}'' in class ''{1}'' with types ''{2}'' and ''{3}''.",
          property, setter2.getDeclaringClass().getName(), paramType1.getName(),
          paramType2.getName()));
    setMethods.put(property, invoker);
    Type[] paramTypes = TypeParameterResolver.resolveParamTypes(setter1, clazz);
    setTypes.put(property, typeToClass(paramTypes[0]));
    return null;
  }

  private void addSetMethod(String name, Method method) {
    int methodIndex = methodAccess.getIndex(method);
    MethodInvoker invoker = new MethodInvoker(methodAccess, methodIndex);
    setMethods.put(name, invoker);
    Type[] paramTypes = TypeParameterResolver.resolveParamTypes(method, clazz);
    setTypes.put(name, typeToClass(paramTypes[0]));
  }

  private boolean isValidPropertyName(String name) {
    return (!name.startsWith("$") && !"serialVersionUID".equals(name) && !"class".equals(name));
  }

  private Class<?> typeToClass(Type src) {
    Class<?> result = null;
    if (src instanceof Class) {
      result = (Class<?>) src;
    } else if (src instanceof ParameterizedType) {
      result = (Class<?>) ((ParameterizedType) src).getRawType();
    } else if (src instanceof GenericArrayType) {
      Type componentType = ((GenericArrayType) src).getGenericComponentType();
      if (componentType instanceof Class) {
        result = Array.newInstance((Class<?>) componentType, 0).getClass();
      } else {
        Class<?> componentClass = typeToClass(componentType);
        result = Array.newInstance(componentClass, 0).getClass();
      }
    }
    if (result == null) {
      result = Object.class;
    }
    return result;
  }

  /**
   * This method returns an array containing all methods declared in this class and any superclass.
   * <br/>
   * We use this method, instead of the simpler <code>Class.getMethods()</code>, <br/>
   * because we want to look for private methods as well.
   * @param clazz The class
   * @return An array containing all methods in this class
   */
  private Method[] getClassMethods(Class<?> clazz) {
    Map<String, Method> uniqueMethods = new HashMap<>();
    Class<?> currentClass = clazz;
    while (currentClass != null && currentClass != Object.class) {
      addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());

      // we also need to look for interface methods -
      // because the class may be abstract
      Class<?>[] interfaces = currentClass.getInterfaces();
      for (Class<?> anInterface : interfaces) {
        addUniqueMethods(uniqueMethods, anInterface.getMethods());
      }

      currentClass = currentClass.getSuperclass();
    }

    Collection<Method> methods = uniqueMethods.values();

    return methods.toArray(new Method[0]);
  }

  private void addUniqueMethods(Map<String, Method> uniqueMethods, Method[] methods) {
    for (Method currentMethod : methods) {
      if (!currentMethod.isBridge()) {
        String signature = getSignature(currentMethod);
        // check to see if the method is already known
        // if it is known, then an extended class must have
        // overridden a method
        if (!uniqueMethods.containsKey(signature)) {
          uniqueMethods.put(signature, currentMethod);
        }
      }
    }
  }

  private String getSignature(Method method) {
    StringBuilder sb = new StringBuilder();
    Class<?> returnType = method.getReturnType();
    sb.append(returnType.getName()).append('#');
    sb.append(method.getName());
    Class<?>[] parameters = method.getParameterTypes();
    for (int i = 0; i < parameters.length; i++) {
      sb.append(i == 0 ? ':' : ',').append(parameters[i].getName());
    }
    return sb.toString();
  }

  public Class<?> getClazz() {
    return clazz;
  }

  public MethodAccess getMethodAccess() {
    return methodAccess;
  }

  public FieldAccess getFieldAccess() {
    return fieldAccess;
  }

  public Field[] getFields() {
    return this.getFieldAccess().getFields();
  }

  public Constructor<?> getDefaultConstructor() {
    if (defaultConstructor != null) {
      return defaultConstructor;
    }
    throw new ReflectionException("There is no default constructor for " + clazz);
  }

  public boolean hasDefaultConstructor() {
    return defaultConstructor != null;
  }

  /**
   * Gets the type for a property getter.
   * @param propertyName - the name of the property
   * @return The Class of the property getter
   */
  public Class<?> getSetterType(String propertyName) {
    Class<?> typeClazz = setTypes.get(propertyName);
    if (typeClazz == null) {
      throw new ReflectionException(
          "There is no setter for property named '" + propertyName + "' in '" + clazz + "'");
    }
    return typeClazz;
  }

  /**
   * Gets the type for a property getter.
   * @param propertyName - the name of the property
   * @return The Class of the property getter
   */
  public Class<?> getGetterType(String propertyName) {
    Class<?> typeClazz = getTypes.get(propertyName);
    if (typeClazz == null) {
      throw new ReflectionException(
          "There is no getter for property named '" + propertyName + "' in '" + clazz + "'");
    }
    return typeClazz;
  }

  public Invoker getSetInvoker(String propertyName) {
    Invoker method = setMethods.get(propertyName);
    if (method == null) {
      throw new ReflectionException(
          "There is no setter for property named '" + propertyName + "' in '" + clazz + "'");
    }
    return method;
  }

  public Invoker getGetInvoker(String propertyName) {
    Invoker method = getMethods.get(propertyName);
    if (method == null) {
      throw new ReflectionException(
          "There is no getter for property named '" + propertyName + "' in '" + clazz + "'");
    }
    return method;
  }

  /**
   * Check if the control member is accessible.
   * @return If the control member is accessible, it return {@literal true}
   * @since 1.0.4
   */
  public static boolean canControlMemberAccessible() {
    try {
      SecurityManager securityManager = System.getSecurityManager();
      if (null != securityManager) {
        securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
      }
    } catch (SecurityException e) {
      return false;
    }
    return true;
  }

  /**
   * Determine whether a field type is a regular attribute<br/>
   * Fields modified with static and final do not belong to a regular field for the time being.
   * @param field field object
   * @return true or false
   */
  public static boolean isNotGeneralProperty(Field field) {
    if (field == null) {
      return true;
    }
    int modifiers = field.getModifiers();
    return Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers);
  }

  public <T> T create(Class<T> type) {
    return instantiateClass(type);
  }

  private <T> T instantiateClass(Class<T> type) {
    try {
      Constructor<T> constructor = type.getDeclaredConstructor();
      try {
        return constructor.newInstance();
      } catch (IllegalAccessException e) {
        if (Reflector.canControlMemberAccessible()) {
          constructor.setAccessible(true);
          return constructor.newInstance();
        }
        throw e;
      }
    } catch (Exception e) {
      throw new ReflectionException("Error instantiating " + type + ". Cause: " + e, e);
    }
  }
}
