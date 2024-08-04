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

package com.hydraql.common.constants;

/**
 * @author leojie 2023/7/14 23:08
 */
public class HBaseConfigKeys {
  public static final String FILTER_NAMESPACE_PREFIX = "filter.namespace.prefix";
  public static final String FILTER_TABLE_NAME_PREFIX = "filter.table.name.prefix";
  public static final String KERBEROS_PROXY_USER = "kerberos.proxy.user";
  public static final String KERBEROS_PRINCIPAL = "kerberos.principal";
  public static final String KERBEROS_KEYTAB_FILE = "keytab.file";
  public static final String JAVA_SECURITY_PREFIX = "java.security";
  public static final String ZOOKEEPER_QUORUM = "hbase.zookeeper.quorum";
  public static final String LOCAL_HOST_ZOOKEEPER_QUORUM = "localhost";
  public static final String ZOOKEEPER_CLIENT_PORT = "hbase.zookeeper.property.clientPort";
  public static final String ZOOKEEPER_NODE_PARENT = "zookeeper.znode.parent";
  public static final String HBASE_DFS_ROOT_DIR = "hbase.rootdir";
  public static final String HBASE_SECURITY_AUTH = "hbase.security.authentication";
  public static final String HADOOP_SECURITY_AUTH = "hadoop.security.authentication";
  public static final String HBASE_REGION_SERVER_KERBEROS_PRINCIPAL =
      "hbase.regionserver.kerberos.principal";
  public static final String HBASE_MASTER_KERBEROS_PRINCIPAL = "hbase.master.kerberos.principal";
  public static final String KRB5_CONF_PATH = "java.security.krb5.conf";
  public static final String KRB5_REALM = "java.security.krb5.realm";
  public static final String KRB5_KDC_SERVER_ADDR = "java.security.krb5.kdc";

  public static final String HEDGED_READ_WRITE_DISABLE = "hbase.client.hedged.read.write.disable";
  public static final boolean HBASE_CLIENT_HEDGED_READ_WRITE_DISABLE = true;
  public static final String HBASE_CLIENT_HEDGED_READ_TIME_OUT = "hbase.client.hedged.read.timeout";
  public static final long HBASE_CLIENT_HEDGED_READ_TIME_OUT_DEFAULT_MS = 100L;
  public static final String HBASE_CLIENT_HEDGED_READ_POOL_SIZE =
      "hbase.client.hedged.thread.pool.size";
  public static final int HBASE_CLIENT_HEDGED_READ_POOL_DEFAULT_SIZE = 10;
  public static final String HBASE_CLIENT_SCANNER_CACHE = "hbase.client.scanner.cache";
  public static final boolean HBASE_CLIENT_DEFAULT_SCANNER_CACHE = false;
  public static final String HBASE_CLIENT_SCANNER_CACHING = "hbase.client.scanner.caching";
  public static final int HBASE_CLIENT_DEFAULT_SCANNER_CACHING = 1000;
  public static final String HBASE_CLIENT_SCANNER_BATCH = "hbase.client.scan.batch";
  public static final int HBASE_CLIENT_DEFAULT_SCANNER_BATCH = 100;
  public static final String HBASE_CLIENT_DELETE_BATCH = "hbase.client.delete.batch";
  public static final int HBASE_CLIENT_DEFAULT_DELETE_BATCH = 100;
}
