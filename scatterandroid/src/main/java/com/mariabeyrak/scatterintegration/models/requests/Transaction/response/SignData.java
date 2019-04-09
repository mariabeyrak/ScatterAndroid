package com.mariabeyrak.scatterintegration.models.requests.Transaction.response;

import java.util.Arrays;

public class SignData {
    private String[] signatures;
    private ReturnedFields returnedFields;

    public SignData(String[] signatures, ReturnedFields returnedFields) {
        this.signatures = signatures;
        this.returnedFields = returnedFields;
    }

    public String[] getSignatures() {
        return signatures;
    }

    public ReturnedFields getReturnedFields() {
        return returnedFields;
    }

    @Override
    public String toString() {
        return "SignData{" +
                "signatures=" + Arrays.toString(signatures) +
                ", returnedFields=" + returnedFields +
                '}';
    }
}
