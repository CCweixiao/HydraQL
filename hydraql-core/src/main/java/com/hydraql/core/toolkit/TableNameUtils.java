package com.hydraql.core.toolkit;

import com.hydraql.common.util.StringUtil;

/**
 * @author leojie@apache.org 2024/8/17 20:50
 */
public class TableNameUtils {
    private TableNameUtils() {}

    public static String format(String namespace, String tableName) {
        if (StringUtil.isBlank(namespace)) {
            return tableName;
        }
        if (namespace.equals(Constants.DEFAULT_NAMESPACE)) {
            return tableName;
        }
        return String.format("%s%s%s", namespace, Constants.NAMESPACE_DELIM, tableName);
    }
}
