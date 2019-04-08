package com.mariabeyrak.scatterintegration.models.response;

public enum ResponseCodeInfo {
    SUCCESS(ResponseType.SUCCESS, ResponseCode.SUCCESS),
    SIGNATURE_REJECTED(ResponseType.SIGNATURE_REJECTED, ResponseCode.NO_SIGNATURE),
    MALICIOUS(ResponseType.MALICIOUS, ResponseCode.FORBIDDEN),
    UNKNOWN_ERROR(ResponseType.UNKNOWN_ERROR, ResponseCode.UNKNOWN_ERROR);

    private ResponseType message;
    private ResponseCode code;

    ResponseCodeInfo(ResponseType message, ResponseCode code) {
        this.message = message;
        this.code = code;
    }

    public ResponseType getMessage() {
        return message;
    }

    public ResponseCode getCode() {
        return code;
    }
}
