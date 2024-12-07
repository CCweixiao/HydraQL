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

package com.hydraql.generator;

import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leojie@apache.org 2024/12/6 23:12
 */
public class RowKeyHashingGeneratorTest {
  @Test
  public void applyAndRecoverRow() {
    RowKeyGenerationStrategy generator = RowKeyGenerationStrategy.HASHING;
    String originalRow = "123456";
    byte[] finalRow = generator.apply(originalRow);
    Assert.assertEquals("e10adc39|123456", Bytes.toString(finalRow));
    String recoverRow = generator.recover(finalRow).toString();
    Assert.assertEquals(originalRow, recoverRow);
  }

  @Test
  public void applyAndRecoverRow2() {
    RowKeyGenerationStrategy generator = RowKeyGenerationStrategy.HASHING;
    String originalRow = "123456";
    String finalRow = "e10adc9|123456|dsds";
    String recoverRow = generator.recover(Bytes.toBytes(finalRow)).toString();
    Assert.assertNotEquals(originalRow, recoverRow);
    Assert.assertEquals(recoverRow, finalRow);
  }
}
