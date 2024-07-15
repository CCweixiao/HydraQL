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

package com.hydraql.common.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * The HBase connection thread pool is initialized.
 * </p>
 * @author leo.jie (leojie1314@gmail.com)
 */
@Deprecated
public class HBaseConnThreadPoolInit {
  private String threadPoolName;
  private int corePoolSize;
  private int maximumPoolSize;
  private long keepAliveTime;

  public HBaseConnThreadPoolInit() {
  }

  public HBaseConnThreadPoolInit(String threadPoolName, int corePoolSize, int maximumPoolSize,
      long keepAliveTime) {
    this.threadPoolName = threadPoolName;
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.keepAliveTime = keepAliveTime;
  }

  public int getCorePoolSize() {
    if (this.corePoolSize <= 0) {
      return 1;
    }
    return corePoolSize;
  }

  public void setCorePoolSize(int corePoolSize) {
    this.corePoolSize = corePoolSize;
  }

  public int getMaximumPoolSize() {
    if (maximumPoolSize <= 0) {
      return Integer.MAX_VALUE;
    }
    return maximumPoolSize;
  }

  public void setMaximumPoolSize(int maximumPoolSize) {
    this.maximumPoolSize = maximumPoolSize;
  }

  public long getKeepAliveTime() {
    if (keepAliveTime <= 0) {
      return 60;
    }
    return keepAliveTime;
  }

  public void setKeepAliveTime(long keepAliveTime) {
    this.keepAliveTime = keepAliveTime;
  }

  public String getThreadPoolName() {
    return threadPoolName;
  }

  public void setThreadPoolName(String threadPoolName) {
    this.threadPoolName = threadPoolName;
  }

  public ThreadFactory hBasePoolThreadFactory() {
    return new DefaultThreadFactory();
  }

  public BlockingQueue<Runnable> getWorkQueue() {
    if (maximumPoolSize <= 0) {
      return new SynchronousQueue<>();
    } else {
      return new ArrayBlockingQueue<>(Math.max(maximumPoolSize, 2000));
    }
  }

  class DefaultThreadFactory implements ThreadFactory {
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DefaultThreadFactory() {
      SecurityManager s = System.getSecurityManager();
      group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
      AtomicInteger poolNumber = new AtomicInteger(1);
      if (threadPoolName != null && !threadPoolName.trim().equals("")) {
        namePrefix = "pool-[" + threadPoolName + "]" + poolNumber.getAndIncrement() + "-thread-";
      } else {
        namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
      }
    }

    public Thread newThread(Runnable runnable) {
      Thread t = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);
      if (t.isDaemon()) t.setDaemon(false);
      if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
      return t;
    }
  }

}
