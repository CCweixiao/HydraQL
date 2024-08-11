/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hydraql.common.meta.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to define the field model in HBase and contains two parts: family and
 * qualifier.
 * @author leo
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HBaseColumn {

  /**
   * Define family name for the field, it will have the highest priority. if not defined, get the
   * default family in {@link HBaseTable}. <br/>
   * <p>
   * If family name is not defined in both places, exception
   * {@link com.hydraql.common.exception.InvalidTableModelClassException} will be thrown to the user
   * @return family name
   */
  String family() default "";

  /**
   * If qualifier is not defined, the field name of the table model class attribute is taken.
   * @return qualifier name
   */
  String qualifier() default "";

  /**
   * Whether the hbase column value can be null. <br/>
   * If set to false and a null value is passed, an exception {@link java.lang.IllegalStateException} will be thrown.
   *
   * @return can be null or not, default value is true
   */
  boolean nullable() default true;
}
