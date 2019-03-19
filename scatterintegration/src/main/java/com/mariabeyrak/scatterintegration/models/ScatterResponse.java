package com.mariabeyrak.scatterintegration.models;

public class ScatterResponse {
    private String methodName;
    private ResponseCodeInfo responseCodeInfo;
    private String data;

    public ScatterResponse(String methodName, ResponseCodeInfo responseCodeInfo, String data) {
        this.methodName = methodName;
        this.responseCodeInfo = responseCodeInfo;
        this.data = data;
    }

    public String formatResponse() {
        return methodName + "('{\"message\":\"" + responseCodeInfo.getMessage() + "\",\"data\":\"" + data + "\",\"code\":" + responseCodeInfo.getCode() + "}')";
    }

}
