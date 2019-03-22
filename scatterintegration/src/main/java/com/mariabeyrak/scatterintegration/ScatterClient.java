package com.mariabeyrak.scatterintegration;

import com.mariabeyrak.scatterintegration.models.requests.MsgTransaction.MsgTransactionRequestParams;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.request.TransactionRequestParams;
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
        void onTransactionMsgCompletedCallback(PrivateKey key);
        void onTransactionMsgCompletedErrorCallback(Error error);
    }

    public void getAccount(AccountReceived onAccountReceived) {
    }

    public void completeTransaction(TransactionRequestParams transactionRequestParams, TransactionCompleted onTransactionCompleted) {
    }

    public void completeMsgTransaction(MsgTransactionRequestParams data, TransactionMsgCompleted onTransactionMsgCompleted) {
    }
}
