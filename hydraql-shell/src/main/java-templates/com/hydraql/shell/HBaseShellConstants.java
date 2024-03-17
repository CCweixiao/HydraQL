package com.hydraql.shell;

/**
 * HBaseShellConstants from compilation time by maven.
 */
public final class HBaseShellConstants {
    /* Hadoop version, specified in maven property. **/
    public static final String HBASE_ADAPTER_VERSION = "${hydraql.hbase.adapter.version}";

    private HBaseShellConstants() {} // prevent instantiation
}