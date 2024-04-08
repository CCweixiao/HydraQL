package com.hydraql.adapter.executor;

/**
 * @author leojie@apache.org 2024/4/8 19:41
 */
public class FirstOneHedgedReadStrategy extends ThresholdHedgedReadStrategy {
    public FirstOneHedgedReadStrategy(int maxThreads) {
        super(0, maxThreads);
    }
}
