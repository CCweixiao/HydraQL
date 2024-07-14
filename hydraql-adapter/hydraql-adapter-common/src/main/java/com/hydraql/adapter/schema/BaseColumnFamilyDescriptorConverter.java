package com.hydraql.adapter.schema;

import com.hydraql.common.lang.Converter;

import java.util.Objects;

/**
 * @author leojie 2023/5/17 22:39
 */
public abstract class BaseColumnFamilyDescriptorConverter<CF extends BaseColumnFamilyDesc, CD> extends Converter<CF, CD> {
    private final CF columnFamilyDesc;
    public BaseColumnFamilyDescriptorConverter(CF columnFamilyDesc) {
        this.columnFamilyDesc = columnFamilyDesc;
    }

    protected CD convertTo() {
        return this.convert(this.columnFamilyDesc);
    }

    protected CF convertFrom(CD columnFamilyDescriptor) {
        return this.reverse().convert(columnFamilyDescriptor);
    }

    protected boolean compareNeedSet(Object source, Object target) {
        if (target == null) {
            return false;
        }
        return !Objects.equals(source, target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BaseColumnFamilyDescriptorConverter<?, ?> that = (BaseColumnFamilyDescriptorConverter<?, ?>) o;
        return columnFamilyDesc.equals(that.columnFamilyDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnFamilyDesc);
    }
}
