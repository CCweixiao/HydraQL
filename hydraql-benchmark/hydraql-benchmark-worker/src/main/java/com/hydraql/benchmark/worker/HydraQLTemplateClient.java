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

package com.hydraql.benchmark.worker;

import com.hydraql.adapter.connection.HBaseConnectionManager;
import com.hydraql.benchmark.core.ByteIterator;
import com.hydraql.benchmark.core.DBException;
import com.hydraql.benchmark.core.Status;
import com.hydraql.benchmark.core.measurements.Measurements;
import com.hydraql.common.query.GetRowParam;
import com.hydraql.common.query.IHBaseFilter;
import com.hydraql.common.query.ScanParams;
import com.hydraql.template.HBaseTableTemplate;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import static com.hydraql.benchmark.core.workloads.CoreWorkload.TABLENAME_PROPERTY;
import static com.hydraql.benchmark.core.workloads.CoreWorkload.TABLENAME_PROPERTY_DEFAULT;

/**
 * @author leojie@apache.org 2024/4/10 20:17
 */
public class HydraQLTemplateClient extends com.hydraql.benchmark.core.DB {
  private static final AtomicInteger THREAD_COUNT = new AtomicInteger(0);

  private final Configuration config = HBaseConfiguration.create();

  private boolean debug = false;

  private String tableName = "";

  /**
   * A Cluster Connection instance that is shared by all running ycsb threads. Needs to be
   * initialized late so we pick up command-line configs if any. To ensure one instance only in a
   * multi-threaded context, guard access with a 'lock' object.
   * @See #CONNECTION_LOCK.
   */
  private static Connection connection = null;
  private HBaseTableTemplate tableTemplate;

  // Depending on the value of clientSideBuffering, either bufferedMutator
  // (clientSideBuffering) or currentTable (!clientSideBuffering) will be used.
  private Table currentTable = null;
  private BufferedMutator bufferedMutator = null;

  private String columnFamily = "";
  private byte[] columnFamilyBytes;

  /**
   * Durability to use for puts and deletes.
   */
  private Durability durability = Durability.USE_DEFAULT;

  /**
   * Whether or not a page filter should be used to limit scan length.
   */
  private boolean usePageFilter = true;

  /**
   * If true, buffer mutations on the client. This is the default behavior for HBaseClient. For
   * measuring insert/update/delete latencies, client side buffering should be disabled.
   */
  private boolean clientSideBuffering = false;
  private long writeBufferSize = 1024 * 1024 * 12;

  /**
   * Initialize any state for this DB. Called once per DB instance; there is one DB instance per
   * client thread.
   */
  @Override
  public void init() throws DBException {
    if ("true".equals(getProperties().getProperty("clientbuffering", "false"))) {
      this.clientSideBuffering = true;
    }
    if (getProperties().containsKey("writebuffersize")) {
      writeBufferSize = Long.parseLong(getProperties().getProperty("writebuffersize"));
    }

    if (getProperties().getProperty("durability") != null) {
      this.durability = Durability.valueOf(getProperties().getProperty("durability"));
    }

    if ("kerberos".equalsIgnoreCase(config.get("hbase.security.authentication"))) {
      config.set("hadoop.security.authentication", "Kerberos");
      UserGroupInformation.setConfiguration(config);
    }

    if ((getProperties().getProperty("principal") != null)
        && (getProperties().getProperty("keytab") != null)) {
      try {
        UserGroupInformation.loginUserFromKeytab(getProperties().getProperty("principal"),
          getProperties().getProperty("keytab"));
      } catch (IOException e) {
        System.err.println("Keytab file is not readable or not found");
        throw new DBException(e);
      }
    }

    String table = getProperties().getProperty(TABLENAME_PROPERTY, TABLENAME_PROPERTY_DEFAULT);
    try {
      THREAD_COUNT.getAndIncrement();
      synchronized (THREAD_COUNT) {
        if (connection == null) {
          // Initialize if not set up already.
          connection = HBaseConnectionManager.create().getConnection(config);

          // Terminate right now if table does not exist, since the client
          // will not propagate this error upstream once the workload
          // starts.
          final TableName tName = TableName.valueOf(table);
          try (Admin admin = connection.getAdmin()) {
            if (!admin.tableExists(tName)) {
              throw new DBException("Table " + tName + " does not exists");
            }
          }
        }
      }
    } catch (IOException e) {
      throw new DBException(e);
    }

    if ((getProperties().getProperty("debug") != null)
        && (getProperties().getProperty("debug").compareTo("true") == 0)) {
      debug = true;
    }

    if ("false".equals(getProperties().getProperty("hbase.usepagefilter", "true"))) {
      usePageFilter = false;
    }

    columnFamily = getProperties().getProperty("columnfamily");
    if (columnFamily == null) {
      columnFamily = "cf";
    }
    columnFamilyBytes = Bytes.toBytes(columnFamily);
    tableTemplate = HBaseTableTemplate.of(config);
  }

