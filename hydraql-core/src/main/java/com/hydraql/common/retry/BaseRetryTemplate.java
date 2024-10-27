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

package com.hydraql.common.retry;

import com.hydraql.exceptions.BaseRetryTemplateHandleException;

/**
 * @author leojie 2023/7/5 16:57
 */
public class BaseRetryTemplate {
  private final int maxRetryNum;
  private final int retryInterval;

  private BaseRetryTemplate(Builder builder) {
    this.maxRetryNum = builder.maxRetryNum;
    this.retryInterval = builder.retryInterval;
  }

  public static class Builder {
    private int maxRetryNum;
    private int retryInterval;

    public Builder maxRetryNum(int maxRetryNum) {
      this.maxRetryNum = maxRetryNum;
      return this;
    }

    public Builder retryInterval(int retryInterval) {
      this.retryInterval = retryInterval;
      return this;
    }

    public BaseRetryTemplate build() {
      return new BaseRetryTemplate(this);
    }
  }

  public int getMaxRetryNum() {
    return maxRetryNum;
  }

  public int getRetryInterval() {
    return retryInterval;
  }

  public <T> T execute(RetryExecutor<T> executor) {
    T execRes = null;
    try {
      execRes = executor.execute();
    } catch (Exception e) {
      for (int i = 0; i < this.getMaxRetryNum(); i++) {
        try {
          execRes = executor.execute();
        } catch (Exception ex) {
          if (i == (this.getMaxRetryNum() - 1)) {
            throw new BaseRetryTemplateHandleException(ex);
          }
          shortSpin(this.getRetryInterval());
        }
      }
    }
    return execRes;
  }

  private void shortSpin(int interval) {
    if (interval <= 0) {
      return;
    }
    try {
      Thread.sleep(interval);
    } catch (InterruptedException e) {
    }
  }
}
