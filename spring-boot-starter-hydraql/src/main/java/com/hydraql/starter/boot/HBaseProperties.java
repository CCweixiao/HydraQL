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

package com.hydraql.starter.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * HBase的连接配置
 * </p>
 * @author leo.jie (leojie1314@gmail.com)
 */
@ConfigurationProperties(prefix = "spring.datasource.hbase")
public class HBaseProperties {
  private String zkQuorum = "localhost";
  private String zkClientPort = "2181";
  private String zkNodeParent = "/hbase";
  private String dfsRootDir = "/hbase";
  private String securityAuthWay = "simple";
  private String kerberosPrincipal = "";
  private String keytabFilePath = "";
  private String kerberosProxyUser = "";
  private String rsKerberosPrincipal = "";
  private String masterKerberosPrincipal = "";
  private String krb5ConfPath = "";
  private String krb5Realm = "";
  private String krb5KdcServerAddr = "";
  private String threadPoolName = "HBase-DataSource";
  private String clientProperties = "";
  private int corePoolSize = 2;
  private int maximumPoolSize = 4;
  private long keepAliveTime = 60000;

  public String getZkQuorum() {
    return zkQuorum;
  }

  public void setZkQuorum(String zkQuorum) {
    this.zkQuorum = zkQuorum;
  }

  public String getZkClientPort() {
    return zkClientPort;
  }

  public void setZkClientPort(String zkClientPort) {
    this.zkClientPort = zkClientPort;
  }

  public String getDfsRootDir() {
    return dfsRootDir;
  }

  public void setDfsRootDir(String dfsRootDir) {
    this.dfsRootDir = dfsRootDir;
  }

  public String getZkNodeParent() {
    return zkNodeParent;
  }

  public void setZkNodeParent(String zkNodeParent) {
    this.zkNodeParent = zkNodeParent;
  }

  public String getSecurityAuthWay() {
    return securityAuthWay;
  }

  public void setSecurityAuthWay(String securityAuthWay) {
    this.securityAuthWay = securityAuthWay;
  }

  public String getKerberosPrincipal() {
    return kerberosPrincipal;
  }

  public void setKerberosPrincipal(String kerberosPrincipal) {
    this.kerberosPrincipal = kerberosPrincipal;
  }

  public String getKeytabFilePath() {
    return keytabFilePath;
  }

  public void setKeytabFilePath(String keytabFilePath) {
    this.keytabFilePath = keytabFilePath;
  }

  public String getKerberosProxyUser() {
    return kerberosProxyUser;
  }

  public void setKerberosProxyUser(String kerberosProxyUser) {
    this.kerberosProxyUser = kerberosProxyUser;
  }

  public String getRsKerberosPrincipal() {
    return rsKerberosPrincipal;
  }

  public void setRsKerberosPrincipal(String rsKerberosPrincipal) {
    this.rsKerberosPrincipal = rsKerberosPrincipal;
  }

  public String getMasterKerberosPrincipal() {
    return masterKerberosPrincipal;
  }

  public void setMasterKerberosPrincipal(String masterKerberosPrincipal) {
    this.masterKerberosPrincipal = masterKerberosPrincipal;
  }

  public String getKrb5ConfPath() {
    return krb5ConfPath;
  }

  public void setKrb5ConfPath(String krb5ConfPath) {
    this.krb5ConfPath = krb5ConfPath;
  }

  public String getKrb5Realm() {
    return krb5Realm;
  }

  public void setKrb5Realm(String krb5Realm) {
    this.krb5Realm = krb5Realm;
  }

  public String getKrb5KdcServerAddr() {
    return krb5KdcServerAddr;
  }

  public void setKrb5KdcServerAddr(String krb5KdcServerAddr) {
    this.krb5KdcServerAddr = krb5KdcServerAddr;
  }

  public String getThreadPoolName() {
    return threadPoolName;
  }

  public void setThreadPoolName(String threadPoolName) {
    this.threadPoolName = threadPoolName;
  }

  public String getClientProperties() {
    return clientProperties;
  }

  public void setClientProperties(String clientProperties) {
    this.clientProperties = clientProperties;
  }

  public int getCorePoolSize() {
    return corePoolSize;
  }

  public void setCorePoolSize(int corePoolSize) {
    this.corePoolSize = corePoolSize;
  }

  public int getMaximumPoolSize() {
    return maximumPoolSize;
  }

  public void setMaximumPoolSize(int maximumPoolSize) {
    this.maximumPoolSize = maximumPoolSize;
  }

  public long getKeepAliveTime() {
    return keepAliveTime;
  }

  public void setKeepAliveTime(long keepAliveTime) {
    this.keepAliveTime = keepAliveTime;
  }
}
