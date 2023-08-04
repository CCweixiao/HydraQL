package com.hydraql.common.mapper;

import java.util.List;

/**
 * <p>HBase's data query result set maps the field properties of POJO objects.</p>
 *
 * @author leo.jie (leojie1314@gmail.com)
 */
public interface RowMapper<T> {
    /**
     * convert function
     *
     * @param r result
     * @param rowNum row number
     * @return pojo object
     * @throws Exception throw error
     */
    <R> T mapRow(R r, int rowNum) throws Exception;

    default <R> List<T> mapRowWithVersions(R r, int rowNum) throws Exception {
        return null;
    }
}
