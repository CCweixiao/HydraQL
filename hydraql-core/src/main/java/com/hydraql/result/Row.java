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

package com.hydraql.result;

import org.apache.hadoop.hbase.util.Bytes;

import java.io.Serializable;

/**
 * @author leojie@apache.org 2024/12/7 18:54
 */
public class Row implements Serializable {
  private static final long serialVersionUID = 4741043194136389238L;

  private final byte[] row;

  private Row(byte[] row) {
    this.row = row;
  }

  public static Row of(byte[] row) {
    return new Row(row);
  }

  public byte[] getRow() {
    return row.clone();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return Bytes.equals(this.row, ((Row) o).row);
  }

  @Override
  public int hashCode() {
    return Bytes.hashCode(this.row);
  }
}
