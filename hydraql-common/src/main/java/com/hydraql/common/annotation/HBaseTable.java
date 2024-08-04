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

package com.hydraql.common.annotation;

import com.hydraql.common.constants.HBaseConstants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to define a table model, including namespace, tableName and
 * defaultFamily.
 * @author leo
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HBaseTable {
  /**
   * namespace name, and the default value is 'default'.
   * @return namespace name
   */
  String namespace() default HBaseConstants.DEFAULT_NAMESPACE_NAME;

  /**
   * Define the table name for the table model, <br/>
   * If the table name is empty, exception
   * {@link com.hydraql.common.exception.InvalidTableModelClassException} is thrown to the user.
   * @return table name
   */
  String tableName() default "";

  /**
   * If you have only one column family in your table model, <br/>
   * you can set a default column family and all fields defined in the table model will have this
   * column family.
   * @return default family name
   */
  String defaultFamily() default "";
}
