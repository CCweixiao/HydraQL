package com.hydraql.common.callback;

/**
 * <p>A functional interface for defining table data operations.</p>
 *
 * @author leo.jie (leojie1314@gmail.com)
 */
public interface TableCallback<T, HT> {
    /**
     * <p>do action in table.</p>
     *
     * @param table The object of HTable
     * @return result
     * @throws Throwable throw error
     */
    T execute(HT table) throws Throwable;
}
