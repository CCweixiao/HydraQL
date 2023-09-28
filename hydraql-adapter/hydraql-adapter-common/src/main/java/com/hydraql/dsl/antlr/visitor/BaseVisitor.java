package com.hydraql.dsl.antlr.visitor;

import com.hydraql.common.constants.HMHBaseConstants;
import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.exception.HBaseSqlColValueAnalysisException;
import com.hydraql.common.exception.HBaseSqlTableSchemaMissingException;
import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.HydraQLParserBaseVisitor;
import com.hydraql.dsl.antlr.data.TimeStampRange;
import com.hydraql.dsl.client.QueryExtInfo;
import com.hydraql.dsl.model.HBaseColumn;
import com.hydraql.dsl.model.HBaseTableSchema;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leojie 2022/12/3 10:30
 */
public abstract class BaseVisitor<T> extends HydraQLParserBaseVisitor<T> {
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

    public HBaseColumn findRow() {
        return this.getTableSchema().findRow();
    }

    protected String extractFamily(HydraQLParser.Column_refContext columnRefContext) {
        HydraQLParser.Family_nameContext familyNameContext = columnRefContext.family_name();
        if (familyNameContext == null) {
            return "";
        }
        return getText(familyNameContext.name());
    }

    protected String extractColName(HydraQLParser.Column_refContext columnRefContext) {
        HydraQLParser.Column_nameContext columnNameContext = columnRefContext.column_name();
        if (columnNameContext == null) {
            return "";
        }
        return getText(columnNameContext.name());
    }

    protected HBaseColumn extractColumn(HydraQLParser.Family_nameContext familyNameContext,
                                        HydraQLParser.Column_nameContext columnNameContext) {
        String family = "";
        String column = "";
        if (familyNameContext != null) {
            family = getText(familyNameContext.name());
        }
        if (columnNameContext != null) {
            column = getText(columnNameContext.name());
        }
        return this.getTableSchema().findColumn(family, column);

    }

    protected HBaseColumn extractColumn(HydraQLParser.ColumnContext columnContext) {
        HydraQLParser.Family_nameContext familyNameContext = columnContext.family_name();
        HydraQLParser.Column_nameContext columnNameContext = columnContext.column_name();
        return this.extractColumn(familyNameContext,  columnNameContext);
    }

    protected Object extractConditionVal(HydraQLParser.ConditionValContext conditionValContext,
                                         HBaseColumn column, Map<String, Object> params, boolean nullAble) {
        HydraQLParser.VarContext varContext = conditionValContext.var();
        Object val;
        if (varContext != null && !varContext.isEmpty()) {
            val = extractParamVal(varContext, params);
        } else {
            HydraQLParser.ConstantContext constantContext = conditionValContext.constant();
            val = extractConstantVal(column, constantContext);
        }
        if (!nullAble && val == null) {
            throw new HBaseSqlAnalysisException(String.format("The value of filter field %s cannot be null.",
                    HMHBaseConstants.getColumnName(column.getFamily(), column.getColumnName())));
        }
        return val;
    }

    private Object extractConstantVal(HBaseColumn column, HydraQLParser.ConstantContext constantContext) {
        String constantOriVal = extractLiteralVal(constantContext.literal());
        if (constantOriVal == null) {
            return null;
        }
        return column.getColumnType().getTypeHandler().extractMatchTtypeValue(constantOriVal);
    }

