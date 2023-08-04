package com.hydraql.template;

import com.hydraql.HBaseSqlAdapter;
import com.hydraql.adapter.IHBaseSqlAdapter;
import com.hydraql.common.model.HQLType;
import com.hydraql.common.model.row.HBaseDataSet;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.apache.hadoop.conf.Configuration;
import java.util.Map;
import java.util.Properties;

/**
 * @author leojie 2022/11/27 17:14
 */
public class HBaseSqlTemplate implements BaseHBaseSqlTemplate {
    private final Configuration configuration;
    private final IHBaseSqlAdapter sqlAdapter;

    private HBaseSqlTemplate(Builder builder) {
        this.configuration = builder.configuration;
        this.sqlAdapter = new HBaseSqlAdapter(this.configuration);
    }

    @Override
    public void dropVirtualTable(String hql) {
        sqlAdapter.dropVirtualTable(hql);
    }

    @Override
    public void createVirtualTable(String hql) {
        sqlAdapter.createVirtualTable(hql);
    }

    @Override
    public HBaseDataSet select(String hql) {
        return sqlAdapter.select(hql);
    }

    @Override
    public HBaseDataSet select(String hql, Map<String, Object> params) {
        return sqlAdapter.select(hql, params);
    }

    @Override
    public void insert(String hql) {
        sqlAdapter.insert(hql);
    }

    @Override
    public void delete(String hql) {
        sqlAdapter.delete(hql);
    }

    @Override
    public String parseTableNameFromHql(String hql) {
        return sqlAdapter.parseTableNameFromHql(hql);
    }

    @Override
    public HQLType parseHQLType(String hql) {
        return sqlAdapter.parseHQLType(hql);
    }

    @Override
    public void registerTableSchema(HBaseTableSchema tableSchema) {
        sqlAdapter.registerTableSchema(tableSchema);
    }

    @Override
    public void printTableSchema(String tableName) {
        sqlAdapter.printTableSchema(tableName);
    }

    static class Builder extends BaseTemplateBuilder<HBaseSqlTemplate> {
        @Override
        HBaseSqlTemplate build() {
            return new HBaseSqlTemplate(this);
        }
    }

    public static HBaseSqlTemplate of(Configuration configuration) {
        return new Builder().configuration(configuration).build();
    }

    public static HBaseSqlTemplate of(Properties properties) {
        return new Builder().configuration(properties).build();
    }

    public static HBaseSqlTemplate of(String zkQuorum, String zkClientPort) {
        return new Builder().configuration(zkQuorum, zkClientPort).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
