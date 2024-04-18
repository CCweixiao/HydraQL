package com.hydraql.common.annotation;

import com.hydraql.common.constants.HBaseConstants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to define a table model, including namespace, tableName and defaultFamily.
 *
 * @author leo
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HBaseTable {
    /**
     * namespace name, and the default value is 'default'.
     *
     * @return namespace name
     */
    String namespace() default HBaseConstants.DEFAULT_NAMESPACE_NAME;

    /**
     * Define the table name for the table model, <br/>
     * If the table name is empty, exception {@link com.hydraql.common.exception.InvalidTableModelClassException}
     * is thrown to the user.
     *
     * @return table name
     */
    String tableName() default "";

    /**
     * If you have only one column family in your table model, <br/>
     * you can set a default column family and all fields defined in the table model
     * will have this column family.
     *
     * @return default family name
     */
    String defaultFamily() default "";
}
