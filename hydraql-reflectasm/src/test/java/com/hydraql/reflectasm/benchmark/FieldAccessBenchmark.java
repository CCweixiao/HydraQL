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

package com.hydraql.reflectasm.benchmark;

import com.hydraql.reflectasm.FieldAccess;

import java.lang.reflect.Field;

public class FieldAccessBenchmark extends Benchmark {
  public FieldAccessBenchmark() throws Exception {
    int count = 1000000;
    Object[] dontCompileMeAway = new Object[count];

    FieldAccess access = FieldAccess.get(SomeClass.class);
    SomeClass someObject = new SomeClass();
    int index = access.getIndex("name");

    Field field = SomeClass.class.getField("name");

    for (int i = 0; i < 100; i++) {
      for (int ii = 0; ii < count; ii++) {
        access.set(someObject, index, "first");
        dontCompileMeAway[ii] = access.get(someObject, index);
      }
      for (int ii = 0; ii < count; ii++) {
        field.set(someObject, "first");
        dontCompileMeAway[ii] = field.get(someObject);
      }
    }
    warmup = false;

    for (int i = 0; i < 100; i++) {
      start();
      for (int ii = 0; ii < count; ii++) {
        access.set(someObject, index, "first");
        dontCompileMeAway[ii] = access.get(someObject, index);
      }
      end("FieldAccess");
    }
    for (int i = 0; i < 100; i++) {
      start();
      for (int ii = 0; ii < count; ii++) {
        field.set(someObject, "first");
        dontCompileMeAway[ii] = field.get(someObject);
      }
      end("Reflection");
    }

    chart("Field Set/Get");
  }

  static public class SomeClass {
    public String name;
  }

  public static void main(String[] args) throws Exception {
    new FieldAccessBenchmark();
  }
}
