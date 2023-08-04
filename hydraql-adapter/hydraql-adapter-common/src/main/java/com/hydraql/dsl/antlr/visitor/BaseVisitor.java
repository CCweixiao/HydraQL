package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.exception.HBaseSqlColValueAnalysisException;
import com.hydraql.common.exception.HBaseSqlTableColumnsMissingException;
import com.hydraql.common.exception.HBaseSqlTableSchemaMissingException;
import com.hydraql.common.lang.MyAssert;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.data.TimeStampRange;
import com.hydraql.dsl.antlr.HBaseSQLBaseVisitor;
import com.hydraql.dsl.antlr.HBaseSQLParser;
import com.hydraql.dsl.client.QueryExtInfo;
import com.hydraql.dsl.client.rowkey.BytesRowKey;
import com.hydraql.dsl.client.rowkey.RowKey;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2022/12/3 10:30
 */
public abstract class BaseVisitor<T> extends HBaseSQLBaseVisitor<T> {
    protected final HBaseTableSchema tableSchema;

    public BaseVisitor(HBaseTableSchema tableSchema) {
        this.tableSchema = tableSchema;
    }

    public HBaseTableSchema getTableSchema() {
        if (this.tableSchema == null) {
            throw new HBaseSqlTableSchemaMissingException("Please register the table schema first.");
        }
        return tableSchema;
    }

    public Map<String, HBaseColumn> getAllColumns() {
        Map<String, HBaseColumn> schemaMap = this.getTableSchema().getColumnSchemaMap();
        if (schemaMap == null || schemaMap.isEmpty()) {
            throw new HBaseSqlTableColumnsMissingException("No column added for registered schema.");
        }
        return schemaMap;
    }

    public HBaseColumn findRow() {
        return this.getTableSchema().findRow();
    }

    public RowKey<byte[]> startRow() {
        return new BytesRowKey(new byte[0]);
    }

    public RowKey<byte[]> endRow() {
        return new BytesRowKey(new byte[0]);
    }

    protected String extractValueFromValueContext(HBaseSQLParser.ValueContext valueContext) {
        if (valueContext == null) {
            return null;
        }

        if (valueContext.NULL() != null) {
            List<TerminalNode> nodeList = valueContext.NULL();
            if (!nodeList.isEmpty()) {
                return null;
            }
        }
        if (valueContext.ID() != null) {
            List<TerminalNode> nodes = valueContext.ID();
            if (!nodes.isEmpty()) {
                return nodes.get(0).getText();
            }
        }

        if (valueContext.ROWKEY() != null) {
            List<TerminalNode> rowNodes = valueContext.ROWKEY();
            if (!rowNodes.isEmpty()) {
                return rowNodes.get(0).getText();
            }
        }

        if (valueContext.STRING() != null) {
            List<TerminalNode> nodes = valueContext.STRING();
            if (!nodes.isEmpty()) {
                String val = nodes.get(0).getText();
                if (StringUtil.isBlank(val)) {
                    return "";
                }
                return val.substring(1, val.length() - 1);
            }
        }
        return null;
    }

    protected List<HBaseColumn> extractColumns(HBaseSQLParser.ColumnListContext colListContext) {
        List<HBaseColumn> columnList = new ArrayList<>();
        for (HBaseSQLParser.ColumnContext colContext : colListContext.column()) {
            columnList.add(extractColumn(colContext));
        }
        return columnList;
    }

    protected HBaseColumn extractColumn(HBaseSQLParser.ColumnContext colContext) {
        String col = colContext.ID().getText();
        String[] colArr = col.split(HMHBaseConstants.FAMILY_QUALIFIER_SEPARATOR);
        if (colArr.length == 1) {
            return this.getTableSchema().findColumn(colArr[0]);
        } else if (colArr.length == 2) {
            return this.getTableSchema().findColumn(colArr[0], colArr[1]);
        } else {
            throw new HBaseSqlAnalysisException("Can not get family and column from the col:" + col);
        }
    }

    protected Object extractConstantVal(HBaseColumn column, HBaseSQLParser.ConstantContext constantContext) {
        MyAssert.checkNotNull(column);
        MyAssert.checkNotNull(constantContext);
        String constantOriVal = this.extractValueFromValueContext(constantContext.value());
        if (constantOriVal == null) {
            throw new HBaseSqlColValueAnalysisException(String.format("The value of a field [%s] filter cannot be null.",
                    column.getColumnName()));
        }
        return column.getColumnType().getTypeHandler().extractMatchTtypeValue(constantOriVal);
    }

