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

package com.hydraql.adapter;

import com.hydraql.adapter.context.HTableContext;
import com.hydraql.common.exception.HBaseQueryParamsException;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.common.query.GetRowsParam;
import com.hydraql.common.query.ScanParams;
import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

/**
 * @author leo.jie (weixiao.me@aliyun.com)
 */

public class HBaseTableAdapter extends AbstractHBaseTableAdapter {
  public HBaseTableAdapter(Configuration configuration) {
    super(configuration);
  }

  @Override
  public Get buildGet(GetRowParam getRowsParam) {
    Get get = new Get(Bytes.toBytes(getRowsParam.getRowKey()));
    if (getRowsParam.onlyFamily()) {
      get.addFamily(Bytes.toBytes(getRowsParam.getFamily()));
    }
    if (getRowsParam.familyWithQualifiers()) {
      getRowsParam.getQualifiers().forEach(colName -> {
        if (StringUtil.isNotBlank(colName)) {
          get.addColumn(Bytes.toBytes(getRowsParam.getFamily()), Bytes.toBytes(colName));
        }
      });
    }
    try {
      get.readVersions(getRowsParam.getVersions());
      if (getRowsParam.getTimeRange() != null) {
        get.setTimeRange(getRowsParam.getTimeRange().getMinTimestamp(),
          getRowsParam.getTimeRange().getMaxTimestamp());
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

    return get;
  }

  @Override
  public List<Get> buildGets(GetRowsParam getRowsParam) {
    if (getRowsParam == null || getRowsParam.getRowKeyList() == null
        || getRowsParam.getRowKeyList().isEmpty()) {
      return new ArrayList<>(0);
    }
    List<Get> gets = new ArrayList<>(getRowsParam.getRowKeyList().size());
    for (String rowKey : getRowsParam.getRowKeyList()) {
      Get get = new Get(Bytes.toBytes(rowKey));
      if (getRowsParam.onlyFamily()) {
        get.addFamily(Bytes.toBytes(getRowsParam.getFamily()));
      }
      if (getRowsParam.familyWithQualifiers()) {
        getRowsParam.getQualifiers().forEach(colName -> {
          if (StringUtil.isNotBlank(colName)) {
            get.addColumn(Bytes.toBytes(getRowsParam.getFamily()), Bytes.toBytes(colName));
          }
        });
      }
      try {
        get.readVersions(getRowsParam.getVersions());
        if (getRowsParam.getTimeRange() != null) {
          get.setTimeRange(getRowsParam.getTimeRange().getMinTimestamp(),
            getRowsParam.getTimeRange().getMaxTimestamp());
        }
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
      gets.add(get);
    }
    return gets;
  }

  @Override
  public Scan buildScan(ScanParams scanParams) {
    Scan scan = new Scan();
    if (scanParams.onlyFamily()) {
      scan.addFamily(Bytes.toBytes(scanParams.getFamilyName()));
    }
    if (scanParams.familyWithQualifiers()) {
      scanParams.getColumnNames().forEach(colName -> {
        if (StringUtil.isNotBlank(colName)) {
          scan.addColumn(Bytes.toBytes(scanParams.getFamilyName()), Bytes.toBytes(colName));
        }
      });
    }

    if (scanParams.startRowIsSet()) {
      scan.withStartRow(Bytes.toBytes(scanParams.getStartRow()), scanParams.isInclusiveStartRow());
    }

    if (scanParams.endRowIsSet()) {
      scan.withStopRow(Bytes.toBytes(scanParams.getStopRow()), scanParams.isInclusiveStopRow());
    }

    if (StringUtil.isNotBlank(scanParams.getRowPrefix())) {
      PrefixFilter filter = new PrefixFilter(Bytes.toBytes(scanParams.getRowPrefix()));
      scan.setFilter(filter);
    }

    if (scanParams.getFilter() != null && scanParams.getFilter().customFilter() instanceof Filter) {
      scan.setFilter((Filter) scanParams.getFilter().customFilter());
    }

    if (scanParams.timeRangeIsSet()) {
      try {
        scan.setTimeRange(scanParams.getMinTimestamp(), scanParams.getMaxTimestamp());
      } catch (IOException e) {
        throw new HBaseQueryParamsException(e);
      }
    }

    if (scanParams.timestampIsSet()) {
      scan.setTimestamp(scanParams.getTimestamp());
    }

    if (scanParams.getVersions() > 0) {
      scan.readVersions(scanParams.getVersions());
    }

    if (scanParams.isCacheBlocks()) {
      scan.setCacheBlocks(scanParams.isCacheBlocks());
    }

    if (scanParams.isReversed()) {
      scan.setReversed(scan.isReversed());
    }

    if (scanParams.getCaching() > 0) {
      scan.setCaching(scanParams.getCaching());
    } else {
      scan.setCaching(getClientScannerCachingFromConfig());
    }

    if (scanParams.getBatch() > 0) {
      if (!(scan.hasFilter() && scan.getFilter().hasFilterRow())) {
        scan.setBatch(scanParams.getBatch());
      }
    }

    if (scanParams.getMaxResultSize() > 0) {
      scan.setMaxResultSize(scanParams.getMaxResultSize());
    }

    if (scanParams.getLimit() > 0) {
      scan.setLimit(scanParams.getLimit());
    }

    return scan;
  }

  @Override
  public WrapperBufferedMutator getWrapperBufferedMutator(HTableContext tableContext) {
    BufferedMutator bufferedMutator = this.getBufferedMutator(tableContext);
    return new WrapperBufferedMutatorImpl(tableContext, bufferedMutator);
  }

  @Override
  public WrapperBufferedMutator getHedgedReadWrapperBufferedMutator(HTableContext tableContext) {
    BufferedMutator bufferedMutator = this.getHedgedReadBufferedMutator(tableContext);
    return new WrapperBufferedMutatorImpl(tableContext, bufferedMutator);
  }
}
