package com.mariabeyrak.scatterintegration;

public interface ScatterClient {
    interface AccountReceived {
        void onAccountReceivedCallback(String accountName);

        void onAccountReceivedErrorCallback(Error error);
    }

    void getAccount(AccountReceived onAccountReceived);
}
