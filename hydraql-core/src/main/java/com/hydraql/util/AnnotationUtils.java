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

package com.hydraql.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author leojie@apache.org 2024/8/17 21:18
 */
public final class AnnotationUtils {
  private AnnotationUtils() {
  }

  public static <T extends Annotation> boolean isAnnotationPresent(Class<?> beanClass,
      Class<T> annotationClass) {
    return AnnotationUtils.findFirstAnnotation(annotationClass, beanClass) != null;
  }

  public static <T extends Annotation> T getAnnotation(Class<?> beanClass,
      Class<T> annotationClass) {
    return AnnotationUtils.findFirstAnnotation(annotationClass, beanClass);
  }

  public static <T extends Annotation> boolean isAnnotationPresent(Field field,
      Class<T> annotationClass) {
    return findFirstAnnotation(annotationClass, field) != null;
  }

  public static <T extends Annotation> T getAnnotation(Field field, Class<T> annotationClass) {
    return AnnotationUtils.findFirstAnnotation(annotationClass, field);
  }

  public static <T extends Annotation> boolean isAnnotationPresent(Method method,
      Class<T> annotationClass) {
    return findFirstAnnotation(annotationClass, method) != null;
  }

  public static <T extends Annotation> T getAnnotation(Method method, Class<T> annotationClass) {
    return AnnotationUtils.findFirstAnnotation(annotationClass, method);
  }

  private static <T extends Annotation> T findFirstAnnotation(Class<T> annotationClazz,
      Field field) {
    return getAnnotation(annotationClazz, new HashSet<>(), field.getDeclaredAnnotations());
  }

  private static <T extends Annotation> T findFirstAnnotation(Class<T> annotationClazz,
      Class<?> clz) {
    Set<Class<? extends Annotation>> hashSet = new HashSet<>();
    T annotation = getAnnotation(annotationClazz, hashSet, clz.getDeclaredAnnotations());
    if (annotation != null) {
      return annotation;
    }
    Class<?> currentClass = clz.getSuperclass();
    while (currentClass != null) {
      annotation = getAnnotation(annotationClazz, hashSet, currentClass.getDeclaredAnnotations());
      if (annotation != null) {
        return annotation;
      }
      currentClass = currentClass.getSuperclass();
    }
    return null;
  }

  private static <T extends Annotation> T findFirstAnnotation(Class<T> annotationClazz,
      Method method) {
    return getAnnotation(annotationClazz, new HashSet<>(), method.getDeclaredAnnotations());
  }

  @SuppressWarnings("unchecked")
  private static <T extends Annotation> T getAnnotation(Class<T> annotationClazz,
      Set<Class<? extends Annotation>> annotationSet, Annotation... annotations) {
    for (Annotation annotation : annotations) {
      if (annotationSet.add(annotation.annotationType())) {
        if (annotationClazz.isAssignableFrom(annotation.annotationType())) {
          return (T) annotation;
        }
        annotation = getAnnotation(annotationClazz, annotationSet,
          annotation.annotationType().getDeclaredAnnotations());
        if (annotation != null) {
          return (T) annotation;
        }
      }
    }
    return null;
  }
}
