# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

module Shell
  module Commands
    # list all enabled tables
    class ListEnabledTables < Command
      def help
        <<~EOF
           List all enabled tables
           Examples:
           hbase> list_enabled_tables
        EOF
      end

      def command
        formatter.header(['TABLE'])

        list = admin.list_tables_by_state(true)
        list.each do |table|
          formatter.row([table])
        end

        formatter.footer(list.size)
        list
      end
    end
  end
end
