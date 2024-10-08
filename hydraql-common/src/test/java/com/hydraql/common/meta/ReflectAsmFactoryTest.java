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

package com.hydraql.common.meta;

import com.hydraql.reflectasm.FieldAccess;
import com.hydraql.reflectasm.MethodAccess;
import com.hydraql.common.meta.model.CityModel;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author leojie 2022/11/20 11:23
 */
public class ReflectAsmFactoryTest {
  @Test
  public void testGetMethods() {
    String[] methodNames = MethodAccess.get(CityModel.class).getMethodNames();
    System.out.println(methodNames);
  }

  @Test
  public void testGetFields() {
    Field[] fieldNames = FieldAccess.get(CityModel.class).getFields();
    System.out.println(fieldNames);
  }
}
