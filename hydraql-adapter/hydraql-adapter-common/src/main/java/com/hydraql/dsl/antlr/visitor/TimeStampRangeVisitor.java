package com.hydraql.dsl.antlr.visitor;


import com.hydraql.common.exception.HBaseSqlAnalysisException;
import com.hydraql.common.lang.MyAssert;
import com.hydraql.dsl.antlr.HydraQLParser;
import com.hydraql.dsl.antlr.data.TimeStampRange;
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
        if (ctx.leOper().LT() != null) {
            stopTs -= 1;
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
        if (ctx.leOper().LT() != null) {
            stopTs -= 1;
        }
        if (startTs > stopTs) {
            throw new HBaseSqlAnalysisException(String.format("The start time [%s] cannot be greater" +
                    " than the end time [%s].", startTs, stopTs));
        }
        timeStampRange.setStart(startTs);
        timeStampRange.setEnd(stopTs);
        return timeStampRange;
    }

    public TimeStampRange extractTimeStampRange(HydraQLParser.Timestamp_range_clauseContext tsRangeContext) {
        MyAssert.checkNotNull(tsRangeContext);
        return tsRangeContext.accept(this);
    }

}
