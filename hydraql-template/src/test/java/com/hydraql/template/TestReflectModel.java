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

package com.hydraql.template;

import com.hydraql.core.metadata.HBaseFieldInfo;
import com.hydraql.core.metadata.HBaseTableInfo;
import com.hydraql.core.metadata.HBaseTableInfoHelper;
import com.hydraql.reflectasm.FieldAccess;
import com.hydraql.reflectasm.MethodAccess;
import com.hydraql.template.model.UserData;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author leojie@apache.org 2024/7/22 22:23
 */
public class TestReflectModel {
  @Test
  public void testFieldAccess() {
    Class<UserData> clazz = UserData.class;
    FieldAccess fieldAccess = FieldAccess.get(clazz);
    Field[] fields = fieldAccess.getFields();
    System.out.println(fields.length);
  }

  @Test
  public void testMethodAccess() {
    Class<UserData> clazz = UserData.class;
    MethodAccess methodAccess = MethodAccess.get(clazz);
    String[] methodNames = methodAccess.getMethodNames();
    System.out.println(methodNames.length);
  }

  @Test
  public void testReflectModel() throws Exception {
    UserData user = UserData.class.getDeclaredConstructor().newInstance();
    HBaseTableInfo hBaseTableMeta = HBaseTableInfoHelper.getTableInfo(UserData.class);
    List<HBaseFieldInfo> fields = hBaseTableMeta.getFields();
    HBaseFieldInfo field = fields.get(3);
    field.setValue(user, false);
    System.out.println(user);
  }
}
