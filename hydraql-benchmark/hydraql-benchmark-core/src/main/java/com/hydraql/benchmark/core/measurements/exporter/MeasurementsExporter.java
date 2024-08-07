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

package com.hydraql.benchmark.core.measurements.exporter;

import java.io.Closeable;
import java.io.IOException;

/**
 * Used to export the collected measurements into a useful format, for example human readable text
 * or machine readable JSON.
 */
public interface MeasurementsExporter extends Closeable {
  /**
   * Write a measurement to the exported format.
   * @param metric Metric name, for example "READ LATENCY".
   * @param measurement Measurement name, for example "Average latency".
   * @param i Measurement to write.
   * @throws IOException if writing failed
   */
  void write(String metric, String measurement, int i) throws IOException;

  /**
   * Write a measurement to the exported format.
   * @param metric Metric name, for example "READ LATENCY".
   * @param measurement Measurement name, for example "Average latency".
   * @param i Measurement to write.
   * @throws IOException if writing failed
   */
  void write(String metric, String measurement, long i) throws IOException;

  /**
   * Write a measurement to the exported format.
   * @param metric Metric name, for example "READ LATENCY".
   * @param measurement Measurement name, for example "Average latency".
   * @param d Measurement to write.
   * @throws IOException if writing failed
   */
  void write(String metric, String measurement, double d) throws IOException;
}
