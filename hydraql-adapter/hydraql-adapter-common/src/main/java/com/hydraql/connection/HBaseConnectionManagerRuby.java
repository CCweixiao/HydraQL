package com.hydraql.connection;

import com.hydraql.common.constants.HBaseConfigKeys;
import com.hydraql.common.exception.HydraQLConnectionException;
import com.hydraql.common.security.AuthType;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author leojie 2021/2/9 11:15 下午
 */
public class HBaseConnectionManagerRuby {
    public static Connection getConnection(Properties prop) {
        Configuration configuration = getConfiguration(prop);
        return getConnection(configuration);
    }

    public static Connection getConnection(Configuration configuration) {
        return HBaseConnectionManager.getInstance().getConnection(configuration);
    }

    public static Configuration getConfiguration(Properties properties) {
        final List<String> keys = properties.keySet().stream().map(Object::toString).collect(Collectors.toList());
        AuthType auth = getAuthType(properties.getProperty(HBaseConfigKeys.HBASE_SECURITY_AUTH, ""));
        Configuration configuration = HBaseConfiguration.create();
        switch (auth) {
            case SIMPLE:
                keys.forEach(key -> configuration.set(key, properties.getProperty(key)));
                break;
            case KERBEROS:
                keys.forEach(key -> {
                    if (key.startsWith(HBaseConfigKeys.JAVA_SECURITY_PREFIX)) {
                        System.setProperty(key, properties.getProperty(key));
                    } else {
                        configuration.set(key, properties.getProperty(key));
                    }
                });
                break;
            default:
                break;
        }
        return configuration;
    }

    public static String uniqueShellSessionConnectionId(Properties properties) {
        Configuration configuration = getConfiguration(properties);
        return HBaseConnectionUtil.generateUniqueConnectionKey(configuration);
    }

    private static AuthType getAuthType(String auth) {
        if (StringUtil.isBlank(auth)) {
            return AuthType.SIMPLE;
        }
        for (AuthType value : AuthType.values()) {
            if (auth.equalsIgnoreCase(value.getAuthType())) {
                return value;
            }
        }
        throw new HydraQLConnectionException("Auth type " + auth + " is not supported.");
    }
}

