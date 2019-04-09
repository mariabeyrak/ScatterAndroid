package com.mariabeyrak.scatter.models;

import com.mariabeyrak.scatter.models.response.ResponseCodeInfo;

public class ScatterResponse {
    private String methodName;
    private ResponseCodeInfo responseCodeInfo;
    private String dataInJson;

    public ScatterResponse(String methodName, ResponseCodeInfo responseCodeInfo, String dataInJson) {
        this.methodName = methodName;
        this.responseCodeInfo = responseCodeInfo;
        this.dataInJson = dataInJson;
    }

    public String formatSuccessResponse() {
        return methodName + "('{\"message\":\"" + responseCodeInfo.getMessage().getMessage() + "\",\"data\":" + dataInJson + ",\"code\":" + responseCodeInfo.getCode().getCode() + "}')";
    }

    public String formatErrorResponse(String messageToUser) {
        return methodName + "('{\"code\":" + responseCodeInfo.getCode().getCode() + ",\"message\":\"" + messageToUser + "\",\"isError\":true,\"type\":\"" + responseCodeInfo.getMessage().getMessage() + "\"}')";
    }

}
