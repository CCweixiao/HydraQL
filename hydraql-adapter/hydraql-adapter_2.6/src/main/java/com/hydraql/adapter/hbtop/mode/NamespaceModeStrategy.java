package com.hydraql.adapter.hbtop.mode;

import com.hydraql.adapter.hbtop.Record;
import com.hydraql.adapter.hbtop.RecordFilter;
import com.hydraql.adapter.hbtop.field.Field;
import com.hydraql.adapter.hbtop.field.FieldInfo;
import org.apache.hadoop.hbase.ClusterMetrics;



import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author leojie 2021/1/16 9:14 下午
 */

public final class NamespaceModeStrategy implements ModeStrategy {

  private final List<FieldInfo> fieldInfos = Arrays.asList(
    new FieldInfo(Field.NAMESPACE, 0, true),
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

  NamespaceModeStrategy(){
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
  public List<Record> getRecords(ClusterMetrics clusterMetrics) {
    // Get records from RegionModeStrategy and add REGION_COUNT field
    List<Record> records = regionModeStrategy.getRecords(clusterMetrics).stream()
            .map(record ->
                    Record.ofEntries(fieldInfos.stream()
                            .filter(fi -> record.containsKey(fi.getField()))
                            .map(fi -> Record.entry(fi.getField(), record.get(fi.getField())))))
            .map(record -> Record.builder().putAll(record).put(Field.REGION_COUNT, 1).build())
            .collect(Collectors.toList());

    // Aggregation by NAMESPACE field
    return records.stream()
            .collect(Collectors.groupingBy(r -> r.get(Field.NAMESPACE).asString()))
            .entrySet().stream()
            .flatMap(
                    e -> e.getValue().stream()
                            .reduce(Record::combine)
                            .map(Stream::of)
                            .orElse(Stream.empty()))
            .collect(Collectors.toList());
  }

  @Override
  public DrillDownInfo drillDown(Record selectedRecord) {
    List<RecordFilter> initialFilters =
      Collections.singletonList(RecordFilter.newBuilder(Field.NAMESPACE)
        .doubleEquals(selectedRecord.get(Field.NAMESPACE)));
    return new DrillDownInfo(Mode.TABLE, initialFilters);
  }
}
