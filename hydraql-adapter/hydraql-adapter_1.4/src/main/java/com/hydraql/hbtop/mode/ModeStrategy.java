package com.hydraql.hbtop.mode;

import com.hydraql.hbtop.field.Field;
import com.hydraql.hbtop.field.FieldInfo;
import com.hydraql.hbtop.Record;
import edu.umd.cs.findbugs.annotations.Nullable;

import java.util.List;

import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.classification.InterfaceAudience;


/**
 * @author leojie 2021/1/16 9:14 下午
 */
@InterfaceAudience.Private
interface ModeStrategy {
    List<FieldInfo> getFieldInfos();

    Field getDefaultSortField();

    List<Record> getRecords(ClusterStatus clusterStatus);

    @Nullable
    DrillDownInfo drillDown(Record selectedRecord);
}
