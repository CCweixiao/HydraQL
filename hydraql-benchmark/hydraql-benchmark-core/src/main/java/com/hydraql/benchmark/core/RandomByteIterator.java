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

package com.hydraql.benchmark.core;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A ByteIterator that generates a random sequence of bytes.
 */
public class RandomByteIterator extends ByteIterator {
  private final long len;
  private long off;
  private int bufOff;
  private final byte[] buf;

  @Override
  public boolean hasNext() {
    return (off + bufOff) < len;
  }

  private void fillBytesImpl(byte[] buffer, int base) {
    int bytes = ThreadLocalRandom.current().nextInt();

    switch (buffer.length - base) {
      default:
        buffer[base + 5] = (byte) (((bytes >> 25) & 95) + ' ');
      case 5:
        buffer[base + 4] = (byte) (((bytes >> 20) & 63) + ' ');
      case 4:
        buffer[base + 3] = (byte) (((bytes >> 15) & 31) + ' ');
      case 3:
        buffer[base + 2] = (byte) (((bytes >> 10) & 95) + ' ');
      case 2:
        buffer[base + 1] = (byte) (((bytes >> 5) & 63) + ' ');
      case 1:
        buffer[base + 0] = (byte) (((bytes) & 31) + ' ');
      case 0:
        break;
    }
  }

  private void fillBytes() {
    if (bufOff == buf.length) {
      fillBytesImpl(buf, 0);
      bufOff = 0;
      off += buf.length;
    }
  }

  public RandomByteIterator(long len) {
    this.len = len;
    this.buf = new byte[6];
    this.bufOff = buf.length;
    fillBytes();
    this.off = 0;
  }

  public byte nextByte() {
    fillBytes();
    bufOff++;
    return buf[bufOff - 1];
  }

  @Override
  public int nextBuf(byte[] buffer, int bufOffset) {
    int ret;
    if (len - off < buffer.length - bufOffset) {
      ret = (int) (len - off);
    } else {
      ret = buffer.length - bufOffset;
    }
    int i;
    for (i = 0; i < ret; i += 6) {
      fillBytesImpl(buffer, i + bufOffset);
    }
    off += ret;
    return ret + bufOffset;
  }

  @Override
  public long bytesLeft() {
    return len - off - bufOff;
  }

  @Override
  public void reset() {
    off = 0;
  }

  /** Consumes remaining contents of this object, and returns them as a byte array. */
  public byte[] toArray() {
    long left = bytesLeft();
    if (left != (int) left) {
      throw new ArrayIndexOutOfBoundsException("Too much data to fit in one array!");
    }
    byte[] ret = new byte[(int) left];
    int bufOffset = 0;
    while (bufOffset < ret.length) {
      bufOffset = nextBuf(ret, bufOffset);
    }
    return ret;
  }

}
