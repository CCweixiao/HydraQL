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

package com.hydraql.adapter.context;

import org.apache.hadoop.hbase.client.BufferedMutator;

/**
 * @author leojie 2024/4/7 14:09
 */
@Deprecated
public class BatchSaveOptions {
  private static final int UNSET = -1;
  private final int maxKeyValueSize;
  private final long writeBufferSize;
  private final long writeBufferPeriodicFlushTimerTickMs;
  private final long writeBufferPeriodicFlushTimeoutMs;
  private final boolean autoFlush;
  private final BufferedMutator.ExceptionListener exceptionListener;

  public BatchSaveOptions(Builder builder) {
    this.maxKeyValueSize = builder.maxKeyValueSize;
    this.writeBufferSize = builder.writeBufferSize;
    this.writeBufferPeriodicFlushTimerTickMs = builder.writeBufferPeriodicFlushTimerTickMs;
    this.writeBufferPeriodicFlushTimeoutMs = builder.writeBufferPeriodicFlushTimeoutMs;
    this.autoFlush = builder.autoFlush;
    this.exceptionListener = builder.exceptionListener;
  }

  public static class Builder {
    private int maxKeyValueSize = UNSET;
    private long writeBufferSize = UNSET;
    private long writeBufferPeriodicFlushTimerTickMs = UNSET;
    private long writeBufferPeriodicFlushTimeoutMs = UNSET;
    private boolean autoFlush;
    private BufferedMutator.ExceptionListener exceptionListener;

    public Builder maxKeyValueSize(int maxKeyValueSize) {
      this.maxKeyValueSize = maxKeyValueSize;
      return this;
    }

    public Builder writeBufferSize(long writeBufferSize) {
      this.writeBufferSize = writeBufferSize;
      return this;
    }

    public Builder writeBufferPeriodicFlushTimerTickMs(long writeBufferPeriodicFlushTimerTickMs) {
      this.writeBufferPeriodicFlushTimerTickMs = writeBufferPeriodicFlushTimerTickMs;
      return this;
    }

    public Builder writeBufferPeriodicFlushTimeoutMs(long writeBufferPeriodicFlushTimeoutMs) {
      this.writeBufferPeriodicFlushTimeoutMs = writeBufferPeriodicFlushTimeoutMs;
      return this;
    }

    public Builder autoFlush(boolean autoFlush) {
      this.autoFlush = autoFlush;
      return this;
    }

    public Builder exceptionListener(BufferedMutator.ExceptionListener exceptionListener) {
      this.exceptionListener = exceptionListener;
      return this;
    }

    public BatchSaveOptions build() {
      return new BatchSaveOptions(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  int getMaxKeyValueSize() {
    return maxKeyValueSize;
  }

  long getWriteBufferSize() {
    return writeBufferSize;
  }

  long getWriteBufferPeriodicFlushTimerTickMs() {
    return writeBufferPeriodicFlushTimerTickMs;
  }

  long getWriteBufferPeriodicFlushTimeoutMs() {
    return writeBufferPeriodicFlushTimeoutMs;
  }

  boolean isAutoFlush() {
    return autoFlush;
  }

  BufferedMutator.ExceptionListener getExceptionListener() {
    return exceptionListener;
  }
}
