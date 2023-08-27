package com.hydraql.hbtop.field;

import org.apache.yetus.audience.InterfaceAudience;


import java.util.Objects;

/**
 * @author leojie 2021/1/16 9:14 下午
 */
@InterfaceAudience.Private
public enum Field {
  REGION_NAME("RNAME", "Region Name", true, true, FieldValueType.STRING),
  NAMESPACE("NAMESPACE", "Namespace Name", true, true, FieldValueType.STRING),
  TABLE("TABLE", "Table Name", true, true, FieldValueType.STRING),
  START_CODE("SCODE", "Start Code", false, true, FieldValueType.STRING),
  REPLICA_ID("REPID", "Replica ID", false, false, FieldValueType.STRING),
  REGION("REGION", "Encoded Region Name", false, true, FieldValueType.STRING),
  REGION_SERVER("RS", "Short Region Server Name", true, true, FieldValueType.STRING),
  LONG_REGION_SERVER("LRS", "Long Region Server Name", true, true, FieldValueType.STRING),
  REQUEST_COUNT_PER_SECOND("#REQ/S", "Request Count per second", false, false,
          FieldValueType.LONG),
  READ_REQUEST_COUNT_PER_SECOND("#READ/S", "Read Request Count per second", false, false,
          FieldValueType.LONG),
  FILTERED_READ_REQUEST_COUNT_PER_SECOND("#FREAD/S", "Filtered Read Request Count per second",
          false, false, FieldValueType.LONG),
  WRITE_REQUEST_COUNT_PER_SECOND("#WRITE/S", "Write Request Count per second", false, false,
          FieldValueType.LONG),
  STORE_FILE_SIZE("SF", "StoreFile Size", false, false, FieldValueType.SIZE),
  UNCOMPRESSED_STORE_FILE_SIZE("USF", "Uncompressed StoreFile Size", false, false,
          FieldValueType.SIZE),
  NUM_STORE_FILES("#SF", "Number of StoreFiles", false, false, FieldValueType.INTEGER),
  MEM_STORE_SIZE("MEMSTORE", "MemStore Size", false, false, FieldValueType.SIZE),
  LOCALITY("LOCALITY", "Block Locality", false, false, FieldValueType.FLOAT),
  START_KEY("SKEY", "Start Key", true, true, FieldValueType.STRING),
  COMPACTING_CELL_COUNT("#COMPingCELL", "Compacting Cell Count", false, false,
          FieldValueType.LONG),
  COMPACTED_CELL_COUNT("#COMPedCELL", "Compacted Cell Count", false, false, FieldValueType.LONG),
  COMPACTION_PROGRESS("%COMP", "Compaction Progress", false, false, FieldValueType.PERCENT),
  LAST_MAJOR_COMPACTION_TIME("LASTMCOMP", "Last Major Compaction Time", false, true,
          FieldValueType.STRING),
  REGION_COUNT("#REGION", "Region Count", false, false, FieldValueType.INTEGER),
  USED_HEAP_SIZE("UHEAP", "Used Heap Size", false, false, FieldValueType.SIZE),
  MAX_HEAP_SIZE("MHEAP", "Max Heap Size", false, false, FieldValueType.SIZE);

  private final String header;
  private final String description;
  private final boolean autoAdjust;
  private final boolean leftJustify;
  private final FieldValueType fieldValueType;

  Field(String header, String description, boolean autoAdjust, boolean leftJustify,
        FieldValueType fieldValueType) {
    this.header = Objects.requireNonNull(header);
    this.description = Objects.requireNonNull(description);
    this.autoAdjust = autoAdjust;
    this.leftJustify = leftJustify;
    this.fieldValueType = Objects.requireNonNull(fieldValueType);
  }

  public FieldValue newValue(Object value) {
    return new FieldValue(value, fieldValueType);
  }

  public String getHeader() {
    return header;
  }

  public String getDescription() {
    return description;
  }

  public boolean isAutoAdjust() {
    return autoAdjust;
  }

  public boolean isLeftJustify() {
    return leftJustify;
  }

  public FieldValueType getFieldValueType() {
    return fieldValueType;
  }
}