  /**
   * Cleanup any state for this DB. Called once per DB instance; there is one DB instance per client
   * thread.
   */
  @Override
  public void cleanup() throws DBException {
    // Get the measurements instance as this is the only client that should
    // count clean up time like an update if client-side buffering is
    // enabled.
    Measurements measurements = Measurements.getMeasurements();
    try {
      long st = System.nanoTime();
      if (bufferedMutator != null) {
        bufferedMutator.close();
      }
      if (currentTable != null) {
        currentTable.close();
      }
      long en = System.nanoTime();
      final String type = clientSideBuffering ? "UPDATE" : "CLEANUP";
      measurements.measure(type, (int) ((en - st) / 1000));
      int threadCount = THREAD_COUNT.decrementAndGet();
      if (threadCount <= 0) {
        // Means we are done so ok to shut down the Connection.
        synchronized (THREAD_COUNT) {
          if (connection != null) {
            connection.close();
            connection = null;
          }
        }
      }
    } catch (IOException e) {
      throw new DBException(e);
    }
  }

  public void getHTable(String table) throws IOException {
    final TableName tName = TableName.valueOf(table);
    this.currentTable = connection.getTable(tName);
    if (clientSideBuffering) {
      final BufferedMutatorParams p = new BufferedMutatorParams(tName);
      p.writeBufferSize(writeBufferSize);
      this.bufferedMutator = connection.getBufferedMutator(p);
    }
  }

  /**
   * Read a record from the database. Each field/value pair from the result will be stored in a
   * HashMap.
   * @param table The name of the table
   * @param key The record key of the record to read.
   * @param fields The list of fields to read, or null for all of them
   * @param result A HashMap of field/value pairs for the result
   * @return Zero on success, a non-zero error code on error
   */
  public Status read(String table, String key, Set<String> fields,
      Map<String, ByteIterator> result) {

    // if this is a "new" table, init HTable object. Else, use existing one
    if (!tableName.equals(table)) {
      currentTable = null;
      try {
        getHTable(table);
        tableName = table;
      } catch (IOException e) {
        System.err.println("Error accessing HBase table: " + e);
        return Status.ERROR;
      }
    }

    UserTestData userTestData;
    try {
      if (debug) {
        System.out.println("Doing read from HBase columnfamily " + columnFamily);
        System.out.println("Doing read for key: " + key);
      }

      GetRowParam.Builder getBuilder = GetRowParam.of(key);
      getBuilder.family(columnFamily);
      if (fields != null) {
        for (String field : fields) {
          getBuilder.qualifier(field);
        }
      }
      userTestData = tableTemplate.getRow(getBuilder.build(), UserTestData.class);
      if (debug) {
        System.out.println(userTestData);
      }
    } catch (ConcurrentModificationException e) {
      // do nothing for now...need to understand HBase concurrency model better
      return Status.ERROR;
    } catch (Exception e) {
      if (debug) {
        System.err.println("Error doing get: " + e);
      }
      return Status.ERROR;
    }

    if (userTestData == null) {
      return Status.NOT_FOUND;
    }

    return Status.OK;
  }

