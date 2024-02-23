package com.hydraql.manager.core.template;

import com.hydraql.manager.core.model.HBaseRowData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author leojie 2024/1/26 10:41
 */
public class HydraqlTemplateWithLogging implements HydraqlTemplate {
    private static final Logger LOG = LoggerFactory.getLogger(HydraqlTemplateWithLogging.class);

    private final HydraqlTemplate template;

    HydraqlTemplateWithLogging(HydraqlTemplate template) {
        this.template = template;
    }

    @Override
    public List<String> listTableNames() {
        return template.listTableNames();
    }

    @Override
    public HBaseRowData getRow(String tableName, String rowKey) {
        return template.getRow(tableName, rowKey);
    }
}
