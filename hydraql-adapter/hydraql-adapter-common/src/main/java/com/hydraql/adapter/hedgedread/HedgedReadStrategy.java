package com.hydraql.adapter.hedgedread;

import com.hydraql.adapter.HBaseClientConfigKeys;
import com.hydraql.adapter.WrapperBufferedMutator;
import com.hydraql.adapter.context.HTableContext;
import com.hydraql.common.callback.MutatorCallback;
import com.hydraql.common.callback.TableCallback;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.client.Table;

/**
 * @author leojie@apache.org 2024/4/8 17:29
 */
public interface HedgedReadStrategy {
    enum Level {
        /**
         * Based on time thresholds
         */
        THRESHOLD,

        /**
         * Two requests are initiated at the same time, and the first one returns a result
         */
        FIRST_ONE,

        /**
         * Multiple requests must all be processed
         */
        CONSISTENCY,

        /**
         * For multiple clusters, the request is made in hash mode
         */
        HASH,

        /**
         * Do not use hedged
         */
        NONE;
        public static HedgedReadStrategy.Level find(String strategy) {
            if (StringUtil.isBlank(strategy)) {
                return NONE;
            }
            for (Level level : Level.values()) {
                if (strategy.equalsIgnoreCase(level.name())) {
                    return level;
                }
            }
            throw new IllegalStateException(String.format("%s=%s is not supported yet",
                    HBaseClientConfigKeys.HedgedRead.STRATEGY, strategy));
        }
    }

    <T> T execute(String tableName, TableCallback<T, Table> action);

    void mutate(HTableContext tableContext, MutatorCallback<WrapperBufferedMutator> action);
}
