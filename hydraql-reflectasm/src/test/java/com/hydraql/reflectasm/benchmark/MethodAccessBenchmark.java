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

package com.hydraql.reflectasm.benchmark;

import com.hydraql.reflectasm.MethodAccess;

import java.lang.reflect.Method;

public class MethodAccessBenchmark extends Benchmark {
  public MethodAccessBenchmark() throws Exception {
    int count = 100000;
    Object[] dontCompileMeAway = new Object[count];
    Object[] args = new Object[0];

    MethodAccess access = MethodAccess.get(SomeClass.class);
    SomeClass someObject = new SomeClass();
    int index = access.getIndex("getName");

    Method method = SomeClass.class.getMethod("getName");
    // method.setAccessible(true); // Improves reflection a bit.

    for (int i = 0; i < 100; i++) {
      for (int ii = 0; ii < count; ii++)
        dontCompileMeAway[ii] = access.invoke(someObject, index, args);
      for (int ii = 0; ii < count; ii++)
        dontCompileMeAway[ii] = method.invoke(someObject, args);
    }
    warmup = false;

    for (int i = 0; i < 100; i++) {
      start();
      for (int ii = 0; ii < count; ii++)
        dontCompileMeAway[ii] = access.invoke(someObject, index, args);
      end("MethodAccess");
    }
    for (int i = 0; i < 100; i++) {
      start();
      for (int ii = 0; ii < count; ii++)
        dontCompileMeAway[ii] = method.invoke(someObject, args);
      end("Reflection");
    }

    chart("Method Call");
  }

  static public class SomeClass {
    private String name = "something";

    public String getName() {
      return name;
    }
  }

  public static void main(String[] args) throws Exception {
    new MethodAccessBenchmark();
  }
}
