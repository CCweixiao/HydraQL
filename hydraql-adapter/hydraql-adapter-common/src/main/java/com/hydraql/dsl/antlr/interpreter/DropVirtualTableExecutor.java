package com.hydraql.dsl.antlr.interpreter;

import com.hydraql.adapter.HqlAdapter;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.model.HQLType;
import com.hydraql.common.util.StringUtil;
import com.hydraql.connection.HBaseConnectionUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.visitor.TableNameVisitor;
import com.hydraql.dsl.context.HBaseSqlContext;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author leojie 2023/9/27 15:13
 */
public class DropVirtualTableExecutor extends BaseHqlExecutor<Boolean> implements Interpreter {
    private final HqlAdapter sqlAdapter;

    private DropVirtualTableExecutor(ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }

    @Override
    public Boolean execute() {
        this.checkParsedHqlTypeMatched(this.getHqlType());
        HydraQLParser.Drop_table_commandContext dropTableCommandContext =
                this.getSqlCommandContext().ddl_command().drop_table_command();

        String virtualTableName = new TableNameVisitor().extractTableName(dropTableCommandContext.table_ref());
        Get get = new Get(Bytes.toBytes(virtualTableName));
        boolean virtualTableExists = sqlAdapter.execute(
                HqlAdapter.HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            Result result = table.get(get);
            if (result == null) {
                return false;
            }
            byte[] value = result.getValue(HqlAdapter.HQL_META_DATA_TABLE_FAMILY,
                    HqlAdapter.HQL_META_DATA_TABLE_QUALIFIER);
            return value != null && StringUtil.isNotBlank(Bytes.toString(value));
        });
        HydraQLParser.If_existsContext ifExistsContext = dropTableCommandContext.if_exists();
        if (!virtualTableExists && ifExistsContext == null) {
            throw new HBaseSqlAnalysisException(String.format("The virtual table %s does not exist.", virtualTableName));
        }
        Delete delete = new Delete(Bytes.toBytes(virtualTableName));
        boolean deleteRes = sqlAdapter.execute(
                HqlAdapter.HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            table.delete(delete);
            return true;
        });
        if (!deleteRes) {
            throw new HBaseSqlAnalysisException(String.format("The virtual table %s failed to be deleted.", virtualTableName));
        }
        removeTableSchema(virtualTableName);
        return true;
    }

    @Override
    public HQLType expectHqlType() {
        return HQLType.DROP_VIRTUAL_TABLE;
    }

    private void removeTableSchema(String virtualTableName) {
        String uniqueKey = HBaseConnectionUtil.generateUniqueConnectionKey(sqlAdapter.getConfiguration());
        uniqueKey = uniqueKey + "#" + virtualTableName;
        HBaseSqlContext.getInstance().removeTableSchema(uniqueKey);
    }

    private static class ExecutorBuilder extends Builder<DropVirtualTableExecutor, Boolean> {
        private final String hql;
        private final HqlAdapter sqlAdapter;

        private ExecutorBuilder(String hql, HqlAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public DropVirtualTableExecutor build() {
            return new DropVirtualTableExecutor(this);
        }
    }

    public static DropVirtualTableExecutor of(String hql, HqlAdapter sqlAdapter) {
        return new DropVirtualTableExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
