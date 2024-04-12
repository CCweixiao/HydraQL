package com.hydraql.adapter.hedgedread;

/**
 * @author leojie@apache.org 2024/4/8 19:41
 */
public class HedgedReadFirstOneStrategy extends HedgedReadThresholdStrategy {
    public HedgedReadFirstOneStrategy(int maxThreads) {
        super(0, maxThreads);
    }
}