  /**
   * Perform a range scan for a set of records in the database. Each field/value pair from the
   * result will be stored in a HashMap.
   * @param table The name of the table
   * @param startkey The record key of the first record to read.
   * @param recordcount The number of records to read
   * @param fields The list of fields to read, or null for all of them
   * @param result A Vector of HashMaps, where each HashMap is a set field/value pairs for one
   *          record
   * @return Zero on success, a non-zero error code on error
   */
  @Override
  public Status scan(String table, String startkey, int recordcount, Set<String> fields,
      Vector<HashMap<String, ByteIterator>> result) {
    // if this is a "new" table, init HTable object. Else, use existing one
    if (!tableName.equals(table)) {
      currentTable = null;
      try {
        getHTable(table);
        tableName = table;
      } catch (IOException e) {
        System.err.println("Error accessing HBase table: " + e);
        return Status.ERROR;
      }
    }
    ScanParams.Builder scanParms = ScanParams.of();
    scanParms.startRow(startkey);
    scanParms.caching(recordcount);
    scanParms.limit(recordcount);

    if (this.usePageFilter) {
      scanParms.filter((IHBaseFilter<Filter>) () -> new PageFilter(recordcount));
    }
    scanParms.familyName(columnFamily);
    if (fields != null) {
      for (String field : fields) {
        scanParms.columnName(field);
      }
    }

    // get results
    try {
      List<UserTestData> testDataList = tableTemplate.scan(scanParms.build(), UserTestData.class);
      System.out.println("Found " + testDataList.size() + " user test data");
    } catch (Exception e) {
      if (debug) {
        System.out.println("Error in getting/parsing scan result: " + e);
      }
      return Status.ERROR;
    }

    return Status.OK;
  }

  /**
   * Update a record in the database. Any field/value pairs in the specified values HashMap will be
   * written into the record with the specified record key, overwriting any existing values with the
   * same field name.
   * @param table The name of the table
   * @param key The record key of the record to write
   * @param values A HashMap of field/value pairs to update in the record
   * @return Zero on success, a non-zero error code on error
   */
  @Override
  public Status update(String table, String key, Map<String, ByteIterator> values) {
    // if this is a "new" table, init HTable object. Else, use existing one
    if (!tableName.equals(table)) {
      currentTable = null;
      try {
        getHTable(table);
        tableName = table;
      } catch (IOException e) {
        System.err.println("Error accessing HBase table: " + e);
        return Status.ERROR;
      }
    }

    if (debug) {
      System.out.println("Setting up put for key: " + key);
    }

    Map<String, Object> data = new HashMap<>(values.size());
    for (Map.Entry<String, ByteIterator> entry : values.entrySet()) {
      data.put("cf:" + entry.getKey(), entry.getValue().toString());
    }

    try {
      if (clientSideBuffering) {
        // removed Preconditions.checkNotNull, which throws NPE, in favor of NPE on next line
        // todo buffer
        tableTemplate.save(tableName, key, data);
      } else {
        tableTemplate.save(tableName, key, data);
      }
    } catch (ConcurrentModificationException e) {
      // do nothing for now...hope this is rare
      return Status.ERROR;
    } catch (Exception e) {
      if (debug) {
        System.err.println("Error doing put: " + e);
      }
      return Status.ERROR;
    }

    return Status.OK;
  }

  /**
   * Insert a record in the database. Any field/value pairs in the specified values HashMap will be
   * written into the record with the specified record key.
   * @param table The name of the table
   * @param key The record key of the record to insert.
   * @param values A HashMap of field/value pairs to insert in the record
   * @return Zero on success, a non-zero error code on error
   */
  @Override
  public Status insert(String table, String key, Map<String, ByteIterator> values) {
    return update(table, key, values);
  }

  /**
   * Delete a record from the database.
   * @param table The name of the table
   * @param key The record key of the record to delete.
   * @return Zero on success, a non-zero error code on error
   */
  @Override
  public Status delete(String table, String key) {
    // if this is a "new" table, init HTable object. Else, use existing one
    if (!tableName.equals(table)) {
      currentTable = null;
      try {
        getHTable(table);
        tableName = table;
      } catch (IOException e) {
        System.err.println("Error accessing HBase table: " + e);
        return Status.ERROR;
      }
    }

    if (debug) {
      System.out.println("Doing delete for key: " + key);
    }

    try {
      if (clientSideBuffering) {
        // todo buffer
        // removed Preconditions.checkNotNull, which throws NPE, in favor of NPE on next line
        tableTemplate.delete(tableName, key);
      } else {
        tableTemplate.delete(tableName, key);
      }
    } catch (Exception e) {
      if (debug) {
        System.err.println("Error doing delete: " + e);
      }
      return Status.ERROR;
    }

    return Status.OK;
  }
}
