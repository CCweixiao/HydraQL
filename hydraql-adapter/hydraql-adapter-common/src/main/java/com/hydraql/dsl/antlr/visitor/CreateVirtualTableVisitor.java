package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.constants.HBaseConfigKeys;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.type.ColumnType;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

/**
 * @author leojie 2023/9/20 21:54
 */
public class CreateVirtualTableVisitor extends BaseVisitor<HBaseTableSchema> {
    private final String tableName;
    public CreateVirtualTableVisitor(String tableName, HBaseTableSchema tableSchema) {
        super(tableSchema);
        this.tableName = tableName;
    }

    @Override
    public HBaseTableSchema visitCreate_virtual_table_command(HydraQLParser.Create_virtual_table_commandContext ctx) {
        //TODO 处理if not exists
        boolean notExists = ctx.if_not_exists().isEmpty();
        HydraQLParser.NameContext nameContext = ctx.table_ref().table_name().name();
        HBaseTableSchema.Builder tableSchemaBuilder = HBaseTableSchema.of(tableName);

        HydraQLParser.Column_def_listContext columnDefListContext = ctx.column_def_list();
        HBaseColumn row;
        for (HydraQLParser.Column_defContext columnDefContext : columnDefListContext.column_def()) {
            TerminalNode primaryKeyNode = columnDefContext.PRIMARY();
            TerminalNode notNode = columnDefContext.NOT();
            TerminalNode nullNode = columnDefContext.NULL_();
            boolean filedNullAble = fieldIsNull(notNode, nullNode);
            HydraQLParser.Data_typeContext dataTypeContext = columnDefContext.data_type();
            String fieldType = dataTypeContext.getText();
            String familyName = getText(columnDefContext.column_ref().family_name().name());
            String columnName = getText(columnDefContext.column_ref().column_name().name());
            if (primaryKeyNode != null) {
                row = getRow(columnName, fieldType, filedNullAble);
                tableSchemaBuilder.addColumn(row);
            }
            HBaseColumn column = getColumn(familyName, columnName, fieldType, filedNullAble);
            tableSchemaBuilder.addColumn(column);
        }
        HydraQLParser.Table_optionsContext tableOptionsContext = ctx.table_options();
        if (tableOptionsContext != null) {
            List<HydraQLParser.OptionContext> optionContextList = tableOptionsContext.options_().option();
            for (HydraQLParser.OptionContext optionContext : optionContextList) {
                String optionName = getText(optionContext.name());
                //todo option value 类型处理
                if (HBaseConfigKeys.HBASE_CLIENT_SCANNER_CACHING.equals(optionName)) {
                    String optionValue = optionContext.literal().numeric().integer().getText();
                    tableSchemaBuilder.scanCaching(Integer.parseInt(optionValue));
                } else if (HBaseConfigKeys.HBASE_CLIENT_BLOCK_CACHE.equals(optionName)) {
                    String optionValue = optionContext.literal().true_false().getText();
                    tableSchemaBuilder.scanCacheBlocks(Boolean.parseBoolean(optionValue));
                } else {
                    throw new HBaseSqlAnalysisException(String.format("Configuration [%s] not currently supported.",
                            optionName));
                }
            }
        }
        return tableSchemaBuilder.build();
    }

    private boolean fieldIsNull(TerminalNode notNode, TerminalNode nullNode) {
        return notNode == null && nullNode != null;
    }

    private HBaseColumn getRow(String filedName, String fieldType, boolean nullAble) {
        if (nullAble) {
            throw new HBaseSqlAnalysisException("The rowKey field cannot be nullable.");
        }
        fieldType = fieldType.toLowerCase();
        return HBaseColumn.of("", filedName)
                .columnIsRow(true)
                .columnType(ColumnType.getColumnType(fieldType))
                .nullable(false)
                .build();
    }

    private HBaseColumn getColumn(String familyName, String columnName, String fieldType, boolean nullAble) {
        return HBaseColumn.of(familyName, columnName)
                .nullable(nullAble)
                .columnType(ColumnType.getColumnType(fieldType)).build();
    }

    public HBaseTableSchema extractHBaseTableSchema(HydraQLParser.Create_virtual_table_commandContext ctx) {
       return ctx.accept(this);
    }
}
