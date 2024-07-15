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

package com.hydraql.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * @author leojie 2022/12/3 20:12
 */
public final class NumberUtil {
  private NumberUtil() {

  }

  public static BigInteger toBigInteger(String numberStr) {
    if (StringUtil.isBlank(numberStr)) {
      return BigInteger.ZERO;
    }

    try {
      final Number number = parseNumber(numberStr);
      if (number instanceof BigDecimal) {
        return ((BigDecimal) number).toBigInteger();
      } else {
        return new BigInteger(number.toString());
      }
    } catch (Exception ignore) {
    }

    return new BigInteger(numberStr);
  }

  public static BigDecimal toBigDecimal(String numberStr) {
    if (StringUtil.isBlank(numberStr)) {
      return BigDecimal.ZERO;
    }

    try {
      final Number number = parseNumber(numberStr);
      if (number instanceof BigDecimal) {
        return (BigDecimal) number;
      } else {
        return new BigDecimal(number.toString());
      }
    } catch (Exception ignore) {
    }

    return new BigDecimal(numberStr);
  }

  public static Number parseNumber(String numberStr) throws NumberFormatException {
    if (StringUtil.startWithIgnoreCase(numberStr, "0x")) {
      // 0x04表示16进制数
      return Long.parseLong(numberStr.substring(2), 16);
    }

    try {
      final NumberFormat format = NumberFormat.getInstance();
      if (format instanceof DecimalFormat) {
        // issue#1818@Github
        // 当字符串数字超出double的长度时，会导致截断，此处使用BigDecimal接收
        ((DecimalFormat) format).setParseBigDecimal(true);
      }
      return format.parse(numberStr);
    } catch (ParseException e) {
      final NumberFormatException nfe = new NumberFormatException(e.getMessage());
      nfe.initCause(e);
      throw nfe;
    }
  }
}
