package com.hydraql.console;

import com.hydraql.common.exception.HBaseShellSessionEnvInitException;
import com.hydraql.common.util.StringUtil;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leojie 2023/7/30 10:48
 */
public class HClusterContext {
    private final ConcurrentMap<String, Properties> clusterConf;
    private String currentSelectedCluster;
    private final Lock lock = new ReentrantLock();

    public void setCurrentSelectedCluster(String clusterName) {
        lock.lock();
        try {
            this.currentSelectedCluster = clusterName;
        } finally {
            lock.unlock();
        }
    }

    public String getCurrentSelectedCluster() {
        return this.currentSelectedCluster;
    }

    private HClusterContext() {
        clusterConf = new ConcurrentHashMap<>();
    }
    public static class HClusterContextHolder {
        public static final HClusterContext INSTANCE = new HClusterContext();
    }

    public static HClusterContext getInstance() {
        return HClusterContextHolder.INSTANCE;
    }

    public Properties getCurrentClusterProperties() {
        if (StringUtil.isBlank(getCurrentSelectedCluster())) {
            throw new HBaseShellSessionEnvInitException("Please switch cluster first.");
        }
        return getClusterProperties(getCurrentSelectedCluster());
    }

    private Properties getClusterProperties(String clusterName) {
        lock.lock();
        try {
            Properties p = clusterConf.get(clusterName);
            if (p != null) {
                return p;
            }
            p = readConf(clusterName);
            clusterConf.put(clusterName, p);
            return readConf(clusterName);
        } finally {
            lock.unlock();
        }
    }

    private Properties readConf(String clusterName) {
        Properties p = new Properties();
        String clusterConfDirPath = getClusterConfigDirPath();
        File clusterConfFile = new File(clusterConfDirPath.concat(File.separator).concat(clusterName)
                .concat(".properties"));
        if (!clusterConfFile.exists()) {
            throw new HBaseShellSessionEnvInitException(String.format("The cluster %s not exists.", clusterName));
        }
        try (FileInputStream out = new FileInputStream(clusterConfFile)) {
            List<String> lines = IOUtils.readLines(out, Charset.defaultCharset());
            for (String line : lines) {
                String[] kv = line.split("=");
                p.setProperty(kv[0], kv[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public File getClusterConfDirFile() {
        String clusterConfDirPath = getClusterConfigDirPath();
        File clusterConfDirFile = new File(clusterConfDirPath);
        if (!clusterConfDirFile.exists()) {
            clusterConfDirFile.mkdirs();
        }
        if (!clusterConfDirFile.isDirectory()) {
            throw new HBaseShellSessionEnvInitException("Please ensure that the default storage path of the cluster" +
                    " configuration file is a folder.");
        }
        return clusterConfDirFile;
    }

    private String getClusterConfigDirPath() {
        File userDirFile = new File(System.getProperty("user.dir"));
        return userDirFile.getAbsolutePath().concat(File.separator).concat("cluster_info_conf");
    }
}
