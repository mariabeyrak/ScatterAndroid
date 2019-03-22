package com.mariabeyrak.scatterintegration.models.requests.MsgTransaction;

import com.mariabeyrak.scatterintegration.models.requests.ScatterRequestParams;

public class MsgTransactionRequestParams extends ScatterRequestParams {
    private String data;
    private String publicKey;
    private String isHash;
    private String whatfor;

    public MsgTransactionRequestParams(String data, String publicKey, String isHash, String whatfor) {
        this.data = data;
        this.publicKey = publicKey;
        this.isHash = isHash;
        this.whatfor = whatfor;
    }

    public String getData() {
        return data;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getIsHash() {
        return isHash;
    }

    public String getWhatfor() {
        return whatfor;
    }

    @Override
    public String toString() {
        return "MsgTransactionRequestParams{" +
                "data='" + data + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", isHash='" + isHash + '\'' +
                ", whatfor='" + whatfor + '\'' +
                '}';
    }
}


