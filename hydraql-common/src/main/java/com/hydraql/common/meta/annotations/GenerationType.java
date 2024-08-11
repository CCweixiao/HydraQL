package com.hydraql.common.meta.annotations;

import com.hydraql.common.row.RowKeyGenerator;
import com.hydraql.common.row.RowKeyHashingGenerator;
import com.hydraql.common.row.RowKeyNothingGenerator;
import com.hydraql.common.row.RowKeyReverseGenerator;

/**
 * Defines the types of hbase row key generation strategies.
 *
 * @author leojie@apache.org 2024/8/10 22:46
 */
public enum GenerationType {
    /**
     * reverse row key, for example: 123 -> 321
     */
    REVERSING,
    /**
     * Take the first 8 digits of the row key md5 value to find the hash value,
     * and splice it in front of the original key.
     */
    HASHING,
    /**
     * do nothing.
     */
    NOTHING;

    public RowKeyGenerator getRowKeyGenerator() {
         if (this == GenerationType.HASHING) {
            return new RowKeyHashingGenerator();
        } else if (this == GenerationType.REVERSING) {
            return new RowKeyReverseGenerator();
        } else {
            return new RowKeyNothingGenerator();
        }
    }
}
