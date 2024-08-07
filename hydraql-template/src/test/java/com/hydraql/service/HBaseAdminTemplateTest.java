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

package com.hydraql.service;

import com.hydraql.common.model.NamespaceDesc;
import com.hydraql.adapter.schema.ColumnFamilyDesc;
import com.hydraql.adapter.schema.HTableDesc;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author leojie 2022/11/4 20:52
 */
public class HBaseAdminTemplateTest extends AbstractHBaseTemplateTest {

  @Before
  public void init() {
    super.initIHBaseAdminTemplate();
  }

  @Test
  public void testListTableNames() {
    List<String> tableNames = adminTemplate.listTableNames();
    System.out.println(tableNames);
  }

  @Test
  public void testListTableDescList() {
    System.out.println(adminTemplate.listTableDesc());
  }

  @Test
  public void createNameSpace() {
    NamespaceDesc namespaceDesc = new NamespaceDesc();
    namespaceDesc.setNamespaceName("test_nn");
    namespaceDesc.addNamespaceProp("createdTime", String.valueOf(System.currentTimeMillis()));
    namespaceDesc.addNamespaceProp("createdBy", "leo_jie");
    adminTemplate.createNamespaceAsync(namespaceDesc);
  }

  @Test
  public void testCreateTable() {
    ColumnFamilyDesc f1 = ColumnFamilyDesc.newBuilder("f1").build();
    ColumnFamilyDesc f2 =
        ColumnFamilyDesc.newBuilder("f2").setTimeToLive(3600).setMaxVersions(3).build();
    HTableDesc tableDesc =
        HTableDesc.newBuilder("leo_test_22222").addFamilyDesc(f1).addFamilyDesc(f2).build();
    adminTemplate.createTable(tableDesc);
  }

}
