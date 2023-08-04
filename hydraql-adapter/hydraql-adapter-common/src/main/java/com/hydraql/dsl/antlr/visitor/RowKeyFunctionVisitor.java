package com.hydraql.dsl.antlr.visitor;

import com.hydraql.dsl.antlr.HBaseSQLParser;
import com.hydraql.dsl.client.rowkey.func.RowKeyFunc;
import com.hydraql.dsl.client.rowkey.func.RowKeyFunction;
import com.hydraql.dsl.model.HBaseTableSchema;

/**
 * @author leojie 2020/11/28 11:02 上午
 */
public class RowKeyFunctionVisitor extends BaseVisitor<RowKeyFunc<?>> {

    public RowKeyFunctionVisitor(HBaseTableSchema tableSchema) {
        super(tableSchema);
    }

    @Override
    public RowKeyFunc<?> visitRowkey_FuncConstant(HBaseSQLParser.Rowkey_FuncConstantContext ctx) {
        String funcName = ctx.funcname().getText();
        return RowKeyFunction.findRowKeyFunc(funcName).getRowKeyFunc();
    }

    @Override
    public RowKeyFunc<?> visitRowkey_Wrapper(HBaseSQLParser.Rowkey_WrapperContext ctx) {
        return ctx.rowKeyExp().accept(this);
    }
}
