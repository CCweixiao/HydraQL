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

package com.hydraql.thrift;

import com.hydraql.common.util.HBaseThriftProtocol;

/**
 * @author leojie 2020/12/27 11:41 下午
 */
public class HBaseThriftTemplateFactory {

  private volatile static HBaseThriftTemplate thriftTemplate;

  private HBaseThriftTemplateFactory() {

  }

  public static HBaseThriftTemplate getInstance() {
    if (null == thriftTemplate) {
      synchronized (HBaseThriftTemplateFactory.class) {
        if (null == thriftTemplate) {
          thriftTemplate = new HBaseThriftTemplate(HBaseThriftProtocol.DEFAULT_HOST,
              HBaseThriftProtocol.DEFAULT_PORT);
        }
      }
    }
    return thriftTemplate;
  }

  public static HBaseThriftTemplate getInstance(String host, int port) {
    if (null == thriftTemplate) {
      synchronized (HBaseThriftTemplateFactory.class) {
        if (null == thriftTemplate) {
          thriftTemplate = new HBaseThriftTemplate(host, port);
        }
      }
    }
    return thriftTemplate;
  }

  public static HBaseThriftTemplate getInstance(String host, int port, int poolSize) {
    if (null == thriftTemplate) {
      synchronized (HBaseThriftTemplateFactory.class) {
        if (null == thriftTemplate) {
          thriftTemplate = new HBaseThriftTemplate(host, port, poolSize);
        }
      }
    }
    return thriftTemplate;
  }

  public static HBaseThriftTemplate getInstance(String host, int port,
      HBaseThriftPoolConfig config) {
    if (null == thriftTemplate) {
      synchronized (HBaseThriftTemplateFactory.class) {
        if (null == thriftTemplate) {
          thriftTemplate = new HBaseThriftTemplate(host, port, config);
        }
      }
    }
    return thriftTemplate;
  }

}
