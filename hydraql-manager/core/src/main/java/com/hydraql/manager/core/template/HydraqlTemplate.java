package com.hydraql.manager.core.template;

import com.hydraql.manager.core.conf.HydraqlHBaseConfiguration;
import com.hydraql.manager.core.model.HBaseRowData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2024/1/25 16:10
 */
public interface HydraqlTemplate {

    class Factory {
        private static final Logger LOG = LoggerFactory.getLogger(Factory.class);

        private Factory() {}

        public static HydraqlTemplate create(HydraqlHBaseConfiguration conf) {
            List<HydraqlTemplateFactory> factories = HydraqlTemplateFactoryRegistry.findAll(conf);
            if (factories.isEmpty()) {
                throw new IllegalArgumentException("No Hydraql Template Factory found.");
            }
            List<Throwable> errors = new ArrayList<>();
            for (HydraqlTemplateFactory factory : factories) {
                ClassLoader previousClassLoader = Thread.currentThread().getContextClassLoader();
                try {
                    Thread.currentThread().setContextClassLoader(factory.getClass().getClassLoader());
                    HydraqlTemplate hydraqlTemplate = new HydraqlTemplateWithLogging(factory.create(conf));
                    LOG.info("HydraqlTemplate created with factory {}", factory.getClass().getSimpleName());
                    return hydraqlTemplate;
                } catch (Throwable e) {
                    errors.add(e);
                    LOG.warn(String.format("Failed to create HydraqlTemplate by factory %s: %s",
                            factory.getClass().getSimpleName(), e));
                } finally {
                    Thread.currentThread().setContextClassLoader(previousClassLoader);
                }
            }
            IllegalArgumentException e = new IllegalArgumentException(
                    "Unable to create an UnderFileSystem instance for path");
            for (Throwable t : errors) {
                e.addSuppressed(t);
            }
            throw e;
        }
    }

    List<String> listTableNames();

    HBaseRowData getRow(String tableName, String rowKey);
}
