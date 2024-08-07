/*
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

package com.hydraql.adapter.hbtop.mode;

import com.hydraql.adapter.hbtop.Record;
import com.hydraql.adapter.hbtop.field.Field;
import com.hydraql.adapter.hbtop.field.FieldInfo;
import com.hydraql.adapter.hbtop.field.Size;
import com.hydraql.common.util.DateAndTimeUtil;
import edu.umd.cs.findbugs.annotations.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HRegionInfo;
import org.apache.hadoop.hbase.RegionLoad;
import org.apache.hadoop.hbase.ServerLoad;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.classification.InterfaceAudience;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author leojie 2021/1/16 9:14 下午
 */

public final class RegionModeStrategy implements ModeStrategy {

  private final List<FieldInfo> fieldInfos = Arrays.asList(
    new FieldInfo(Field.REGION_NAME, 0, false), new FieldInfo(Field.NAMESPACE, 0, true),
    new FieldInfo(Field.TABLE, 0, true), new FieldInfo(Field.START_CODE, 13, false),
    new FieldInfo(Field.REPLICA_ID, 5, false), new FieldInfo(Field.REGION, 32, true),
    new FieldInfo(Field.REGION_SERVER, 0, true), new FieldInfo(Field.LONG_REGION_SERVER, 0, false),
    new FieldInfo(Field.REQUEST_COUNT_PER_SECOND, 8, true),
    new FieldInfo(Field.READ_REQUEST_COUNT_PER_SECOND, 8, true),
    new FieldInfo(Field.WRITE_REQUEST_COUNT_PER_SECOND, 8, true),
    new FieldInfo(Field.STORE_FILE_SIZE, 10, true),
    new FieldInfo(Field.UNCOMPRESSED_STORE_FILE_SIZE, 12, false),
    new FieldInfo(Field.NUM_STORE_FILES, 4, true), new FieldInfo(Field.MEM_STORE_SIZE, 8, true),
    new FieldInfo(Field.LOCALITY, 8, true), new FieldInfo(Field.START_KEY, 0, false),
    new FieldInfo(Field.COMPACTING_CELL_COUNT, 12, false),
    new FieldInfo(Field.COMPACTED_CELL_COUNT, 12, false),
    new FieldInfo(Field.COMPACTION_PROGRESS, 7, false),
    new FieldInfo(Field.LAST_MAJOR_COMPACTION_TIME, 19, false));

  private final Map<String, RequestCountPerSecond> requestCountPerSecondMap = new HashMap<>();

  RegionModeStrategy() {
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
    List<Record> ret = new ArrayList<>();
    for (ServerName sn : clusterStatus.getServers()) {
      ServerLoad sl = clusterStatus.getLoad(sn);
      long lastReportTimestamp = sl.obtainServerLoadPB().getReportEndTime();
      for (RegionLoad rl : sl.getRegionsLoad().values()) {
        ret.add(createRecord(sn, rl, lastReportTimestamp));
      }
    }
    return ret;
  }

  private Record createRecord(ServerName sn, RegionLoad regionLoad, long lastReportTimestamp) {
    Record.Builder builder = Record.builder();

    String regionName = regionLoad.getNameAsString();
    builder.put(Field.REGION_NAME, regionName);

    String namespaceName = "";
    String tableName = "";
    String region = "";
    String startKey = "";
    String startCode = "";
    String replicaId = "";
    try {
      byte[][] elements = HRegionInfo.parseRegionName(regionLoad.getName());
      TableName tn = TableName.valueOf(elements[0]);
      namespaceName = tn.getNamespaceAsString();
      tableName = tn.getQualifierAsString();
      startKey = Bytes.toStringBinary(elements[1]);
      startCode = Bytes.toString(elements[2]);
      replicaId =
          elements.length == 4 ? Integer.valueOf(Bytes.toString(elements[3])).toString() : "";
      region = HRegionInfo.encodeRegionName(regionLoad.getName());
    } catch (IOException ignored) {
    }

    builder.put(Field.NAMESPACE, namespaceName);
    builder.put(Field.TABLE, tableName);
    builder.put(Field.START_CODE, startCode);
    builder.put(Field.REPLICA_ID, replicaId);
    builder.put(Field.REGION, region);
    builder.put(Field.START_KEY, startKey);
    builder.put(Field.REGION_SERVER, sn.toShortString());
    builder.put(Field.LONG_REGION_SERVER, sn.getServerName());

    RequestCountPerSecond requestCountPerSecond = requestCountPerSecondMap.get(regionName);
    if (requestCountPerSecond == null) {
      requestCountPerSecond = new RequestCountPerSecond();
      requestCountPerSecondMap.put(regionName, requestCountPerSecond);
    }
    requestCountPerSecond.refresh(lastReportTimestamp, regionLoad.getReadRequestsCount(),
      regionLoad.getWriteRequestsCount());

    builder.put(Field.READ_REQUEST_COUNT_PER_SECOND,
      requestCountPerSecond.getReadRequestCountPerSecond());
    builder.put(Field.WRITE_REQUEST_COUNT_PER_SECOND,
      requestCountPerSecond.getWriteRequestCountPerSecond());
    builder.put(Field.REQUEST_COUNT_PER_SECOND, requestCountPerSecond.getRequestCountPerSecond());

    builder.put(Field.STORE_FILE_SIZE,
      new Size(regionLoad.getStorefileSizeMB(), Size.Unit.MEGABYTE));
    builder.put(Field.UNCOMPRESSED_STORE_FILE_SIZE,
      new Size(regionLoad.getStoreUncompressedSizeMB(), Size.Unit.MEGABYTE));
    builder.put(Field.NUM_STORE_FILES, regionLoad.getStorefiles());
    builder.put(Field.MEM_STORE_SIZE, new Size(regionLoad.getMemStoreSizeMB(), Size.Unit.MEGABYTE));
    builder.put(Field.LOCALITY, regionLoad.getDataLocality());

    long compactingCellCount = regionLoad.getTotalCompactingKVs();
    long compactedCellCount = regionLoad.getCurrentCompactedKVs();
    float compactionProgress = 0;
    if (compactedCellCount > 0) {
      compactionProgress = 100 * ((float) compactedCellCount / compactingCellCount);
    }

    builder.put(Field.COMPACTING_CELL_COUNT, compactingCellCount);
    builder.put(Field.COMPACTED_CELL_COUNT, compactedCellCount);
    builder.put(Field.COMPACTION_PROGRESS, compactionProgress);

    long lastMajorCompactionTimestamp = regionLoad.getLastMajorCompactionTs();

    builder.put(Field.LAST_MAJOR_COMPACTION_TIME, lastMajorCompactionTimestamp == 0 ? ""
        : DateAndTimeUtil.parseTimestampToTimeStr(lastMajorCompactionTimestamp));

    return builder.build();
  }

  @Nullable
  @Override
  public DrillDownInfo drillDown(Record selectedRecord) {
    // do nothing
    return null;
  }
}
