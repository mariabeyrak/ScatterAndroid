package com.mariabeyrak.scatterintegration.models.requests.Transaction;

public class TransactionRequestParams {
    private Transaction transaction;
    private Buf buf;

    public TransactionRequestParams(Transaction transaction, Buf buf) {
        this.transaction = transaction;
        this.buf = buf;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Buf getBuf() {
        return buf;
    }

    public void setBuf(Buf buf) {
        this.buf = buf;
    }
}


