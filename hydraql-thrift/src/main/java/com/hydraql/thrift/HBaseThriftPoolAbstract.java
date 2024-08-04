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

package com.hydraql.thrift;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author leojie 2020/12/27 2:48 下午
 */
public class HBaseThriftPoolAbstract extends Pool<HBaseThrift> {
  public HBaseThriftPoolAbstract() {
    super();
  }

  public HBaseThriftPoolAbstract(GenericObjectPoolConfig poolConfig,
      PooledObjectFactory<HBaseThrift> factory) {
    super(poolConfig, factory);
  }

  @Override
  protected void returnBrokenResource(HBaseThrift resource) {
    super.returnBrokenResource(resource);
  }

  @Override
  protected void returnResource(HBaseThrift resource) {
    super.returnResource(resource);
  }
}
