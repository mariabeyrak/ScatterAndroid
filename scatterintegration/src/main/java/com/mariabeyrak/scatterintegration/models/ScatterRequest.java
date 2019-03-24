package com.mariabeyrak.scatterintegration.models;

public class ScatterRequest {
    private String params;
    private @MethodName.Methods
    String methodName;

    public ScatterRequest(String params, @MethodName.Methods String methodName) {
        this.params = params;
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public @MethodName.Methods
    String getMethodName() {
        return methodName;
    }

    @Override
    public String toString() {
        return "ScatterRequest{" +
                "params='" + params + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
