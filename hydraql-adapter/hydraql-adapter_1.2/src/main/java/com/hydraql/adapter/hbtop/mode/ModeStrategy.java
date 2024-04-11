package com.hydraql.adapter.hbtop.mode;

import com.hydraql.adapter.hbtop.field.Field;
import com.hydraql.adapter.hbtop.field.FieldInfo;
import com.hydraql.adapter.hbtop.Record;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.util.List;

import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.classification.InterfaceAudience;


/**
 * @author leojie 2021/1/16 9:14 下午
 */

interface ModeStrategy {
    List<FieldInfo> getFieldInfos();

    Field getDefaultSortField();

    List<Record> getRecords(ClusterStatus clusterStatus);

    @Nullable
    DrillDownInfo drillDown(Record selectedRecord);
}
