package com.hydraql.shell;

import com.hydraql.common.exception.HBaseShellSessionEnvInitException;
import com.hydraql.common.util.StringUtil;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.ScriptingContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author leojie 2023/7/4 19:59
 */
public class HBaseShellSession implements ShellSession {
    private static final Logger LOGGER = LoggerFactory.getLogger(HBaseShellSession.class);
    private static final String SESSION_PROP_SEPARATOR = "$";

    private final String sessionId;
    private final int sessionInitMaxTimes;
    private final long sessionInitRetryInterval;
    private final long sessionInitTimeout;
    private final long sessionIdle;
    private final long sessionInitStartTime;
    private final boolean sessionDebugLog;
    private final Properties properties;

    private ScriptingContainer scriptingContainer;
    private StringWriter writer;
    private boolean isConnected;

    public HBaseShellSession(Builder builder) {
        this.properties = builder.properties;
        this.sessionId = builder.sessionId;
        this.sessionInitMaxTimes = builder.sessionInitMaxTimes;
        this.sessionInitRetryInterval = builder.sessionInitRetryInterval;
        this.sessionInitTimeout = builder.sessionInitTimeout;
        this.sessionIdle = builder.sessionIdle;
        this.sessionInitStartTime = System.currentTimeMillis();
        this.sessionDebugLog = builder.sessionDebugLog;
    }

    static class Builder {
        private String sessionId;
        private Properties properties;
        private int sessionInitMaxTimes;
        private long sessionInitRetryInterval;
        private long sessionInitTimeout;
        private long sessionIdle;
        private boolean sessionDebugLog;

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder properties(Properties properties) {
            this.properties = properties;
            return this;
        }

        public Builder sessionInitMaxTimes(int sessionInitMaxTimes) {
            this.sessionInitMaxTimes = sessionInitMaxTimes;
            return this;
        }

        public Builder sessionInitRetryInterval(long sessionInitRetryInterval) {
            this.sessionInitRetryInterval = sessionInitRetryInterval;
            return this;
        }

        public Builder sessionInitTimeout(long sessionInitTimeout) {
            this.sessionInitTimeout = sessionInitTimeout;
            return this;
        }

        public Builder sessionIdle(long sessionIdle) {
            this.sessionIdle = sessionIdle;
            return this;
        }

        public Builder sessionDebugLog(boolean sessionDebugLog) {
            this.sessionDebugLog = sessionDebugLog;
            return this;
        }

        public Builder properties(String key, String value) {
            if (this.properties == null) {
                this.properties = new Properties();
            }
            this.properties.setProperty(key, value);
            return this;
        }

        public HBaseShellSession build() {
            return new HBaseShellSession(this);
        }
    }

    public static HBaseShellSession.Builder sessionBuilder() {
        return new HBaseShellSession.Builder();
    }

    @Override
    public void open() {
        Thread t = new Thread(() -> {
            int initMaxTimes = this.getSessionInitMaxTimes();

            try {
                LOGGER.info("Starting create hbase shell session ......");
                createShellRunningEnv();
            } catch (Exception e) {
                for (int i = 0; i < initMaxTimes; i++) {
                    try {
                        createShellRunningEnv();
                    } catch (Exception ex) {
                        if (i == (initMaxTimes - 1)) {
                            LOGGER.error("After {} retries, HBase shell session initialization failed.", initMaxTimes, ex);
                            throw new HBaseShellSessionEnvInitException(ex);
                        }
                        shortSpin(this.getSessionInitRetryInterval());
                    }
                }
            }
        });
        t.setName("HBaseShellRunningEnvInitThread");
        t.setDaemon(true);
        t.start();
        shortSpin(10000);

        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(this::waitShellSessionConnected);
        try {
            this.isConnected = future.get(this.getSessionInitTimeout(), TimeUnit.MILLISECONDS);
            LOGGER.info("Created hbase shell session successfully.");
        } catch (InterruptedException | ExecutionException e) {
            this.isConnected = false;
            future.cancel(true);
            LOGGER.error("Initialize hbase shell session failed.", e);
            this.destroy();
        } catch (TimeoutException e) {
            LOGGER.error("Initialize hbase shell session timeout.", e);
            this.isConnected = false;
            future.cancel(true);
            this.destroy();
        }
    }

    private void shortSpin(long interval) {
        if (interval <= 0) {
            return;
        }
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            LOGGER.warn("Ignore error.", e);
        }
    }

    private void createShellRunningEnv() throws IOException {
        this.scriptingContainer = new ScriptingContainer(LocalContextScope.SINGLETHREAD);
        this.writer = new StringWriter();
        scriptingContainer.setOutput(this.writer);
        Properties sysProps = System.getProperties();
        String prop = "";
        if (this.isSessionDebugLog()) {
            prop = "-d".concat(SESSION_PROP_SEPARATOR);
        }
        if (properties != null && !properties.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String key : properties.stringPropertyNames()) {
                sb.append("-D");
                sb.append(key);
                sb.append("=");
                sb.append(properties.getProperty(key));
                sb.append(SESSION_PROP_SEPARATOR);
            }
            prop = prop + sb.substring(0, sb.length() - 1);
        }
        if (StringUtil.isNotBlank(prop)) {
            sysProps.setProperty("hbase.ruby.args", prop);
        }
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("hbase-ruby/hirb.rb")) {
            this.scriptingContainer.runScriptlet(in, "hirb.rb");
        }
    }

    private boolean waitShellSessionConnected() {
        while (true) {
            Result result = executeCmd("list_namespace");
            String r = result.getResult();
            if (result.isSuccess() && StringUtil.isNotBlank(r)) {
                return true;
            }
            shortSpin(200L);
        }
    }

    @Override
    public Result execute(String cmd) {
        if (!this.isConnected()) {
            return Result.failed(String.format("The current session [%s] is not connected successfully," +
                    " please try again.", this));
        }
        return executeCmd(cmd);
    }

    @Override
    public void destroy() {
        if (this.scriptingContainer != null) {
            this.scriptingContainer.terminate();
        }
        this.setConnected(false);
        LOGGER.info("The hbase shell session destroy successfully.");
    }

    private Result executeCmd(String cmd) {
        try {
            this.writer.getBuffer().setLength(0);
            Object o = this.scriptingContainer.runScriptlet(cmd);
            this.writer.flush();
            String res = writer.toString();
            if (StringUtil.isBlank(res) && o != null) {
                res = o.toString();
            }
            return Result.ok(res);
        } catch (Exception e) {
            return Result.failed(getStackTrace(e));
        }
    }

    public String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public String getSessionId() {
        return sessionId;
    }

    public Properties getProperties() {
        return properties;
    }

    public int getSessionInitMaxTimes() {
        return sessionInitMaxTimes;
    }

    public long getSessionInitRetryInterval() {
        return sessionInitRetryInterval;
    }

    public long getSessionInitTimeout() {
        return sessionInitTimeout;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public long getSessionIdle() {
        return sessionIdle;
    }

    public long getSessionInitStartTime() {
        return sessionInitStartTime;
    }

    public boolean isSessionDebugLog() {
        return sessionDebugLog;
    }

    @Override
    public String toString() {
        return this.getSessionId();
    }


}
