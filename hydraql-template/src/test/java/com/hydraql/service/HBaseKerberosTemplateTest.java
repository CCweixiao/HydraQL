package com.hydraql.service;

import com.hydraql.template.BaseHBaseAdminTemplate;
import com.hydraql.template.HBaseAdminTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * @author leojie 2022/12/9 22:40
 */
public class HBaseKerberosTemplateTest {
    private BaseHBaseAdminTemplate adminTemplate;

    @Before
    public void init() {
        Properties properties = new Properties();
        properties.put("hbase.zookeeper.quorum", "myhbase");
        properties.put("hbase.zookeeper.property.clientPort", "2181");
        properties.put("hbase.security.authentication", "kerberos");
        // 下面配置是kerberos认证方式所需
        properties.put("kerberos.principal", "hbase@HADOOP.LEO.COM");
        properties.put("keytab.file", "/etc/hbase/conf/hbase.keytab");
        properties.put("hbase.regionserver.kerberos.principal", "hbase/_HOST@HADOOP.LEO.COM");
        properties.put("hbase.master.kerberos.principal", "hbase/_HOST@HADOOP.LEO.COM");
        // 指定kdc服务相关的配置方式有如下两种：
        // 方式一：指定krb5.conf路径
        properties.put("java.security.krb5.conf", "/etc/krb5.conf");
        // 方式二：指定java.security.krb5.realm和java.security.krb5.kdc
        properties.put("java.security.krb5.realm", "HADOOP.LEO.COM");
        properties.put("java.security.krb5.kdc", "你自己的kdc服务地址");
        // 一些额外的客户端参数
        properties.put("hbase.client.retries.number", "3");
        adminTemplate = HBaseAdminTemplate.of(properties);
    }

    @Test
    public void Con() {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "myhbase");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.security.authentication", "kerberos");
        // 下面配置是kerberos认证方式所需
        configuration.set("kerberos.principal", "hbase@HADOOP.LEO.COM");
        configuration.set("keytab.file", "/etc/hbase/conf/hbase.keytab");
        // 设置kerberos用户代理，需保证hbase有代理普通用户的权限
        configuration.set("kerberos.proxy.user", "proxy_user");
        configuration.set("hbase.regionserver.kerberos.principal", "hbase/_HOST@HADOOP.LEO.COM");
        configuration.set("hbase.master.kerberos.principal", "hbase/_HOST@HADOOP.LEO.COM");
        // 指定kdc服务相关的配置方式有如下两种：
        // 方式一：指定krb5.conf路径
        configuration.set("java.security.krb5.conf", "/etc/krb5.conf");
        // 方式二：指定java.security.krb5.realm和java.security.krb5.kdc
        configuration.set("java.security.krb5.realm", "HADOOP.LEO.COM");
        configuration.set("java.security.krb5.kdc", "你自己的kdc服务地址");
        // 一些额外的客户端参数
        configuration.set("hbase.client.retries.number", "3");
        // HBase客户端的hedged read功能
        configuration.set("hbase.client.hedged.read.open", "true");
        // 主集群读超时触发hedged.read的阈值
        configuration.set("hbase.client.hedged.read.timeout", "100");
        // 可以与rpc handler count一致
        configuration.set("hbase.client.hedged.thread.pool.size", "100");
        // 设置hedged.read集群的配置，其他配置，默认复用主集群的配置
        configuration.set("hbase.zookeeper.quorum.hedged.read", "myhbase");
        configuration.set("hbase.zookeeper.property.clientPort.hedged.read", "2181");
    }


    @Test
    public void getTest() {
        System.out.println(adminTemplate.listTableNames());
    }
}
