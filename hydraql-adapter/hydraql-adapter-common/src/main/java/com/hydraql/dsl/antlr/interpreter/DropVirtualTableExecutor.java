package com.hydraql.dsl.antlr.interpreter;

import com.hydraql.adapter.AbstractHBaseSqlAdapter;
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
    private final AbstractHBaseSqlAdapter sqlAdapter;

    private DropVirtualTableExecutor(ExecutorBuilder builder) {
        super(builder.hql);
        this.sqlAdapter = builder.sqlAdapter;
    }

    @Override
    public void execute() {
        this.checkParsedHqlTypeMatched(this.getHqlType());
        HydraQLParser.Drop_table_commandContext dropTableCommandContext =
                this.getSqlCommandContext().ddl_command().drop_table_command();

        String virtualTableName = new TableNameVisitor().extractTableName(dropTableCommandContext.table_ref());
        Get get = new Get(Bytes.toBytes(virtualTableName));
        boolean virtualTableExists = sqlAdapter.execute(
                AbstractHBaseSqlAdapter.HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            Result result = table.get(get);
            if (result == null) {
                return false;
            }
            byte[] value = result.getValue(AbstractHBaseSqlAdapter.HQL_META_DATA_TABLE_FAMILY,
                    AbstractHBaseSqlAdapter.HQL_META_DATA_TABLE_QUALIFIER);
            return value != null && StringUtil.isNotBlank(Bytes.toString(value));
        });
        HydraQLParser.If_existsContext ifExistsContext = dropTableCommandContext.if_exists();
        if (!virtualTableExists && ifExistsContext == null) {
            throw new HBaseSqlAnalysisException(String.format("The virtual table %s does not exist.", virtualTableName));
        }
        Delete delete = new Delete(Bytes.toBytes(virtualTableName));
        boolean deleteRes = sqlAdapter.execute(
                AbstractHBaseSqlAdapter.HQL_META_DATA_TABLE_NAME.getNameAsString(), table -> {
            table.delete(delete);
            return true;
        });
        if (!deleteRes) {
            throw new HBaseSqlAnalysisException(String.format("The virtual table %s failed to be deleted.", virtualTableName));
        }
        removeTableSchema(virtualTableName);
        this.setResult(true);
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
        private final AbstractHBaseSqlAdapter sqlAdapter;

        private ExecutorBuilder(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
            this.hql = hql;
            this.sqlAdapter = sqlAdapter;
        }

        @Override
        public DropVirtualTableExecutor build() {
            return new DropVirtualTableExecutor(this);
        }
    }

    public static DropVirtualTableExecutor of(String hql, AbstractHBaseSqlAdapter sqlAdapter) {
        return new DropVirtualTableExecutor.ExecutorBuilder(hql, sqlAdapter).build();
    }
}
