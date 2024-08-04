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

package com.hydraql.adapter.schema;

import com.hydraql.common.lang.Converter;

import java.util.Objects;

/**
 * @author leojie 2023/5/17 22:39
 */
public abstract class BaseColumnFamilyDescriptorConverter<CF extends BaseColumnFamilyDesc, CD>
    extends Converter<CF, CD> {
  private final CF columnFamilyDesc;

  public BaseColumnFamilyDescriptorConverter(CF columnFamilyDesc) {
    this.columnFamilyDesc = columnFamilyDesc;
  }

  protected CD convertTo() {
    return this.convert(this.columnFamilyDesc);
  }

  protected CF convertFrom(CD columnFamilyDescriptor) {
    return this.reverse().convert(columnFamilyDescriptor);
  }

  protected boolean compareNeedSet(Object source, Object target) {
    if (target == null) {
      return false;
    }
    return !Objects.equals(source, target);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    BaseColumnFamilyDescriptorConverter<?, ?> that = (BaseColumnFamilyDescriptorConverter<?, ?>) o;
    return columnFamilyDesc.equals(that.columnFamilyDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(columnFamilyDesc);
  }
}
