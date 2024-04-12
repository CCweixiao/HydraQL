package com.hydraql.adapter.hedgedread;

import com.hydraql.adapter.service.AbstractHTableService;

/**
 * @author leojie@apache.org 2024/4/8 19:41
 */
public class HedgedReadFirstOneStrategy extends HedgedReadThresholdStrategy {
    public HedgedReadFirstOneStrategy(AbstractHTableService tableService) {
        super(0, tableService);
    }
}
