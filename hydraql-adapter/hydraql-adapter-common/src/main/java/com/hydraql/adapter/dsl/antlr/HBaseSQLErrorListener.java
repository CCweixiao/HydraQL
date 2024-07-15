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

package com.hydraql.adapter.dsl.antlr;

import com.hydraql.common.exception.HBaseSqlSyntaxException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

/**
 * @author leojie 2023/7/29 08:28
 */
public class HBaseSQLErrorListener extends BaseErrorListener {
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
      int charPositionInLine, String msg, RecognitionException e) {
    TokenStream tokenStream = ((Parser) recognizer).getInputStream();
    String hql = tokenStream.getText();
    String underLineError = underlineError(hql, (Token) offendingSymbol, line, charPositionInLine);
    throw new HBaseSqlSyntaxException("\n" + underLineError + "line " + line + ",char position at "
        + charPositionInLine + ", typo: " + msg);
  }

  protected String underlineError(String hql, Token offendingToken, int line,
      int charPositionInLine) {
    String[] lines = hql.split("\n");
    String errorLine = lines[line - 1];
    // System.err.println(errorLine);
    StringBuilder sb = new StringBuilder(errorLine);
    sb.append("\n");
    for (int i = 0; i < charPositionInLine; i++) {
      sb.append(" ");
    }

    int start = offendingToken.getStartIndex();
    int stop = offendingToken.getStopIndex();
    if (stop < start) {
      stop = start;
    }
    if (start >= 0) {
      for (int i = start; i <= stop; i++) {
        sb.append("^");
      }
    }
    sb.append("\n");
    return sb.toString();
  }

}
