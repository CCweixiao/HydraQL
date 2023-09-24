package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.constants.HBaseConfigKeys;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.type.ColumnType;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

/**
 * @author leojie 2023/9/20 21:54
 */
public class CreateVirtualTableVisitor extends BaseVisitor<HBaseTableSchema> {
    public CreateVirtualTableVisitor() {
        super(null);
    }

    @Override
    public HBaseTableSchema visitCreate_virtual_table_command(HydraQLParser.Create_virtual_table_commandContext ctx) {
        String tableName = new TableNameVisitor().extractTableName(ctx.table_ref());
        HBaseTableSchema.Builder tableSchemaBuilder = HBaseTableSchema.of(tableName);

        HydraQLParser.Column_def_listContext columnDefListContext = ctx.column_def_list();
        HBaseColumn row;
        for (HydraQLParser.Column_defContext columnDefContext : columnDefListContext.column_def()) {
            TerminalNode primaryKeyNode = columnDefContext.PRIMARY();
            TerminalNode notNode = columnDefContext.NOT();
            TerminalNode nullNode = columnDefContext.NULL_();
            boolean filedNullAble = fieldIsNull(notNode, nullNode);
            HydraQLParser.Data_typeContext dataTypeContext = columnDefContext.data_type();
            String fieldType = extractColType(dataTypeContext);
            String familyName = extractFamily(columnDefContext.column_ref());
            String columnName = extractColName(columnDefContext.column_ref());
            if (primaryKeyNode != null) {
                if (StringUtil.isNotBlank(familyName)) {
                    throw new HBaseSqlAnalysisException("Illegal rowKey naming format " +
                            columnDefContext.column_ref().getText());
                }
                row = getRow(columnName, fieldType, filedNullAble);
                tableSchemaBuilder.addColumn(row);
                continue;
            }
            HBaseColumn column = getColumn(familyName, columnName, fieldType, filedNullAble);
            tableSchemaBuilder.addColumn(column);
        }
        HydraQLParser.Table_optionsContext tableOptionsContext = ctx.table_options();
        if (tableOptionsContext != null) {
            List<HydraQLParser.OptionContext> optionContextList = tableOptionsContext.options_().option();
            for (HydraQLParser.OptionContext optionContext : optionContextList) {
                String optionName = getText(optionContext.name());
                if (HBaseConfigKeys.HBASE_CLIENT_SCANNER_CACHING.equals(optionName)) {
                    String optionValue = extractLiteralVal(optionContext.literal());
                    tableSchemaBuilder.scanCaching(Integer.parseInt(optionValue));
                } else if (HBaseConfigKeys.HBASE_CLIENT_BLOCK_CACHE.equals(optionName)) {
                    String optionValue = extractLiteralVal(optionContext.literal());
                    tableSchemaBuilder.scanCacheBlocks(Boolean.parseBoolean(optionValue));
                } else {
                    throw new HBaseSqlAnalysisException(String.format("Unsupported table property option [%s].",
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

    private String extractColType(HydraQLParser.Data_typeContext dataTypeContext) {
        // todo 处理字段类型长度
        // HydraQLParser.Precision_intContext precisionIntContext = dataTypeContext.sql_data_type().precision_int();
        HydraQLParser.Sql_data_typeContext sqlDataTypeContext = dataTypeContext.sql_data_type();
        HydraQLParser.Hbase_data_typeContext hbaseDataTypeContext = dataTypeContext.hbase_data_type();
        if (sqlDataTypeContext.CHAR() != null) {
            return sqlDataTypeContext.CHAR().getText();
        } else if (sqlDataTypeContext.VARCHAR() != null) {
            return sqlDataTypeContext.VARCHAR().getText();
        } else if (sqlDataTypeContext.DECIMAL() != null) {
            return sqlDataTypeContext.DECIMAL().getText();
        } else if (sqlDataTypeContext.TINYINT() != null) {
            return sqlDataTypeContext.TINYINT().getText();
        } else if (sqlDataTypeContext.SMALLINT() != null) {
            return sqlDataTypeContext.SMALLINT().getText();
        } else if (sqlDataTypeContext.INTEGER() != null) {
            return sqlDataTypeContext.INTEGER().getText();
        } else if (sqlDataTypeContext.BIGINT() != null) {
            return sqlDataTypeContext.BIGINT().getText();
        } else if (sqlDataTypeContext.FLOAT() != null) {
            return sqlDataTypeContext.FLOAT().getText();
        } else if (sqlDataTypeContext.DOUBLE() != null) {
            return sqlDataTypeContext.DOUBLE().getText();
        } else if (sqlDataTypeContext.TIMESTAMP() != null) {
            return sqlDataTypeContext.TIMESTAMP().getText();
        } else if (sqlDataTypeContext.DATE() != null) {
            return sqlDataTypeContext.DATE().getText();
        }else if (sqlDataTypeContext.TIME() != null) {
            return sqlDataTypeContext.TIME().getText();
        }else if (sqlDataTypeContext.BINARY() != null) {
            return sqlDataTypeContext.BINARY().getText();
        }else if (sqlDataTypeContext.VARBINARY() != null) {
            return sqlDataTypeContext.VARBINARY().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_TIMESTAMP() != null) {
            return hbaseDataTypeContext.UNSIGNED_TIMESTAMP().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_DATE() != null) {
            return hbaseDataTypeContext.UNSIGNED_DATE().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_TIME() != null) {
            return hbaseDataTypeContext.UNSIGNED_TIME().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_TINYINT() != null) {
            return hbaseDataTypeContext.UNSIGNED_TINYINT().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_SMALLINT() != null) {
            return hbaseDataTypeContext.UNSIGNED_SMALLINT().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_INT() != null) {
            return hbaseDataTypeContext.UNSIGNED_INT().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_LONG() != null) {
            return hbaseDataTypeContext.UNSIGNED_LONG().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_FLOAT() != null) {
            return hbaseDataTypeContext.UNSIGNED_FLOAT().getText();
        } else if (hbaseDataTypeContext.UNSIGNED_DOUBLE() != null) {
            return hbaseDataTypeContext.UNSIGNED_DOUBLE().getText();
        }else {
            throw new HBaseSqlAnalysisException("Unsupported field type " + dataTypeContext.getText());
        }
    }

    public HBaseTableSchema extractHBaseTableSchema(HydraQLParser.Create_virtual_table_commandContext ctx) {
       return ctx.accept(this);
    }
}
