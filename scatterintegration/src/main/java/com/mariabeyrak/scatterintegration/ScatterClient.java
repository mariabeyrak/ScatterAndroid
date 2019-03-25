package com.mariabeyrak.scatterintegration;

import com.mariabeyrak.scatterintegration.models.requests.MsgTransaction.MsgTransactionRequestParams;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.request.TransactionRequestParams;

public abstract class ScatterClient {

    public interface AccountReceived {
        void onAccountReceivedSuccessCallback(String accountName);
        void onAccountReceivedErrorCallback(Error error);
    }

    public interface TransactionCompleted {
        void onTransactionCompletedSuccessCallback(String response);
        void onTransactionCompletedErrorCallback(Error error);
    }

    public void getAccount(AccountReceived onAccountReceived) {
    }

    public void completeTransaction(TransactionRequestParams transactionRequestParams, TransactionCompleted onTransactionCompleted) {
    }

    public void completeMsgTransaction(MsgTransactionRequestParams data, TransactionCompleted onTransactionMsgCompleted) {
    }
}
