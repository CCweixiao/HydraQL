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

package com.hydraql.common.lang;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author leojie 2023/7/21 15:18
 */
public abstract class Converter<A, B> implements Function<A, B> {
  private transient Converter<B, A> reverse;

  @Override
  public final B apply(A a) {
    return convert(a);
  }

  public final B convert(A a) {
    return correctedDoForward(a);
  }

  B correctedDoForward(A a) {
    return unsafeDoForward(a);
  }

  A correctedDoBackward(B b) {
    return unsafeDoBackward(b);
  }

  private B unsafeDoForward(A a) {
    return doForward(a);
  }

  private A unsafeDoBackward(B b) {
    return doBackward(b);
  }

  protected abstract B doForward(A a);

  protected abstract A doBackward(B b);

  public Converter<B, A> reverse() {
    Converter<B, A> result = reverse;
    return (result == null) ? reverse = new ReverseConverter<>(this) : result;
  }

  private static final class ReverseConverter<A, B> extends Converter<B, A>
      implements Serializable {
    final Converter<A, B> original;

    ReverseConverter(Converter<A, B> original) {
      this.original = original;
    }

    /*
     * These gymnastics are a little confusing. Basically this class has neither legacy nor
     * non-legacy behavior; it just needs to let the behavior of the backing converter shine
     * through. So, we override the correctedDo* methods, after which the do* methods should never
     * be reached.
     */

    @Override
    protected A doForward(B b) {
      throw new AssertionError();
    }

    @Override
    protected B doBackward(A a) {
      throw new AssertionError();
    }

    @Override
    A correctedDoForward(B b) {
      return original.correctedDoBackward(b);
    }

    @Override
    B correctedDoBackward(A a) {
      return original.correctedDoForward(a);
    }

    @Override
    public Converter<A, B> reverse() {
      return original;
    }

    @Override
    public boolean equals(Object object) {
      if (object instanceof ReverseConverter) {
        ReverseConverter<?, ?> that = (ReverseConverter<?, ?>) object;
        return this.original.equals(that.original);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return ~original.hashCode();
    }

    @Override
    public String toString() {
      return original + ".reverse()";
    }

    private static final long serialVersionUID = 0L;
  }
}
