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

package com.hydraql.common.callback;

/**
 * <p>
 * A functional interface for defining administrator actions.
 * </p>
 * @author leo.jie (leojie1314@gmail.com)
 */
public interface AdminCallback<T, A> {
  /**
   * do action in admin
   * @param admin admin
   * @return result
   * @throws Throwable throw error
   */
  T doInAdmin(A admin) throws Throwable;
}
