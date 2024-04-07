package com.hydraql.connection;

import com.hydraql.common.constants.HBaseConfigKeys;
import com.hydraql.common.util.DigestUtil;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;

/**
 * @author leojie 2023/7/6 08:18
 */
public class HBaseConnectionUtil {

    public static String generateUniqueConnectionKey(Configuration configuration, String tableName) {
        return generateUniqueConnectionKey(configuration).concat("#").concat(tableName);
    }

    public static String generateUniqueConnectionKey(Configuration configuration) {
        String zkQuorum = configuration.get(HConstants.ZOOKEEPER_QUORUM);
        String zkClientPort = configuration.get(HConstants.ZOOKEEPER_CLIENT_PORT);
        String uniqueKey = generateUniqueConnectionKey(zkQuorum, zkClientPort);
        if (isProxyUserEnabled(configuration)) {
            uniqueKey = uniqueKey + "#" + proxyUser(configuration);
        }
        return uniqueKey;
    }

    private static String generateUniqueConnectionKey(String zkQuorum, String zkClientPort) {
        if (StringUtil.isBlank(zkQuorum)) {
            throw new IllegalArgumentException("hbase.zookeeper.quorum must be set.");
        }
        if (StringUtil.isBlank(zkClientPort)) {
            throw new IllegalArgumentException("hbase.zookeeper.property.clientPort must be set.");
        }
        return DigestUtil.md5Hex(zkQuorum.concat(zkClientPort));
    }

    public static boolean isProxyUserEnabled(Configuration configuration) {
        String val = proxyUser(configuration);
        return StringUtil.isNotBlank(val);
    }

    public static String proxyUser(Configuration configuration) {
        return configuration.get(HBaseConfigKeys.KERBEROS_PROXY_USER);
    }
}
