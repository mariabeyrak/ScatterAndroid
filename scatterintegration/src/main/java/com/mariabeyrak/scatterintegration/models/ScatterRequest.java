package com.mariabeyrak.scatterintegration.models;

public class ScatterRequest {
    private String params;
    private MethodName methodName;

    public ScatterRequest(String params, MethodName methodName) {
        this.params = params;
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public MethodName getMethodName() {
        return methodName;
    }

    @Override
    public String toString() {
        return "ScatterRequest{" +
                "params='" + params + '\'' +
                ", methodName='" + methodName.getMethod() + '\'' +
                '}';
    }
}
