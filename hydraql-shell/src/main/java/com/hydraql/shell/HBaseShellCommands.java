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

package com.hydraql.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author leojie 2023/7/11 22:19
 */
public class HBaseShellCommands {
  private final static Logger LOG = LoggerFactory.getLogger(HBaseShellCommands.class);
  private volatile static Set<String> commandsSet = null;

  private HBaseShellCommands() {

  }

  public static Set<String> getAllCommands() throws IOException {
    String commandsPath =
        String.format("%s/hbase-ruby/shell/commands", HBaseShellConstants.HBASE_ADAPTER_VERSION);
    if (commandsSet == null) {
      synchronized (HBaseShellCommands.class) {
        if (commandsSet == null) {
          Set<String> sortedSet = new TreeSet<>();
          URL commandFilesUrl = HBaseShellCommands.class.getClassLoader().getResource(commandsPath);
          if (commandFilesUrl == null) {
            throw new IOException(
                String.format("The commands file path %s is not exists", commandsPath));
          }
          String commandFilePath = commandFilesUrl.getPath();
          File commandFile = new File(commandFilePath);
          if (!commandFile.exists()) {
            LOG.warn(
              String.format("The commands file path %s is not exists, starting read file from jar",
                commandFilePath));
            String jarPath = commandFilesUrl.toString().substring(0,
              commandFilesUrl.toString().indexOf("!/") + 2);
            LOG.info("The commands file path in jar is " + jarPath);
            URL jarUrl = new URL(jarPath);
            JarURLConnection jarCon = (JarURLConnection) jarUrl.openConnection();
            JarFile jarFile = jarCon.getJarFile();
            Enumeration<JarEntry> jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
              JarEntry entry = jarEntries.nextElement();
              String name = entry.getName();
              if (!entry.isDirectory() && name.startsWith(commandsPath)) {
                String commandName =
                    name.substring(name.lastIndexOf(File.separator) + 1, name.lastIndexOf(".rb"));
                sortedSet.add(commandName);
              }
            }

          } else {
            String[] files = commandFile.list();
            if (files == null) {
              throw new IOException("The command files is null!");
            }
            for (String file : files) {
              if (file.endsWith(".rb")) {
                sortedSet.add(file.substring(0, file.lastIndexOf(".rb")));
              }
            }
          }

          commandsSet = sortedSet;
        }
      }
    }
    return commandsSet;
  }

  public static List<String> searchCommand(String subCommand) {
    List<String> matchCommands = new ArrayList<>();

    try {
      Set<String> allCommands = getAllCommands();
      for (String command : allCommands) {
        if (command.startsWith(subCommand)) {
          matchCommands.add(command);
        }
      }
    } catch (IOException e) {
      return matchCommands;
    }
    return matchCommands;
  }
}
