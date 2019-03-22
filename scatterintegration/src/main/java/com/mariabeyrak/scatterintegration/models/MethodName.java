package com.mariabeyrak.scatterintegration.models;

import com.google.gson.annotations.SerializedName;

public enum MethodName {
    @SerializedName("callbackGetEosAccount")
    GetEosAccount("callbackGetEosAccount"),
    @SerializedName("callbackRequestSignature")
    RequestSignature("callbackRequestSignature"),
    @SerializedName("callbackRequestMsgSignature")
    RequestMsgSignature("callbackRequestMsgSignature");

    private String method;

    MethodName(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
