package com.mariabeyrak.scatter.models.requests.Transaction.request;

import com.google.gson.annotations.SerializedName;

public class Transaction {
    private String expiration;
    @SerializedName("ref_block_num")
    private long refBlockNum;
    @SerializedName("ref_block_prefix")
    private long refBlockPrefix;
    @SerializedName("max_net_usage_words")
    private int maxNetUsageWords;
    @SerializedName("max_cpu_usage_ms")
    private int maxCpuUsageMs;
    @SerializedName("delay_sec")
    private int delaySec;
    @SerializedName("context_free_actions")
    private Action[] contextFreeActions;
    private Action[] actions;

    public Transaction(String expiration, long refBlockNum, long refBlockPrefix, int maxNetUsageWords, int maxCpuUsageMs, int delaySec, Action[] contextFreeActions, Action[] actions) {
        this.expiration = expiration;
        this.refBlockNum = refBlockNum;
        this.refBlockPrefix = refBlockPrefix;
        this.maxNetUsageWords = maxNetUsageWords;
        this.maxCpuUsageMs = maxCpuUsageMs;
        this.delaySec = delaySec;
        this.contextFreeActions = contextFreeActions;
        this.actions = actions;
    }

    public String getExpiration() {
        return expiration;
    }

    public long getRefBlockNum() {
        return refBlockNum;
    }

    public long getRefBlockPrefix() {
        return refBlockPrefix;
    }

    public int getMaxNetUsageWords() {
        return maxNetUsageWords;
    }

    public int getMaxCpuUsageMs() {
        return maxCpuUsageMs;
    }

    public int getDelaySec() {
        return delaySec;
    }

    public Action[] getContextFreeActions() {
        return contextFreeActions;
    }

    public Action[] getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "expiration='" + expiration + '\'' +
                ", refBlockNum=" + refBlockNum +
                ", refBlockPrefix=" + refBlockPrefix +
                ", maxNetUsageWords=" + maxNetUsageWords +
                ", maxCpuUsageMs=" + maxCpuUsageMs +
                ", delaySec=" + delaySec +
                ", contextFreeActions=" + contextFreeActions +
                ", actions=" + actions +
                '}';
    }
}