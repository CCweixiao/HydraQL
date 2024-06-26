package com.hydraql.adapter.hbtop.mode;

import com.hydraql.adapter.hbtop.Record;
import com.hydraql.adapter.hbtop.field.Field;
import com.hydraql.adapter.hbtop.field.FieldInfo;
import org.apache.hadoop.hbase.ClusterMetrics;


import java.util.List;
import java.util.Objects;

/**
 * @author leojie 2021/1/16 9:14 下午
 */

public enum Mode {
  NAMESPACE("Namespace", "Record per Namespace", new NamespaceModeStrategy()),
  TABLE("Table", "Record per Table", new TableModeStrategy()),
  REGION("Region", "Record per Region", new RegionModeStrategy()),
  REGION_SERVER("RegionServer", "Record per RegionServer", new RegionServerModeStrategy());

  private final String header;
  private final String description;
  private final ModeStrategy modeStrategy;

  Mode(String header, String description, ModeStrategy modeStrategy) {
    this.header  = Objects.requireNonNull(header);
    this.description = Objects.requireNonNull(description);
    this.modeStrategy = Objects.requireNonNull(modeStrategy);
  }

  public String getHeader() {
    return header;
  }

  public String getDescription() {
    return description;
  }

  public List<Record> getRecords(ClusterMetrics clusterMetrics) {
    return modeStrategy.getRecords(clusterMetrics);
  }

  public List<FieldInfo> getFieldInfos() {
    return modeStrategy.getFieldInfos();
  }

  public Field getDefaultSortField() {
    return modeStrategy.getDefaultSortField();
  }

  public DrillDownInfo drillDown(Record currentRecord) {
    return modeStrategy.drillDown(currentRecord);
  }
}
