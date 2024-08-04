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

package com.hydraql.reflectasm.benchmark;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Benchmark {
  public boolean warmup = true;
  public HashMap<String, Long> testTimes = new HashMap<>();
  private long s;

  public void start() {
    s = System.nanoTime();
  }

  public void end(String name) {
    if (warmup) return;
    long e = System.nanoTime();
    long time = e - s;
    Long oldTime = testTimes.get(name);
    if (oldTime == null || time < oldTime) testTimes.put(name, time);
    System.out.println(name + ": " + time / 1000000f + " ms");
  }

  public void chart(String title) {
    Comparator<Entry<String, Long>> comparator = (o1, o2) -> (int) (o1.getValue() - o2.getValue());
    List<Entry<String, Long>> list = new ArrayList<>(testTimes.entrySet());
    list.sort(comparator);

    StringBuilder names = new StringBuilder(512);
    StringBuilder times = new StringBuilder(512);
    long max = 0;
    int count = 0;
    for (Entry<String, Long> entry : list) {
      String name = entry.getKey();
      names.insert(0, '|');
      names.insert(0, name);
      long time = entry.getValue();
      times.append(time);
      times.append(',');
      max = Math.max(max, time);
      count++;
    }
    times.setLength(times.length() - 1);
    names.setLength(names.length() - 1);
    int height = count * 18 + 21;
    int width = Math.min(700, 300000 / height);
    System.out.println(
      "[img]http://chart.apis.google.com/chart?chtt=" + title + "&" + "chs=" + width + "x" + height
          + "&chd=t:" + times + "&chds=0," + max + "&chxl=0:|" + names + "&cht=bhg&chbh=10&chxt=y&"
          + "chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|"
          + "663366|663399|6633CC|6633FF|666600|666633|666666[/img]\n");
  }
}
