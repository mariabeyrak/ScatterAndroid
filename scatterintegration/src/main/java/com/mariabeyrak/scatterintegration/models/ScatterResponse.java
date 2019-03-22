package com.mariabeyrak.scatterintegration.models;

public class ScatterResponse {
    private String methodName;
    private ResponseCodeInfo responseCodeInfo;
    private String dataInJson;

    public ScatterResponse(String methodName, ResponseCodeInfo responseCodeInfo, String dataInJson) {
        this.methodName = methodName;
        this.responseCodeInfo = responseCodeInfo;
        this.dataInJson = dataInJson;
    }

    public String formatResponse() {
        return methodName + "('{\"message\":\"" + responseCodeInfo.getMessage() + "\",\"data\":" + dataInJson + ",\"code\":" + responseCodeInfo.getCode() + "}')";
    }

}
