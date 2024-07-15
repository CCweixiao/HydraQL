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

package com.hydraql.adapter.dsl.antlr.visitor;

import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.lang.Assert;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.adapter.dsl.antlr.data.TimeStampRange;
import com.hydraql.dsl.model.HBaseTableSchema;

import java.util.List;

/**
 * @author leojie 2020/11/28 10:24 上午
 */
public class TimeStampRangeVisitor extends BaseVisitor<TimeStampRange> {

  public TimeStampRangeVisitor(HBaseTableSchema tableSchema) {
    super(tableSchema);
  }

  @Override
  public TimeStampRange visitTsRangeEq(HydraQLParser.TsRangeEqContext ctx) {
    TimeStampRange timeStampRange = new TimeStampRange();
    long ts = this.extractTimeStamp(ctx.tsExp());
    if (ts > 1) {
      timeStampRange.setStart(ts);
      timeStampRange.setEnd(ts + 1);
    }
    return timeStampRange;
  }

  @Override
  public TimeStampRange visitTsRangeStart(HydraQLParser.TsRangeStartContext ctx) {
    TimeStampRange timeStampRange = new TimeStampRange();
    long startTs = this.extractTimeStamp(ctx.tsExp());
    if (ctx.gtOper().GT() != null) {
      startTs += 1;
    }
    timeStampRange.setStart(startTs);
    timeStampRange.setEnd(Long.MAX_VALUE);
    return timeStampRange;
  }

  @Override
  public TimeStampRange visitTsRangeEnd(HydraQLParser.TsRangeEndContext ctx) {
    TimeStampRange timeStampRange = new TimeStampRange();
    long stopTs = this.extractTimeStamp(ctx.tsExp());
    if (ctx.leOper().LE() != null) {
      stopTs += 1;
    }
    timeStampRange.setStart(0L);
    timeStampRange.setEnd(stopTs);
    return timeStampRange;
  }

  @Override
  public TimeStampRange visitTsRangeStartAndEnd(HydraQLParser.TsRangeStartAndEndContext ctx) {
    TimeStampRange timeStampRange = new TimeStampRange();
    List<HydraQLParser.TsExpContext> tsExpContexts = ctx.tsExp();
    long startTs = this.extractTimeStamp(tsExpContexts.get(0));
    long stopTs = this.extractTimeStamp(tsExpContexts.get(1));
    if (ctx.gtOper().GT() != null) {
      startTs += 1;
    }
    if (ctx.leOper().LE() != null) {
      stopTs += 1;
    }
    if (startTs > stopTs) {
      throw new HBaseSqlAnalysisException(String.format(
        "The start time [%s] cannot be greater" + " than the end time [%s].", startTs, stopTs));
    }
    timeStampRange.setStart(startTs);
    timeStampRange.setEnd(stopTs);
    return timeStampRange;
  }

  public TimeStampRange
      extractTimeStampRange(HydraQLParser.Timestamp_range_clauseContext tsRangeContext) {
    Assert.checkNotNull(tsRangeContext);
    return tsRangeContext.accept(this);
  }

}
