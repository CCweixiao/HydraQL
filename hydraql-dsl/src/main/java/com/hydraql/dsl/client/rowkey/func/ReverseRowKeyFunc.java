package com.hydraql.dsl.client.rowkey.func;

import com.hydraql.common.util.StringUtil;
import com.hydraql.dsl.client.rowkey.BaseRowKey;
import com.hydraql.dsl.model.HBaseColumn;

/**
 * @author leojie 2022/12/3 13:50
 */
public class ReverseRowKeyFunc implements RowKeyFunc<String> {

    @Override
    public String evalFuncReturnRowValue(BaseRowKey<String> rowKey) {
        String oriValue = rowKey.getOriValue();
        if (StringUtil.isBlank(oriValue)) {
            return oriValue;
        }
        return StringUtil.reverse(oriValue);
    }

    @Override
    public String evalFuncReturnRowValue(HBaseColumn row, String value) {
        if (StringUtil.isBlank(value)) {
            return value;
        }
        return StringUtil.reverse(value);
    }

    @Override
    public String showFuncName() {
        return "reverse";
    }

    @Override
    public String showDesc() {
        return "Reverse the row key, example reverse ( 'abcd' ) ";
    }
}
