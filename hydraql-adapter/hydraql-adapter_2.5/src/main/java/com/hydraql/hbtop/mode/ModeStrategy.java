package com.hydraql.hbtop.mode;

import com.hydraql.hbtop.Record;
import com.hydraql.hbtop.field.Field;
import com.hydraql.hbtop.field.FieldInfo;
import org.apache.hadoop.hbase.ClusterMetrics;
import org.apache.yetus.audience.InterfaceAudience;


import java.util.List;


/**
 * @author leojie 2021/1/16 9:14 下午
 */
@InterfaceAudience.Private
interface ModeStrategy {
    List<FieldInfo> getFieldInfos();

    Field getDefaultSortField();

    List<Record> getRecords(ClusterMetrics clusterMetrics);

    DrillDownInfo drillDown(Record selectedRecord);
}