    protected List<Object> extractConstantValList(HBaseColumn column,
                                                  List<HBaseSQLParser.ConstantContext> constantContextList) {
        List<Object> valList = new ArrayList<>();
        for (HBaseSQLParser.ConstantContext constantContext : constantContextList) {
            valList.add(extractConstantVal(column, constantContext));
        }
        return valList;
    }

    protected List<Object> extractParamValList(List<HBaseSQLParser.VarContext> varContextList,
                                               Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            throw new HBaseSqlAnalysisException("The parameter list cannot be empty.");
        }
        List<Object> valList = new ArrayList<>();
        for (HBaseSQLParser.VarContext varContext : varContextList) {
            valList.add(extractParamVal(varContext, params));
        }
        return valList;
    }

    protected Object extractParamVal(HBaseSQLParser.VarContext varContext, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            throw new HBaseSqlAnalysisException("The parameter list cannot be empty.");
        }
        String paramName = varContext.ID().getText();
        if (StringUtil.isBlank(paramName)) {
            throw new HBaseSqlAnalysisException("The parameter name cannot be empty.");
        }
        Object paramVal = params.get(paramName);
        if (paramVal == null) {
            throw new HBaseSqlAnalysisException("No value is specified for parameter " + paramName);
        }
        return paramVal;
    }

    protected TimeStampRange extractTimeStampRange(HBaseTableSchema tableSchema, HBaseSQLParser.TsRangeContext tsRangeContext) {
        TimeStampRangeVisitor visitor = new TimeStampRangeVisitor(tableSchema);
        return visitor.parseTimeStampRange(tsRangeContext);
    }

    public QueryExtInfo parseQueryExtInfo(HBaseSQLParser.SelectStatementContext selectStatementContext) {
        QueryExtInfo queryExtInfo = new QueryExtInfo();

        if (selectStatementContext.multiVersionExp() != null) {
            // 解析最大版本号
            HBaseSQLParser.MaxVersionExpContext maxVersionExpContext = selectStatementContext.multiVersionExp().maxVersionExp();
            if (maxVersionExpContext != null) {
                int maxVersion;
                try {
                    maxVersion = Integer.parseInt(maxVersionExpContext.integer().ID().getText());
                } catch (NumberFormatException e) {
                    throw new HBaseSqlAnalysisException("The value of max version must be one integer number.");
                }
                queryExtInfo.setMaxVersions(maxVersion);
            }
            // 解析起止时间戳范围
            HBaseSQLParser.TsRangeContext tsRangeContext = selectStatementContext.multiVersionExp().tsRange();
            if (tsRangeContext != null) {
                TimeStampRange timeStampRange = extractTimeStampRange(this.getTableSchema(), tsRangeContext);
                queryExtInfo.setTimeRange(timeStampRange.getStart(), timeStampRange.getEnd());
            }
        }

        // 解析limit
        HBaseSQLParser.LimitExpContext limitExpContext = selectStatementContext.limitExp();
        if (limitExpContext != null) {
            int limit;
            try {
                limit = Integer.parseInt(limitExpContext.integer().ID().getText());
            } catch (NumberFormatException e) {
                throw new HBaseSqlAnalysisException("The value of limit must be a number.");
            }
            if (limit <= 0) {
                throw new HBaseSqlAnalysisException("The value of limit must be a positive number.");
            }
            queryExtInfo.setLimit(limit);
        }
        return queryExtInfo;
    }

    public long extractTimeStamp(HBaseSQLParser.TsExpContext tsExpContext) {
        String ts = tsExpContext.timestamp().getText();
        if (StringUtil.isBlank(ts)) {
            throw new HBaseSqlAnalysisException("The value of timestamp must not be empty.");
        }
        String error = String.format("The timestamp %s is not a standard timestamp format, " +
                "please enter a 13-bit Unix timestamp.", ts);
        if (ts.length() != 13) {
            throw new HBaseSqlColValueAnalysisException(error);
        }
        try {
            return Long.parseLong(ts);
        } catch (NumberFormatException e) {
            throw new HBaseSqlColValueAnalysisException(error);
        }
    }
}
