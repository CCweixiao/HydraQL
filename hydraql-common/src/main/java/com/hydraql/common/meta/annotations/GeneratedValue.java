package com.hydraql.common.meta.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.hydraql.common.meta.annotations.GenerationType.NOTHING;

/**
 * Specify strategy to generate required row key.
 *
 * @author leojie@apache.org 2024/8/10 22:45
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedValue {
    /**
     * (Optional) The row key generation strategy {@link GenerationType}.
     */
    GenerationType strategy() default NOTHING;
}
