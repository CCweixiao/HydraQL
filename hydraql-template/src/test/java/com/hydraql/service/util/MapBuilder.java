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

package com.hydraql.service.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leojie 2022/11/4 21:21
 */
public class MapBuilder {
  /**
   * Creates an instance of {@code HashMap}
   */
  public static <K, V> HashMap<K, V> newHashMap() {
    return new HashMap<>();
  }

  /**
   * Returns the empty map.
   */
  public static <K, V> Map<K, V> of() {
    return newHashMap();
  }

  /**
   * Returns map containing a single entry.
   */
  public static <K, V> Map<K, V> of(K k1, V v1) {
    Map<K, V> map = of();
    map.put(k1, v1);
    return map;
  }

  /**
   * Returns map containing the given entries.
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
    Map<K, V> map = of();
    map.put(k1, v1);
    map.put(k2, v2);
    return map;
  }

  /**
   * Returns map containing the given entries.
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    Map<K, V> map = of();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    return map;
  }

  /**
   * Returns map containing the given entries.
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    Map<K, V> map = of();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    map.put(k4, v4);
    return map;
  }

  /**
   * Returns map containing the given entries.
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    Map<K, V> map = of();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    map.put(k4, v4);
    map.put(k5, v5);
    return map;
  }

  /**
   * Returns map containing the given entries.
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
      K k6, V v6) {
    Map<K, V> map = of();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    map.put(k4, v4);
    map.put(k5, v5);
    map.put(k6, v6);
    return map;
  }

  /**
   * Returns map containing the given entries.
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5,
      K k6, V v6, K k7, V v7) {
    Map<K, V> map = of();
    map.put(k1, v1);
    map.put(k2, v2);
    map.put(k3, v3);
    map.put(k4, v4);
    map.put(k5, v5);
    map.put(k6, v6);
    map.put(k7, v7);
    return map;
  }

  /**
   * Returns map containing the given entries.
   */
  public static <K, V> Builder<K, V> builder() {
    return new Builder<>();
  }

  public static final class Builder<K, V> {

    private Map<K, V> map;
    private boolean underConstruction;

    public Builder() {
      map = newHashMap();
      underConstruction = true;
    }

    public Builder<K, V> put(K k, V v) {
      if (!underConstruction) {
        throw new IllegalStateException("Underlying map has already been built");
      }
      map.put(k, v);
      return this;
    }

    public Map<K, V> build() {
      if (!underConstruction) {
        throw new IllegalStateException("Underlying map has already been built");
      }
      underConstruction = false;
      return map;
    }
  }

}
