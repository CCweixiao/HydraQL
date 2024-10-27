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

package com.hydraql.conf;

import org.apache.hadoop.hbase.client.BufferedMutator;

/**
 * @author leojie 2024/4/7 14:09
 */
public class BufferedMutatorOptions {
  private static final int UNSET = -1;
  private final int maxKeyValueSize;
  private final long writeBufferSize;
  private final long writeBufferPeriodicFlushTimerTickMs;
  private final long writeBufferPeriodicFlushTimeoutMs;
  private final boolean autoFlush;
  private final BufferedMutator.ExceptionListener exceptionListener;

  private BufferedMutatorOptions(Builder builder) {
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

    private Builder() {

    }

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

    public BufferedMutatorOptions build() {
      return new BufferedMutatorOptions(this);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public int getMaxKeyValueSize() {
    return maxKeyValueSize;
  }

  public long getWriteBufferSize() {
    return writeBufferSize;
  }

  public long getWriteBufferPeriodicFlushTimerTickMs() {
    return writeBufferPeriodicFlushTimerTickMs;
  }

  public long getWriteBufferPeriodicFlushTimeoutMs() {
    return writeBufferPeriodicFlushTimeoutMs;
  }

  public boolean isAutoFlush() {
    return autoFlush;
  }

  public BufferedMutator.ExceptionListener getExceptionListener() {
    return exceptionListener;
  }
}
