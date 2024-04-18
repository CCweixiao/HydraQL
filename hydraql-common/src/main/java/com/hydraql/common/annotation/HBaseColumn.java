package com.hydraql.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to define the field model in HBase
 * and contains two parts: family and qualifier.
 *
 * @author leo
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HBaseColumn {

    /**
     * Define family name for the field, it will have the highest priority.
     * if not defined, get the default family in {@link HBaseTable}. <br/>
     * <p>
     * If family name is not defined in both places,
     * exception {@link com.hydraql.common.exception.InvalidTableModelClassException} will be thrown to the user
     *
     * @return family name
     */
    String family() default "";

    /**
     * If qualifier is not defined, the field name of the table model class attribute is taken.
     *
     * @return qualifier name
     */
    String qualifier() default "";
}
