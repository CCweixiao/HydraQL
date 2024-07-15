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

import com.hydraql.common.constants.HBaseConfigKeys;
import com.hydraql.common.security.AuthType;
import com.hydraql.common.util.StringUtil;
import com.hydraql.template.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * HBaseTemplate自动配置
 * </p>
 * @author leo.jie (leojie1314@gmail.com)
 */
@Configuration
@EnableConfigurationProperties(HBaseProperties.class)
@ConditionalOnClass({ BaseHBaseAdminTemplate.class, BaseHBaseSqlTemplate.class,
    BaseHBaseTableTemplate.class })
public class HBaseAutoConfiguration {

  private final HBaseProperties properties;

  public HBaseAutoConfiguration(HBaseProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnMissingBean(BaseHBaseAdminTemplate.class)
  public BaseHBaseAdminTemplate hbaseAdminTemplate() {
    return HBaseAdminTemplate.of(createHBaseProperties());
  }

  @Bean
  @ConditionalOnMissingBean(BaseHBaseTableTemplate.class)
  public BaseHBaseTableTemplate hbaseTableTemplate() {
    return HBaseTableTemplate.of(createHBaseProperties());
  }

  @Bean
  @ConditionalOnMissingBean(BaseHBaseSqlTemplate.class)
  public BaseHBaseSqlTemplate hbaseSqlTemplate() {
    return HBaseSqlTemplate.of(createHBaseProperties());
  }

  private Properties createHBaseProperties() {
    Properties properties = new Properties();
    if (StringUtil.isBlank(this.properties.getZkQuorum())) {
      properties.setProperty(HBaseConfigKeys.ZOOKEEPER_QUORUM,
        HBaseConfigKeys.LOCAL_HOST_ZOOKEEPER_QUORUM);
    } else {
      properties.setProperty(HBaseConfigKeys.ZOOKEEPER_QUORUM, this.properties.getZkQuorum());
    }
    if (StringUtil.isNotBlank(this.properties.getZkClientPort())) {
      properties.setProperty(HBaseConfigKeys.ZOOKEEPER_CLIENT_PORT,
        this.properties.getZkClientPort());
    }
    if (StringUtil.isNotBlank(this.properties.getDfsRootDir())) {
      properties.setProperty(HBaseConfigKeys.HBASE_DFS_ROOT_DIR, this.properties.getDfsRootDir());
    }
    if (StringUtil.isNotBlank(this.properties.getZkNodeParent())) {
      properties.setProperty(HBaseConfigKeys.ZOOKEEPER_NODE_PARENT,
        this.properties.getZkNodeParent());
    }
    AuthType defaultAuthType = AuthType.findAuthType(this.properties.getSecurityAuthWay());
    properties.setProperty(HBaseConfigKeys.HBASE_SECURITY_AUTH, defaultAuthType.getAuthType());

    if (defaultAuthType == AuthType.KERBEROS) {
      if (StringUtil.isBlank(this.properties.getKerberosPrincipal())) {
        throw new IllegalArgumentException("The client kerberos principal must not be empty.");
      }
      properties.setProperty(HBaseConfigKeys.KERBEROS_PRINCIPAL,
        this.properties.getKerberosPrincipal());

      if (StringUtil.isBlank(this.properties.getKeytabFilePath())) {
        throw new IllegalArgumentException(
            "The client kerberos keytab file path must not be empty.");
      }
      properties.setProperty(HBaseConfigKeys.KERBEROS_KEYTAB_FILE,
        this.properties.getKeytabFilePath());
      properties.setProperty(HBaseConfigKeys.KERBEROS_PROXY_USER,
        this.properties.getKerberosProxyUser());

      if (StringUtil.isBlank(this.properties.getRsKerberosPrincipal())) {
        throw new IllegalArgumentException(
            "The region server kerberos principal must not be empty.");
      }
      properties.setProperty(HBaseConfigKeys.HBASE_REGION_SERVER_KERBEROS_PRINCIPAL,
        this.properties.getRsKerberosPrincipal());

      if (StringUtil.isBlank(this.properties.getMasterKerberosPrincipal())) {
        throw new IllegalArgumentException(
            "The master server kerberos principal must not be empty.");
      }
      properties.setProperty(HBaseConfigKeys.HBASE_MASTER_KERBEROS_PRINCIPAL,
        this.properties.getMasterKerberosPrincipal());

      if (StringUtil.isNotBlank(this.properties.getKrb5ConfPath())) {
        properties.setProperty(HBaseConfigKeys.KRB5_CONF_PATH, this.properties.getKrb5ConfPath());
      } else {
        properties.setProperty(HBaseConfigKeys.KRB5_REALM, this.properties.getKrb5Realm());
        properties.setProperty(HBaseConfigKeys.KRB5_KDC_SERVER_ADDR,
          this.properties.getKrb5KdcServerAddr());
      }
    }

    Map<String, String> otherProperties =
        StringUtil.parsePropertyToMapFromStr(this.properties.getClientProperties());
    if (!otherProperties.isEmpty()) {
      otherProperties.forEach(properties::setProperty);
    }
    return properties;
  }

}
