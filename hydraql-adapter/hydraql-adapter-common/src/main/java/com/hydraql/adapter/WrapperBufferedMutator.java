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

package com.hydraql.adapter;

import com.hydraql.adapter.context.HTableContext;
import org.apache.hadoop.hbase.client.Mutation;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author leojie 2024/4/7 15:12
 */
public interface WrapperBufferedMutator extends Closeable {
  long MIN_WRITE_BUFFER_PERIODIC_FLUSH_TIMER_TICK_MS = 100;

  default void mutate(Mutation mutation) throws IOException {
    mutate(Collections.singletonList(mutation));
  }

  void mutate(List<? extends Mutation> mutations) throws IOException;

  void flush() throws IOException;

  default void setWriteBufferPeriodicFlush(long timeoutMs, long timerTickMs) {
    throw new UnsupportedOperationException(
        "The BufferedMutator::setWriteBufferPeriodicFlush has not been implemented");
  }

  default void disableWriteBufferPeriodicFlush() {
    setWriteBufferPeriodicFlush(0, MIN_WRITE_BUFFER_PERIODIC_FLUSH_TIMER_TICK_MS);
  }

  HTableContext getTableContext();

  default boolean autoFlush() {
    return getTableContext().isAutoFlush();
  }
}
