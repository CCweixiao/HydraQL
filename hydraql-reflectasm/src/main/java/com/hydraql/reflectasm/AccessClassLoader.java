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

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.WeakHashMap;

/**
 * @author leojie@apache.org 2024/7/23 21:55
 */
class AccessClassLoader extends ClassLoader {
  // Weak-references to class loaders, to avoid perm gen memory leaks, for example in app
  // servers/web containters if the
  // reflectasm library (including this class) is loaded outside the deployed applications (WAR/EAR)
  // using ReflectASM/Kryo (exts,
  // user classpath, etc).
  // The key is the parent class loader and the value is the AccessClassLoader, both are
  // weak-referenced in the hash table.
  static private final WeakHashMap<ClassLoader, WeakReference<AccessClassLoader>> accessClassLoaders =
      new WeakHashMap<>();

  // Fast-path for classes loaded in the same ClassLoader as this class.
  static private final ClassLoader selfContextParentClassLoader =
      getParentClassLoader(AccessClassLoader.class);
  static private volatile AccessClassLoader selfContextAccessClassLoader =
      new AccessClassLoader(selfContextParentClassLoader);

  static private volatile Method defineClassMethod;

  private final HashSet<String> localClassNames = new HashSet<>();

  private AccessClassLoader(ClassLoader parent) {
    super(parent);
  }

  /**
   * Returns null if the access class has not yet been defined.
   */
  Class<?> loadAccessClass(String name) {
    // No need to check the parent class loader if the access class hasn't been defined yet.
    if (localClassNames.contains(name)) {
      try {
        return loadClass(name, false);
      } catch (ClassNotFoundException ex) {
        throw new RuntimeException(ex); // Should not happen, since we know the class has been
                                        // defined.
      }
    }
    return null;
  }

  Class<?> defineAccessClass(String name, byte[] bytes) throws ClassFormatError {
    localClassNames.add(name);
    return defineClass(name, bytes);
  }

  protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
    // These classes come from the classloader that loaded AccessClassLoader.
    if (name.equals(FieldAccess.class.getName())) return FieldAccess.class;
    if (name.equals(MethodAccess.class.getName())) return MethodAccess.class;
    if (name.equals(ConstructorAccess.class.getName())) return ConstructorAccess.class;
    if (name.equals(PublicConstructorAccess.class.getName())) return PublicConstructorAccess.class;
    // All other classes come from the classloader that loaded the type we are accessing.
    return super.loadClass(name, resolve);
  }

  Class<?> defineClass(String name, byte[] bytes) throws ClassFormatError {
    try {
      // Attempt to load the access class in the same loader, which makes protected and default
      // access members accessible.
      return (Class<?>) getDefineClassMethod().invoke(getParent(),
        new Object[] { name, bytes, 0, bytes.length, getClass().getProtectionDomain() });
    } catch (Exception ignored) {
      // continue with the definition in the current loader (won't have access to protected and
      // package-protected members)
    }
    return defineClass(name, bytes, 0, bytes.length, getClass().getProtectionDomain());
  }

  // As per JLS, section 5.3,
  // "The runtime package of a class or interface is determined by the package name and defining
  // class loader of the class or
  // interface."
  static boolean areInSameRuntimeClassLoader(Class<?> type1, Class<?> type2) {
    if (type1.getPackage() != type2.getPackage()) {
      return false;
    }
    ClassLoader loader1 = type1.getClassLoader();
    ClassLoader loader2 = type2.getClassLoader();
    ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
    if (loader1 == null) {
      return (loader2 == null || loader2 == systemClassLoader);
    }
    if (loader2 == null) return loader1 == systemClassLoader;
    return loader1 == loader2;
  }

  static private ClassLoader getParentClassLoader(Class<?> type) {
    ClassLoader parent = type.getClassLoader();
    if (parent == null) parent = ClassLoader.getSystemClassLoader();
    return parent;
  }

  static private Method getDefineClassMethod() throws Exception {
    if (defineClassMethod == null) {
      synchronized (accessClassLoaders) {
        if (defineClassMethod == null) {
          defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class,
            byte[].class, int.class, int.class, ProtectionDomain.class);
          try {
            defineClassMethod.setAccessible(true);
          } catch (Exception ignored) {
          }
        }
      }
    }
    return defineClassMethod;
  }

  static AccessClassLoader get(Class<?> type) {
    ClassLoader parent = getParentClassLoader(type);
    // 1. fast-path:
    if (selfContextParentClassLoader.equals(parent)) {
      if (selfContextAccessClassLoader == null) {
        synchronized (accessClassLoaders) { // DCL with volatile semantics
          if (selfContextAccessClassLoader == null)
            selfContextAccessClassLoader = new AccessClassLoader(selfContextParentClassLoader);
        }
      }
      return selfContextAccessClassLoader;
    }
    // 2. normal search:
    synchronized (accessClassLoaders) {
      WeakReference<AccessClassLoader> ref = accessClassLoaders.get(parent);
      if (ref != null) {
        AccessClassLoader accessClassLoader = ref.get();
        if (accessClassLoader != null) return accessClassLoader;
        else accessClassLoaders.remove(parent); // the value has been GC-reclaimed, but still not
                                                // the key (defensive sanity)
      }
      AccessClassLoader accessClassLoader = new AccessClassLoader(parent);
      accessClassLoaders.put(parent, new WeakReference<>(accessClassLoader));
      return accessClassLoader;
    }
  }

  static public void remove(ClassLoader parent) {
    // 1. fast-path:
    if (selfContextParentClassLoader.equals(parent)) {
      selfContextAccessClassLoader = null;
    } else {
      // 2. normal search:
      synchronized (accessClassLoaders) {
        accessClassLoaders.remove(parent);
      }
    }
  }

  static public int activeAccessClassLoaders() {
    int sz = accessClassLoaders.size();
    if (selfContextAccessClassLoader != null) {
      sz++;
    }
    return sz;
  }
}
