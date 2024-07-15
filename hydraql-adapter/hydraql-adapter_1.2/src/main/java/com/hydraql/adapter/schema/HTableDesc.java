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

package com.hydraql.adapter.schema;

import org.apache.hadoop.hbase.HTableDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2021/6/23 9:48 下午
 */
public class HTableDesc extends BaseHTableDesc implements Comparable<HTableDesc> {

  private final BaseHTableDescriptorConverter<HTableDesc, HTableDescriptor> tableDescriptorConverter;

  private HTableDesc(BaseHTableDesc.Builder<HTableDesc> builder) {
    super(builder);
    this.tableDescriptorConverter = new HTableDescriptorConverter(this);
  }

  public static class Builder extends BaseHTableDesc.Builder<HTableDesc> {

    private static final List<String> IGNORE_VALUE_KEYS = new ArrayList<>(3);

    static {
      IGNORE_VALUE_KEYS.add("IS_META");
      IGNORE_VALUE_KEYS.add("MERGE_ENABLED");
      IGNORE_VALUE_KEYS.add("SPLIT_ENABLED");
    }

    private Builder(String name) {
      super(name);
    }

    @Override
    public boolean ignoreValue(String key) {
      boolean unsupported = super.ignoreValue(key);
      if (unsupported) {
        return true;
      }
      return IGNORE_VALUE_KEYS.contains(key);
    }

    @Override
    public HTableDesc build() {
      return new HTableDesc(this);
    }
  }

  public static Builder newBuilder(String name) {
    return new Builder(name);
  }

  public static BaseHTableDesc.Builder<HTableDesc> copyFrom(String name, HTableDesc td) {
    return new Builder(name).copyFrom(td);
  }

  public static HTableDesc createDefault(String name) {
    return newBuilder(name).build();
  }

  public HTableDescriptor convertTo() {
    return this.tableDescriptorConverter.convertTo();
  }

  public HTableDesc convertFrom(HTableDescriptor tableDescriptor) {
    return this.tableDescriptorConverter.convertFrom(tableDescriptor);
  }

  @Override
  public int compareTo(HTableDesc o) {
    return this.convertTo().compareTo(o.convertTo());
  }

  @Override
  public int hashCode() {
    return this.convertTo().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    boolean res = super.equals(obj);
    if (!res) {
      return false;
    }
    return this.convertTo().equals(((HTableDesc) obj).convertTo());
  }

  @Override
  public String toString() {
    return this.convertTo().toString();
  }
}
