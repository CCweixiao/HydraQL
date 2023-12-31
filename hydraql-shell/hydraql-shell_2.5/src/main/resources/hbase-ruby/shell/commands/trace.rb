#
#
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
    class Trace < Command
       @@tracer = org.apache.hadoop.hbase.trace.TraceUtil.getGlobalTracer()
       @@tracespan = nil
       @@tracescope = nil

      def help
        <<-EOF
Start or Stop tracing using OpenTelemetry.
Always returns true if tracing is running, otherwise false.
If the first argument is 'start', new span is started.
If the first argument is 'stop', current running span is stopped.
('stop' returns false on success.)
If the first argument is 'status', just returns if or not tracing is running.
On 'start'-ing, you can optionally pass the name of span as the second argument.
The default name of span is 'HBaseShell'.
Repeating 'start' does not start nested span.

Examples:

  hbase> trace 'start'
  hbase> trace 'status'
  hbase> trace 'stop'

  hbase> trace 'start', 'MySpanName'
  hbase> trace 'stop'

EOF
      end

      def command(startstop = 'status', spanname = 'HBaseShell')
        trace(startstop, spanname)
      end

      def trace(startstop, spanname)
        if startstop == 'start'
          unless tracing?
           @@tracespan = @@tracer.spanBuilder(spanname).startSpan()
           @@tracescope = @@tracespan.makeCurrent()
          end
        elsif startstop == 'stop'
          if tracing?
           @@tracescope.close()
           @@tracespan.end()
           @@tracescope = nil
          end
        end
        tracing?
      end

      def tracing?
        @@tracescope != nil
      end
    end
  end
end
