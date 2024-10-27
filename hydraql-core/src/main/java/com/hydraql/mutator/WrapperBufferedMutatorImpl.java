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

package com.hydraql.mutator;

import com.hydraql.session.HQlConnection;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Mutation;

import java.io.IOException;
import java.util.List;

/**
 * @author leojie@apache.org 2024/4/7 22:18
 */
public class WrapperBufferedMutatorImpl implements WrapperBufferedMutator {
  private final HQlConnection hydraQlConnection;
  private BufferedMutator bufferedMutator;

  public WrapperBufferedMutatorImpl(HQlConnection hydraQlConnection,
      BufferedMutator bufferedMutator) {
    this.hydraQlConnection = hydraQlConnection;
    this.bufferedMutator = bufferedMutator;
  }

  @Override
  public void mutate(Mutation mutation) throws IOException {
    bufferedMutator.mutate(mutation);
    if (this.autoFlush()) {
      flush();
    }
  }

  @Override
  public void mutate(List<? extends Mutation> mutations) throws IOException {
    bufferedMutator.mutate(mutations);
    if (this.autoFlush()) {
      flush();
    }
  }

  @Override
  public void flush() throws IOException {
    bufferedMutator.flush();
  }

  @Override
  public boolean autoFlush() {
    return hydraQlConnection.getBufferedMutatorOptions().isAutoFlush();
  }

  @Override
  public void close() throws IOException {
    if (null != bufferedMutator) {
      bufferedMutator.close();
      bufferedMutator = null;
    }
  }
}
