package com.hydraql.adapter.executor;

/**
 * @author leojie@apache.org 2024/4/8 19:41
 */
class HedgedReadFirstOneStrategy extends HedgedReadThresholdStrategy {
    public HedgedReadFirstOneStrategy(int maxThreads) {
        super(0, maxThreads);
    }
}