    private Object extractParamVal(HydraQLParser.VarContext varContext, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            throw new HBaseSqlAnalysisException("The parameter list cannot be empty.");
        }
        String paramName = varContext.variable().varString().getText();
        if (StringUtil.isBlank(paramName)) {
            throw new HBaseSqlAnalysisException("The parameter name cannot be empty.");
        }
        Object paramVal = params.get(paramName);
        if (paramVal == null) {
            throw new HBaseSqlAnalysisException("No value is specified for parameter " + paramName);
        }
        return paramVal;
    }

    protected String extractRowKeyVal(HydraQLParser.RowKeyContext rowKeyContext) {
        HydraQLParser.NumericContext numericContext = rowKeyContext.numeric();
        if (numericContext != null) {
            return extractNumberVal(numericContext);
        }
        String text = rowKeyContext.string().STRING_LITERAL().getText();
        return text.substring(1, text.length() -1 );
    }

    protected String extractLiteralVal(HydraQLParser.LiteralContext literalContext) {

        if (literalContext.NULL_() != null) {
            return null;
        }
        HydraQLParser.NumericContext numericContext = literalContext.numeric();
        if (numericContext != null) {
            return extractNumberVal(numericContext);
        }
        HydraQLParser.True_falseContext trueFalseContext = literalContext.true_false();
        if (trueFalseContext != null) {
            return trueFalseContext.getText();
        }
        String text = literalContext.string().STRING_LITERAL().getText();
        return text.substring(1, text.length() -1 );
    }

    private String extractNumberVal(HydraQLParser.NumericContext numericContext) {
        HydraQLParser.IntegerContext integerContext = numericContext.integer();
        if (integerContext != null) {
            return integerContext.DECIMAL_LITERAL().getText();
        }
        HydraQLParser.DecimalContext decimalContext = numericContext.decimal();
        if (decimalContext != null) {
            return decimalContext.FLOAT_LITERAL().getText();
        }
        throw new HBaseSqlAnalysisException("Invalid number str " + numericContext.getText());
    }

    protected List<Object> extractConstantValList(HydraQLParser.ConditionValListContext conditionValListContext,
                                                  HBaseColumn column,
                                                  Map<String, Object> params) {
        List<HydraQLParser.ConditionValContext> conditionValContextList =
                conditionValListContext.conditionVal();
        List<Object> valList = new ArrayList<>(conditionValContextList.size());
        for (HydraQLParser.ConditionValContext conditionValContext : conditionValContextList) {
            valList.add(extractConditionVal(conditionValContext, column, params, true));
        }
        return valList;
    }

    protected TimeStampRange extractTimeStampRange(HBaseTableSchema tableSchema,
                                                   HydraQLParser.Timestamp_range_clauseContext tsRangeContext) {
        TimeStampRangeVisitor visitor = new TimeStampRangeVisitor(tableSchema);
        return visitor.extractTimeStampRange(tsRangeContext);
    }

    public QueryExtInfo parseQueryExtInfo(HydraQLParser.Select_commandContext selectCommandContext) {
        QueryExtInfo queryExtInfo = new QueryExtInfo();
        HydraQLParser.Versions_clauseContext versionsClauseContext = selectCommandContext.versions_clause();
        if (versionsClauseContext != null) {
            String val = versionsClauseContext.integer().DECIMAL_LITERAL().getText();
            int maxVersion = Integer.parseInt(val);
            if (maxVersion <= 0) {
                throw new HBaseSqlAnalysisException("The value of max version must be bigger than zero.");
            }
            queryExtInfo.setMaxVersions(maxVersion);
        }

        HydraQLParser.Timestamp_range_clauseContext timestampRangeClauseContext =
                selectCommandContext.timestamp_range_clause();

        if (timestampRangeClauseContext != null) {
            TimeStampRange timeStampRange = extractTimeStampRange(this.getTableSchema(), timestampRangeClauseContext);
            queryExtInfo.setTimeRange(timeStampRange.getStart(), timeStampRange.getEnd());
        }

        // 解析limit
        HydraQLParser.Limit_clauseContext limitClauseContext = selectCommandContext.limit_clause();
        if (limitClauseContext != null) {
            int limit;
            try {
                limit = Integer.parseInt(limitClauseContext.integer().DECIMAL_LITERAL().getText());
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

    public long extractTimeStamp(HydraQLParser.TsExpContext tsExpContext) {
        String ts = tsExpContext.timestamp().integer().getText();
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

    protected String getText(HydraQLParser.NameContext nameContext) {
        if (nameContext == null || nameContext.isEmpty()) {
            return "";
        }
        TerminalNode id = nameContext.ID();
        if (id != null) {
            return id.getText();
        }
        String text = getText(nameContext.quoted_name());
        if (StringUtil.isNotBlank(text)) {
            return text;
        }
        return "";
    }

    private String getText(HydraQLParser.Quoted_nameContext quotedNameContext) {
        if (quotedNameContext != null && !quotedNameContext.isEmpty()) {
            String text = quotedNameContext.DOUBLE_QUOTE_ID().getText();
            return text.substring(1, text.length() - 1);
        }
        return "";
    }
}
