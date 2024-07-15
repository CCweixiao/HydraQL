package com.hydraql.adapter.schema;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.Map;

/**
 * @author leojie 2023/5/17 21:48
 */
public class HTableDescriptorConverter extends BaseHTableDescriptorConverter<HTableDesc, HTableDescriptor> {
    public HTableDescriptorConverter(HTableDesc tableDesc) {
        super(tableDesc);
    }

    @Override
    protected HTableDescriptor doForward(HTableDesc htd) {
        HTableDescriptor td = new HTableDescriptor(TableName.valueOf(htd.getName()));

        if (compareNeedSet(td.getMaxFileSize(), htd.getMaxFileSize())) {
            td.setMaxFileSize(htd.getMaxFileSize());
        }

        if (compareNeedSet(td.getMemStoreFlushSize(), htd.getMemStoreFlushSize())) {
            td.setMemStoreFlushSize(htd.getMemStoreFlushSize());
        }

        if (compareNeedSet(td.isReadOnly(), htd.getReadOnly())) {
            td.setReadOnly(htd.getReadOnly());
        }

        if (compareNeedSet(td.isCompactionEnabled(), htd.getCompactionEnabled())) {
            td.setCompactionEnabled(htd.getCompactionEnabled());
        }

        if (compareNeedSet(td.isNormalizationEnabled(), htd.getNormalizationEnabled())) {
            td.setNormalizationEnabled(htd.getNormalizationEnabled());
        }

        if (compareNeedSet(td.getRegionSplitPolicyClassName(), htd.getRegionSplitPolicyClassName())) {
            td.setRegionSplitPolicyClassName(htd.getRegionSplitPolicyClassName());
        }

        if (compareNeedSet(td.getFlushPolicyClassName(), htd.getFlushPolicyClassName())) {
            td.setFlushPolicyClassName(htd.getFlushPolicyClassName());
        }

        List<BaseColumnFamilyDesc> familyDescList = htd.getColumnFamilyDescList();
        if (familyDescList != null && !familyDescList.isEmpty()) {
            for (BaseColumnFamilyDesc familyDesc : familyDescList) {
                td.addFamily(((ColumnFamilyDesc) familyDesc).convertTo());
            }
        }

        Map<String, String> configuration = htd.getConfiguration();
        if (!configuration.isEmpty()) {
            for (String key : configuration.keySet()) {
                String defaultVal = td.getConfigurationValue(key);
                String newVal = configuration.get(key);
                if (defaultVal == null) {
                    td.setConfiguration(key, newVal);
                    continue;
                }
                if (compareNeedSet(defaultVal, newVal)) {
                    td.setConfiguration(key, newVal);
                }
            }
        }
        Map<String, String> values = htd.getValues();
        if (!values.isEmpty()) {
            for (String key : values.keySet()) {
                String defaultVal = BaseHTableDesc.DEFAULT_VALUES.get(key);
                String newVal = values.get(key);
                if (defaultVal == null) {
                    td.setValue(key, newVal);
                    continue;
                }
                if (compareNeedSet(defaultVal, newVal)) {
                    td.setValue(key, newVal);
                }
            }
        }
        return td;
    }

    @Override
    protected HTableDesc doBackward(HTableDescriptor td) {
        BaseHTableDesc.Builder<HTableDesc> builder = HTableDesc.newBuilder(td.getTableName().getNameAsString());
        builder.setMaxFileSize(td.getMaxFileSize())
                .setMemStoreFlushSize(td.getMemStoreFlushSize())
                .setReadOnly(td.isReadOnly())
                .setDurability(td.getDurability().name())
                .setPriority(td.getPriority())
                .setCompactionEnabled(td.isCompactionEnabled())
                .setNormalizationEnabled(td.isNormalizationEnabled())
                .setRegionSplitPolicyClassName(td.getRegionSplitPolicyClassName())
                .setFlushPolicyClassName(td.getFlushPolicyClassName());

        for (HColumnDescriptor cf : td.getColumnFamilies()) {
            ColumnFamilyDesc cfd = ColumnFamilyDesc.createDefault(cf.getNameAsString()).convertFrom(cf);
            builder.addFamilyDesc(cfd);
        }

        Map<String, String> configuration = td.getConfiguration();
        if (!configuration.isEmpty()) {
            configuration.forEach(builder::setConfiguration);
        }

        Map<ImmutableBytesWritable, ImmutableBytesWritable> values = td.getValues();
        if (!values.isEmpty()) {
            for (ImmutableBytesWritable key : values.keySet()) {
                builder.setValue(Bytes.toString(key.get()), Bytes.toString(values.get(key).get()));
            }
        }
        return builder.build();
    }
}
