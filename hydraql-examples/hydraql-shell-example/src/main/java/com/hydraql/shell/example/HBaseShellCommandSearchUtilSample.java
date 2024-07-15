/**
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

package com.hydraql.shell.example;

import com.hydraql.shell.HBaseShellCommands;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author leojie 2023/7/11 23:03
 */
public class HBaseShellCommandSearchUtilSample {
  public static void main(String[] args) {
    String name = "hbase-ruby/shell/commands/list_namespace.rb";
    String commandName = name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf(".rb"));
    System.out.println(commandName);

    Set<String> allCommands = null;
    try {
      allCommands = HBaseShellCommands.getAllCommands();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    List<String> li = HBaseShellCommands.searchCommand("create_nam");
    System.out.println(allCommands);
  }
}
