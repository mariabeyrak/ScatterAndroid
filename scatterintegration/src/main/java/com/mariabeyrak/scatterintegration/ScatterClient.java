package com.mariabeyrak.scatterintegration;

import com.paytomat.eos.PrivateKey;

public abstract class ScatterClient {

    public interface AccountReceived {
        void onAccountReceivedCallback(String accountName);
        void onAccountReceivedErrorCallback(Error error);
    }

    public interface TransactionCompleted {
        void onTransactionCompletedCallback(PrivateKey key);

        void onTransactionCompletedErrorCallback(Error error);
    }

    public interface TransactionMsgCompleted {
        void onTransactionCompletedCallback(PrivateKey key);

        void onTransactionCompletedErrorCallback(Error error);
    }

    void getAccount(AccountReceived onAccountReceived) {
    }

    void completeTransaction(TransactionCompleted onTransactionCompleted) {
    }

    void completeMsgTransaction(TransactionMsgCompleted onTransactionMsgCompleted) {
    }
}
