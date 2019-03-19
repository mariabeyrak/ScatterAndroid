package com.mariabeyrak.scatterintegration.models.requests.Transaction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transaction {
    private String expiration;
    @SerializedName("ref_block_num")
    private Integer refBlockNum;
    @SerializedName("ref_block_prefix")
    private Integer refBlockPrefix;
    @SerializedName("max_net_usage_words")
    private Integer maxNetUsageWords;
    @SerializedName("max_cpu_usage_ms")
    private Integer maxCpuUsageMs;
    @SerializedName("delay_sec")
    private Integer delaySec;
    @SerializedName("context_free_actions")
    private List<Object> contextFreeActions = null;
    private List<Action> actions = null;
    @SerializedName("transaction_extensions")
    private List<Object> transactionExtensions = null;

    public Transaction(String expiration, Integer refBlockNum, Integer refBlockPrefix, Integer maxNetUsageWords, Integer maxCpuUsageMs, Integer delaySec, List<Object> contextFreeActions, List<Action> actions, List<Object> transactionExtensions) {
        this.expiration = expiration;
        this.refBlockNum = refBlockNum;
        this.refBlockPrefix = refBlockPrefix;
        this.maxNetUsageWords = maxNetUsageWords;
        this.maxCpuUsageMs = maxCpuUsageMs;
        this.delaySec = delaySec;
        this.contextFreeActions = contextFreeActions;
        this.actions = actions;
        this.transactionExtensions = transactionExtensions;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public Integer getRefBlockNum() {
        return refBlockNum;
    }

    public void setRefBlockNum(Integer refBlockNum) {
        this.refBlockNum = refBlockNum;
    }

    public Integer getRefBlockPrefix() {
        return refBlockPrefix;
    }

    public void setRefBlockPrefix(Integer refBlockPrefix) {
        this.refBlockPrefix = refBlockPrefix;
    }

    public Integer getMaxNetUsageWords() {
        return maxNetUsageWords;
    }

    public void setMaxNetUsageWords(Integer maxNetUsageWords) {
        this.maxNetUsageWords = maxNetUsageWords;
    }

    public Integer getMaxCpuUsageMs() {
        return maxCpuUsageMs;
    }

    public void setMaxCpuUsageMs(Integer maxCpuUsageMs) {
        this.maxCpuUsageMs = maxCpuUsageMs;
    }

    public Integer getDelaySec() {
        return delaySec;
    }

    public void setDelaySec(Integer delaySec) {
        this.delaySec = delaySec;
    }

    public List<Object> getContextFreeActions() {
        return contextFreeActions;
    }

    public void setContextFreeActions(List<Object> contextFreeActions) {
        this.contextFreeActions = contextFreeActions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Object> getTransactionExtensions() {
        return transactionExtensions;
    }

    public void setTransactionExtensions(List<Object> transactionExtensions) {
        this.transactionExtensions = transactionExtensions;
    }

}