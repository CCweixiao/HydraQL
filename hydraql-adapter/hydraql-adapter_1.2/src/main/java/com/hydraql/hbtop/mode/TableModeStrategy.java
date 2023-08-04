package com.hydraql.hbtop.mode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hydraql.hbtop.Record;
import com.hydraql.hbtop.field.Field;
import com.hydraql.hbtop.field.FieldInfo;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.classification.InterfaceAudience;
import com.hydraql.hbtop.RecordFilter;


/**
 * @author leojie 2021/1/16 9:14 下午
 */
@InterfaceAudience.Private
public final class TableModeStrategy implements ModeStrategy {

  private final List<FieldInfo> fieldInfos = Arrays.asList(
    new FieldInfo(Field.NAMESPACE, 0, true),
    new FieldInfo(Field.TABLE, 0, true),
    new FieldInfo(Field.REGION_COUNT, 7, true),
    new FieldInfo(Field.REQUEST_COUNT_PER_SECOND, 10, true),
    new FieldInfo(Field.READ_REQUEST_COUNT_PER_SECOND, 10, true),
    new FieldInfo(Field.WRITE_REQUEST_COUNT_PER_SECOND, 10, true),
    new FieldInfo(Field.STORE_FILE_SIZE, 13, true),
    new FieldInfo(Field.UNCOMPRESSED_STORE_FILE_SIZE, 15, false),
    new FieldInfo(Field.NUM_STORE_FILES, 7, true),
    new FieldInfo(Field.MEM_STORE_SIZE, 11, true)
  );

  private final RegionModeStrategy regionModeStrategy = new RegionModeStrategy();

  TableModeStrategy() {
  }

  @Override
  public List<FieldInfo> getFieldInfos() {
    return fieldInfos;
  }

  @Override
  public Field getDefaultSortField() {
    return Field.REQUEST_COUNT_PER_SECOND;
  }

  @Override
  public List<Record> getRecords(ClusterStatus clusterStatus) {
    // Get records from RegionModeStrategy and add REGION_COUNT field
    List<Record> records = new ArrayList<>();
    for (Record record : regionModeStrategy.getRecords(clusterStatus)) {
      List<Record.Entry> entries = new ArrayList<>();
      for (FieldInfo fieldInfo : fieldInfos) {
        if (record.containsKey(fieldInfo.getField())) {
          entries.add(Record.entry(fieldInfo.getField(),
            record.get(fieldInfo.getField())));
        }
      }

      // Add REGION_COUNT field
      records.add(Record.builder().putAll(Record.ofEntries(entries))
        .put(Field.REGION_COUNT, 1).build());
    }

    // Aggregation by NAMESPACE field
    Map<TableName, Record> retMap = new HashMap<>();
    for (Record record : records) {
      String namespace = record.get(Field.NAMESPACE).asString();
      String table = record.get(Field.TABLE).asString();
      TableName tableName = TableName.valueOf(namespace, table);

      if (retMap.containsKey(tableName)) {
        retMap.put(tableName, retMap.get(tableName).combine(record));
      } else {
        retMap.put(tableName, record);
      }
    }
    return new ArrayList<>(retMap.values());
  }

  @Override
  public DrillDownInfo drillDown(Record selectedRecord) {
    List<RecordFilter> initialFilters = Arrays.asList(
      RecordFilter.newBuilder(Field.NAMESPACE).doubleEquals(selectedRecord.get(Field.NAMESPACE)),
      RecordFilter.newBuilder(Field.TABLE).doubleEquals(selectedRecord.get(Field.TABLE)));
    return new DrillDownInfo(Mode.REGION, initialFilters);
  }
}
