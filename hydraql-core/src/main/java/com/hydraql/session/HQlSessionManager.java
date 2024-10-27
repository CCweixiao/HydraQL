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

package com.hydraql.session;

import com.hydraql.HQLTable;
import com.hydraql.exceptions.HydraQlException;
import com.hydraql.reflectasm.util.ExceptionUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author leojie@apache.org 2024/9/7 22:43
 */
public class HQlSessionManager implements HQlSessionFactory, HQlSession {
  private final HQlSessionFactory hqlSessionFactory;
  private final HQlSession hqlSessionProxy;

  private final ThreadLocal<HQlSession> localHQlSession = new ThreadLocal<>();

  private HQlSessionManager(HQlSessionFactory hqlSessionFactory) {
    this.hqlSessionFactory = hqlSessionFactory;
    this.hqlSessionProxy =
        (HQlSession) Proxy.newProxyInstance(HQlSessionFactory.class.getClassLoader(),
          new Class[] { HQlSession.class }, new HQlSessionInterceptor());
  }

  public static HQlSessionManager newInstance(HQLTable table) {
    return new HQlSessionManager(new DefaultHQlSessionFactory(table));
  }

  public void startManagedSession() {
    this.localHQlSession.set(openSession());
  }

  @Override
  public <E> E get(Get get) {
    return hqlSessionProxy.get(get);
  }

  @Override
  public <E> List<E> gets(List<Get> gets) {
    return hqlSessionProxy.gets(gets);
  }

  @Override
  public void put(Put put) {
    hqlSessionProxy.put(put);
  }

  @Override
  public void puts(List<Put> puts) {
    hqlSessionProxy.puts(puts);
  }

  @Override
  public void delete(Delete delete) {
    hqlSessionProxy.delete(delete);
  }

  @Override
  public void deletes(List<Delete> deletes) {
    hqlSessionProxy.deletes(deletes);
  }

  @Override
  public <E> void upsert(E t) {
    hqlSessionProxy.upsert(t);
  }

  @Override
  public <E> void upsets(List<E> list) {
    hqlSessionProxy.upsets(list);
  }

  @Override
  public HQlSession openSession() {
    return hqlSessionFactory.openSession();
  }

  @Override
  public void close() {
    final HQlSession hydraQlSession = localHQlSession.get();
    if (null == hydraQlSession) {
      throw new HydraQlException("Error:  Cannot close.  No managed hydraql session is started.");
    }
    try {
      hydraQlSession.close();
    } finally {
      localHQlSession.remove();
    }
  }

  private class HQlSessionInterceptor implements InvocationHandler {
    public HQlSessionInterceptor() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      final HQlSession hydraQlSession = HQlSessionManager.this.localHQlSession.get();
      if (hydraQlSession != null) {
        try {
          return method.invoke(hydraQlSession, args);
        } catch (Throwable t) {
          throw ExceptionUtil.unwrapThrowable(t);
        }
      }
      HQlSession autoSqlSession = openSession();
      try {
        return method.invoke(autoSqlSession, args);
      } catch (Throwable t) {
        throw ExceptionUtil.unwrapThrowable(t);
      }
    }
  }
}
