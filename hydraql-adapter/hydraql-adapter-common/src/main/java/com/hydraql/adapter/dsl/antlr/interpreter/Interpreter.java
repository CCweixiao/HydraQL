package com.hydraql.adapter.dsl.antlr.interpreter;

import com.hydraql.common.exception.HydraQLTypeMissmatchException;
import com.hydraql.common.model.HQLType;

/**
 * @author leojie 2023/9/27 13:49
 */
public interface Interpreter {

    HQLType expectHqlType();

    default void checkParsedHqlTypeMatched(HQLType hqlType) {
        if (hqlType != expectHqlType()) {
            throw new HydraQLTypeMissmatchException(
                    String.format("The parsed hql type [%s] does not match the target execution hql type [%s].",
                            hqlType, expectHqlType()));
        }
    }
}
