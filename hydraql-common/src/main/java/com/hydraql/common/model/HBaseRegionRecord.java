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

package com.hydraql.common.model;

import java.io.Serializable;

/**
 * @author leojie 2021/6/13 4:15 下午
 */
public class HBaseRegionRecord implements Serializable {
  private static final long serialVersionUID = 1L;

  private String namespaceName;
  private String tableName;
  private String regionName;
  private String encodedRegionName;
  private String startCode;
  private String regionServer;
  private String storeFileSizeTag;
  private double storeFileSize;
  private String uncompressedStoreFileSizeTag;
  private double uncompressedStoreFileSize;
  private int numStoreFiles;
  private String memStoreSizeTag;
  private double memStoreSize;
  private float locality;
  private String startKey;
  private String endKey;

  public String getNamespaceName() {
    return namespaceName;
  }

  public void setNamespaceName(String namespaceName) {
    this.namespaceName = namespaceName;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public String getEncodedRegionName() {
    return encodedRegionName;
  }

  public void setEncodedRegionName(String encodedRegionName) {
    this.encodedRegionName = encodedRegionName;
  }

  public String getStartCode() {
    return startCode;
  }

  public void setStartCode(String startCode) {
    this.startCode = startCode;
  }

  public String getRegionServer() {
    return regionServer;
  }

  public void setRegionServer(String regionServer) {
    this.regionServer = regionServer;
  }

  public String getStoreFileSizeTag() {
    return storeFileSizeTag;
  }

  public void setStoreFileSizeTag(String storeFileSizeTag) {
    this.storeFileSizeTag = storeFileSizeTag;
  }

  public double getStoreFileSize() {
    return storeFileSize;
  }

  public void setStoreFileSize(double storeFileSize) {
    this.storeFileSize = storeFileSize;
  }

  public String getUncompressedStoreFileSizeTag() {
    return uncompressedStoreFileSizeTag;
  }

  public void setUncompressedStoreFileSizeTag(String uncompressedStoreFileSizeTag) {
    this.uncompressedStoreFileSizeTag = uncompressedStoreFileSizeTag;
  }

  public double getUncompressedStoreFileSize() {
    return uncompressedStoreFileSize;
  }

  public void setUncompressedStoreFileSize(double uncompressedStoreFileSize) {
    this.uncompressedStoreFileSize = uncompressedStoreFileSize;
  }

  public int getNumStoreFiles() {
    return numStoreFiles;
  }

  public void setNumStoreFiles(int numStoreFiles) {
    this.numStoreFiles = numStoreFiles;
  }

  public String getMemStoreSizeTag() {
    return memStoreSizeTag;
  }

  public void setMemStoreSizeTag(String memStoreSizeTag) {
    this.memStoreSizeTag = memStoreSizeTag;
  }

  public double getMemStoreSize() {
    return memStoreSize;
  }

  public void setMemStoreSize(double memStoreSize) {
    this.memStoreSize = memStoreSize;
  }

  public float getLocality() {
    return locality;
  }

  public void setLocality(float locality) {
    this.locality = locality;
  }

  public String getStartKey() {
    return startKey;
  }

  public void setStartKey(String startKey) {
    this.startKey = startKey;
  }

  public String getEndKey() {
    return endKey;
  }

  public void setEndKey(String endKey) {
    this.endKey = endKey;
  }
}
