package com.github.CCweixiao.hbase.sdk.common.query;

import com.github.CCweixiao.hbase.sdk.common.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leojie 2023/7/20 11:09
 */
public class GetParams {
    private final String rowKey;
    private final String familyName;
    private final List<String> columnNames;
    private final TimeRange timeRange;
    private final int versions;

    public GetParams(Builder builder) {
        this.rowKey = builder.rowKey;
        this.familyName = builder.familyName;
        this.columnNames = builder.columnNames;
        this.timeRange = builder.timeRange;
        this.versions = builder.versions;
    }

    public static class TimeRange {
        private final long minTimestamp;
        private final long maxTimestamp;

        public TimeRange(long minTimestamp, long maxTimestamp) {
            if (minTimestamp >= 0L && maxTimestamp >= 0L) {
                if (maxTimestamp < minTimestamp) {
                    throw new IllegalArgumentException("maxTimestamp is smaller than minTimestamp");
                }
            } else {
                throw new IllegalArgumentException("Timestamp cannot be negative. minTimestamp:" + minTimestamp
                        + ", maxTimestamp:" + maxTimestamp);
            }

            this.minTimestamp = minTimestamp;
            this.maxTimestamp = maxTimestamp;
        }

        public long getMinTimestamp() {
            return minTimestamp;
        }

        public long getMaxTimestamp() {
            return maxTimestamp;
        }

        @Override
        public String toString() {
            return "[" +
                    "minTimestamp=" + minTimestamp +
                    ", maxTimestamp=" + maxTimestamp +
                    ']';
        }
    }

    public static class Builder {
        private String rowKey;
        private String familyName;
        private List<String> columnNames;
        private TimeRange timeRange;
        private int versions;

        private Builder() {
        }

        public Builder of(String rowKey) {
            if (StringUtil.isBlank(rowKey)) {
                throw new IllegalArgumentException("The rowKey must be specified in the get query.");
            }
            this.rowKey = rowKey;
            return this;
        }

        public Builder familyName(String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder columnNames(List<String> columnNames) {
            this.columnNames = columnNames;
            return this;
        }

        public Builder columnName(String columnName) {
            if (this.columnNames == null) {
                this.columnNames = new ArrayList<>();
            }
            this.columnNames.add(columnName);
            return this;
        }

        public Builder withTimeRange(long min, long max) {
            this.timeRange = new TimeRange(min, max);
            return this;
        }

        public Builder withTimestamp(long ts) {
            if (ts < 0 || ts == Long.MAX_VALUE) {
                throw new IllegalArgumentException("invalid ts:" + ts);
            }
            this.timeRange = new TimeRange(ts, ts + 1);
            return this;
        }

        public Builder versions(int versions) {
            if (versions <= 0) {
                throw new IllegalArgumentException("versions must be positive");
            }
            this.versions = versions;
            return this;
        }

        public Builder readAllVersions() {
            this.versions = Integer.MAX_VALUE;
            return this;
        }

        public GetParams build() {
            return new GetParams(this);
        }
    }

    public static Builder builder(String rowKey) {
        return new GetParams.Builder().of(rowKey);
    }

    public String getRowKey() {
        return rowKey;
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public int getVersions() {
        return versions;
    }

    public boolean onlyFamily() {
        return FamilyQualifierUtil.familyNameOnly(this.getFamilyName(), this.getColumnNames());
    }

    public boolean familyWithQualifiers() {
        return FamilyQualifierUtil.familyAndColumnNames(this.getFamilyName(), this.getColumnNames());
    }

    @Override
    public String toString() {
        return "GetParams{" +
                "rowKey='" + rowKey + '\'' +
                ", familyName='" + familyName + '\'' +
                ", columnNames=" + columnNames +
                ", timeRange=" + timeRange +
                ", versions=" + versions +
                '}';
    }
}
