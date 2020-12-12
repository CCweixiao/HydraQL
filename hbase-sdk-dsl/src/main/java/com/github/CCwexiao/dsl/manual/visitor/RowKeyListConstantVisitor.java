package com.github.CCwexiao.dsl.manual.visitor;

import com.github.CCwexiao.dsl.auto.HBaseSQLBaseVisitor;
import com.github.CCwexiao.dsl.auto.HBaseSQLParser;
import com.github.CCwexiao.dsl.client.RowKey;
import com.github.CCwexiao.dsl.client.rowkey.RowKeyUtil;
import com.github.CCwexiao.dsl.client.rowkeytextfunc.RowKeyTextFunc;
import com.github.CCwexiao.dsl.config.HBaseSQLRuntimeSetting;
import com.github.CCwexiao.dsl.manual.HBaseSQLContextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2020/12/6 8:27 下午
 */
public class RowKeyListConstantVisitor extends HBaseSQLBaseVisitor<List<RowKey>> {

    private final HBaseSQLRuntimeSetting runtimeSetting;

    public RowKeyListConstantVisitor(HBaseSQLRuntimeSetting runtimeSetting) {
        this.runtimeSetting = runtimeSetting;
    }

    @Override
    public List<RowKey> visitRowkey_inRangeKey(HBaseSQLParser.Rowkey_inRangeKeyContext ctx) {
        String funcName = ctx.funcname().getText();
        final RowKeyTextFunc rowKeyTextFunc = runtimeSetting.findRowKeyTextFunc(funcName);
        List<RowKey> rowKeyList = new ArrayList<>();

        for (HBaseSQLParser.ConstantContext constantContext : ctx.constant()) {
            String rowKeyText = constantContext.TEXT().getText();
            final RowKey rowKey = rowKeyTextFunc.func(rowKeyText);
            rowKeyList.add(rowKey);
        }
        return rowKeyList;
    }


}
