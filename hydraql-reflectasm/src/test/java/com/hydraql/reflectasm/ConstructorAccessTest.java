/**
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

import junit.framework.TestCase;

import java.util.List;

public class ConstructorAccessTest extends TestCase {
  static private boolean java17;

  static {
    try {
      Object version = Runtime.class.getDeclaredMethod("version").invoke(null);
      java17 = ((List<Integer>) version.getClass().getDeclaredMethod("version").invoke(version))
          .get(0) >= 17;
    } catch (Exception ignored) {
      java17 = false;
    }
  }

  public void testNewInstance() {
    ConstructorAccess<SomeClass> access = ConstructorAccess.get(SomeClass.class);
    SomeClass someObject = new SomeClass();
    assertEquals(someObject, access.newInstance());
    assertEquals(someObject, access.newInstance());
    assertEquals(someObject, access.newInstance());
  }

  public void testPackagePrivateNewInstance() {
    if (java17) return;
    ConstructorAccess<PackagePrivateClass> access =
        ConstructorAccess.get(PackagePrivateClass.class);
    PackagePrivateClass someObject = new PackagePrivateClass();
    assertEquals(someObject, access.newInstance());
    assertEquals(someObject, access.newInstance());
    assertEquals(someObject, access.newInstance());
  }

  public void testHasArgumentConstructor() {
    HasArgumentConstructor someObject = new HasArgumentConstructor("bla");
    ConstructorAccess<HasArgumentConstructor> access =
        ConstructorAccess.get(HasArgumentConstructor.class);
    assertEquals(someObject, access.classAccessor.newInstance(0, "bla"));
    assertEquals(someObject, access.classAccessor.newInstance(0, "bla"));
    assertEquals(someObject, access.classAccessor.newInstance(0, "bla"));
    assertEquals(someObject, access.classAccessor.newInstance(0, "bla"));
  }

  public void testHasPrivateConstructor() {
    ConstructorAccess<HasPrivateConstructor> access =
        ConstructorAccess.get(HasPrivateConstructor.class);
    HasPrivateConstructor someObject = new HasPrivateConstructor();
    assertEquals(someObject, access.newInstance());
    assertEquals(someObject, access.newInstance());
    assertEquals(someObject, access.newInstance());
  }

  public void testHasProtectedConstructor() {
    if (java17) return;
    try {
      ConstructorAccess<HasProtectedConstructor> access =
          ConstructorAccess.get(HasProtectedConstructor.class);
      HasProtectedConstructor newInstance = access.newInstance();
      assertEquals("cow", newInstance.getMoo());
    } catch (Throwable t) {
      t.printStackTrace();
      assertTrue(false);
    }
  }

  public void testHasPackagePrivateConstructor() {
    if (java17) return;
    try {
      ConstructorAccess<HasPackagePrivateConstructor> access =
          ConstructorAccess.get(HasPackagePrivateConstructor.class);
      HasPackagePrivateConstructor newInstance = access.newInstance();
      assertEquals("cow", newInstance.getMoo());
    } catch (Throwable t) {
      t.printStackTrace();
      fail();
    }
  }

  public void testHasPublicConstructor() {
    try {
      ConstructorAccess<HasPublicConstructor> access =
          ConstructorAccess.get(HasPublicConstructor.class);
      HasPublicConstructor newInstance = access.newInstance();
      assertEquals("cow", newInstance.getMoo());
    } catch (Throwable t) {
      t.printStackTrace();
      fail();
    }
  }

  static class PackagePrivateClass {
    public String name;
    public int intValue;
    protected float test1;
    Float test2;
    private String test3;

    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      PackagePrivateClass other = (PackagePrivateClass) obj;
      if (intValue != other.intValue) return false;
      if (name == null) {
        if (other.name != null) return false;
      } else if (!name.equals(other.name)) return false;
      if (Float.floatToIntBits(test1) != Float.floatToIntBits(other.test1)) return false;
      if (test2 == null) {
        if (other.test2 != null) return false;
      } else if (!test2.equals(other.test2)) return false;
      if (test3 == null) {
        if (other.test3 != null) return false;
      } else if (!test3.equals(other.test3)) return false;
      return true;
    }
  }

  static public class SomeClass {
    public String name;
    public int intValue;
    protected float test1;
    Float test2;
    private String test3;

    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      SomeClass other = (SomeClass) obj;
      if (intValue != other.intValue) return false;
      if (name == null) {
        if (other.name != null) return false;
      } else if (!name.equals(other.name)) return false;
      if (Float.floatToIntBits(test1) != Float.floatToIntBits(other.test1)) return false;
      if (test2 == null) {
        if (other.test2 != null) return false;
      } else if (!test2.equals(other.test2)) return false;
      if (test3 == null) {
        if (other.test3 != null) return false;
      } else if (!test3.equals(other.test3)) return false;
      return true;
    }
  }

  static public class HasArgumentConstructor {
    public String moo;

    public HasArgumentConstructor(String moo) {
      this.moo = moo;
    }

    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      HasArgumentConstructor other = (HasArgumentConstructor) obj;
      if (moo == null) {
        if (other.moo != null) return false;
      } else if (!moo.equals(other.moo)) return false;
      return true;
    }

    public String getMoo() {
      return moo;
    }
  }

  static public class HasPrivateConstructor extends HasArgumentConstructor {
    private HasPrivateConstructor() {
      super("cow");
    }
  }

  static public class HasProtectedConstructor extends HasPrivateConstructor {
    @SuppressWarnings("synthetic-access")
    protected HasProtectedConstructor() {
      super();
    }
  }

  static public class HasPackagePrivateConstructor extends HasProtectedConstructor {
    HasPackagePrivateConstructor() {
      super();
    }
  }

  static public class HasPublicConstructor extends HasPackagePrivateConstructor {
    public HasPublicConstructor() {
      super();
    }
  }
}
