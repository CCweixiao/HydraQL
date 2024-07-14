package com.hydraql.adapter.schema;

import com.hydraql.common.util.StringUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author leojie 2023/5/17 21:48
 */

public class HTableDescriptorConverter extends BaseHTableDescriptorConverter<HTableDesc, TableDescriptor> {
    public HTableDescriptorConverter(HTableDesc tableDesc) {
        super(tableDesc);
    }

    @Override
    protected TableDescriptor doForward(HTableDesc htd) {
        TableName tableName = TableName.valueOf(htd.getName());
        TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(tableName);

        if (htd.getMaxFileSize() != null) {
            builder.setMaxFileSize(htd.getMaxFileSize());
        }

        if (htd.getMemStoreFlushSize() != null) {
            builder.setMemStoreFlushSize(htd.getMemStoreFlushSize());
        }

        if (htd.getReadOnly() != null) {
            builder.setReadOnly(htd.getReadOnly());
        }

        if (htd.getCompactionEnabled() != null) {
            builder.setCompactionEnabled(htd.getCompactionEnabled());
        }

        if (htd.getNormalizationEnabled() != null) {
            builder.setNormalizationEnabled(htd.getNormalizationEnabled());
        }

        if (htd.getMergeEnabled() != null) {
            builder.setMergeEnabled(htd.getMergeEnabled());
        }

        if (htd.getSplitEnabled() != null) {
            builder.setSplitEnabled(htd.getSplitEnabled());
        }

        if (StringUtil.isNotBlank(htd.getRegionSplitPolicyClassName())) {
            builder.setRegionSplitPolicyClassName(htd.getRegionSplitPolicyClassName());
        }

        if (StringUtil.isNotBlank(htd.getFlushPolicyClassName())) {
            builder.setRegionSplitPolicyClassName(htd.getRegionSplitPolicyClassName());
        }

        List<BaseColumnFamilyDesc> familyDescList = htd.getColumnFamilyDescList();
        if (!familyDescList.isEmpty()) {
            for (BaseColumnFamilyDesc familyDesc : familyDescList) {
                builder.setColumnFamily(((ColumnFamilyDesc) familyDesc).convertTo());
            }
        }

        Map<String, String> values = htd.getValues();
        if (!values.isEmpty()) {
            values.forEach(builder::setValue);
        }

        return builder.build();
    }

    @Override
    protected HTableDesc doBackward(TableDescriptor td) {
        List<ColumnFamilyDesc> columnFamilyDescList =
                Arrays.stream(td.getColumnFamilies()).map(cd -> ColumnFamilyDesc.createDefault(cd.getNameAsString())
                        .convertFrom(cd)).collect(Collectors.toList());
        BaseHTableDesc.Builder<HTableDesc> builder = HTableDesc.newBuilder(td.getTableName().getNameAsString());
        builder.maxFileSize(td.getMaxFileSize())
                .memStoreFlushSize(td.getMemStoreFlushSize())
                .readOnly(td.isReadOnly())
                .regionSplitPolicyClassName(td.getRegionSplitPolicyClassName())
                .flushPolicyClassName(td.getFlushPolicyClassName())
                .compactionEnabled(td.isCompactionEnabled())
                .mergeEnabled(td.isMergeEnabled())
                .splitEnabled(td.isSplitEnabled());

        for (ColumnFamilyDesc columnFamilyDesc : columnFamilyDescList) {
            builder.addFamilyDesc(columnFamilyDesc);
        }
        Map<Bytes, Bytes> values = td.getValues();
        if (values != null && !values.isEmpty()) {
            for (Bytes key : values.keySet()) {
                builder.setValue(Bytes.toString(key.get()), Bytes.toString(values.get(key).get()));
            }
        }
        return builder.build();
    }

}
