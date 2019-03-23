package com.mariabeyrak.scatterintegration.models;

public enum ResponseCodeInfo {
    SUCCESS("success", 0),
    ERROR("error", 1);

    private String message;
    private Integer code;

    ResponseCodeInfo(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
