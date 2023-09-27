package com.hydraql.dsl.antlr.interpreter;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
import com.hydraql.common.model.HQLType;
import com.hydraql.dsl.context.HBaseSqlContext;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author leojie 2023/9/27 14:57
 */
public class ShowVirtualTablesExecutor extends BaseHqlExecutor<List<String>> implements Interpreter {
    private final AbstractHBaseSqlAdapter sqlAdapter;

    private ShowVirtualTablesExecutor(ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }

    @Override
    public List<String> execute() {
        this.checkParsedHqlTypeMatched(this.getHqlType());
        List<String> allRegisteredVirtualTables = HBaseSqlContext.getInstance().getAllRegisteredVirtualTables();
        List<String> allVirtualTables = queryAllVirtualTables();
        Set<String> tableNames = new HashSet<>(allRegisteredVirtualTables);
        tableNames.addAll(allVirtualTables);
        return new ArrayList<>(tableNames);
    }

    @Override
    public HQLType expectHqlType() {
        return HQLType.SHOW_VIRTUAL_TABLES;
    }

    private List<String> queryAllVirtualTables() {
        Scan scan = new Scan();
        return sqlAdapter.execute(AbstractHBaseSqlAdapter.
                HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            ResultScanner scanner = table.getScanner(scan);
            List<String> tables = new ArrayList<>();
            for (Result result : scanner) {
                tables.add(Bytes.toString(result.getRow()));
            }
            return tables;
        });
    }

    private static class ExecutorBuilder extends Builder<ShowVirtualTablesExecutor, List<String>> {
        private final String hql;
        private final AbstractHBaseSqlAdapter sqlAdapter;
        private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public ShowVirtualTablesExecutor build() {
            return new ShowVirtualTablesExecutor(this);
        }
    }

    public static ShowVirtualTablesExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
        return new ShowVirtualTablesExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
