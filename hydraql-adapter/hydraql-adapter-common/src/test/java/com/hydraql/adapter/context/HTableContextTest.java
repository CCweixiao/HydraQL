package com.hydraql.adapter.context;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author leojie 2024/4/7 15:51
 */
public class HTableContextTest {
    @Test
    public void testInit() {
        HTableContext tableContext = HTableContext.builder("test_table")
                .batchSaveOptions(BatchSaveOptions.builder()
                        .autoFlush(true)
                        .writeBufferSize(1024).build()).build();
        Assert.assertNotNull(tableContext);
    }
}
