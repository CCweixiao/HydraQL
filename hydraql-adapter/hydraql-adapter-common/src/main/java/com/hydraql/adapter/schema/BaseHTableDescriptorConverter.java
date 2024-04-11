package com.hydraql.adapter.schema;

import com.hydraql.common.lang.Converter;

import java.util.Objects;

/**
 * @author leojie 2023/5/16 23:17
 */
public abstract class BaseHTableDescriptorConverter<D extends BaseHTableDesc, S> extends Converter<D, S> {
    private final D hTableDesc;
    public BaseHTableDescriptorConverter(D hTableDesc) {
        this.hTableDesc = hTableDesc;
    }

    protected S convertFor() {
        return this.convert(this.hTableDesc);
    }

    protected D convertTo(S tableDescriptor) {
        return this.reverse().convert(tableDescriptor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseHTableDescriptorConverter<?, ?> that = (BaseHTableDescriptorConverter<?, ?>) o;
        return hTableDesc.equals(that.hTableDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hTableDesc);
    }
}
