package com.mariabeyrak.scatter.models.response;

public enum ResponseType {
    SUCCESS("success"),
    UNKNOWN_ERROR("error"),
    MALICIOUS("malicious"),
    LOCKED("locked"),
    PROMPT_CLOSED("prompt_closed"),
    UPGRADE_REQUIRED("upgrade_required"),
    SIGNATURE_REJECTED("signature_rejected"),
    IDENTITY_MISSING("identity_missing"),
    ACCOUNT_MISSING("account_missing"),
    MALFORMED_REQUIREMENTS("malformed_requirements"),
    NO_NETWORK("no_network");

    private String message;

    ResponseType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}