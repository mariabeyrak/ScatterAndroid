package com.mariabeyrak.scatterintegration.models.requests.Transaction.request;

import android.util.Log;

import com.mariabeyrak.scatterintegration.models.core.transaction.EosAction;
import com.mariabeyrak.scatterintegration.models.core.transaction.EosActionAuthorization;

import org.bouncycastle.util.encoders.Hex;

public class Action {
    private String account;
    private String name;
    private Authorization[] authorization;
    private String data;

    public Action(String account, String name, Authorization[] authorization, String data) {
        super();
        this.account = account;
        this.name = name;
        this.authorization = authorization;
        this.data = data;
    }

    EosAction toEosAction() {
        Log.d("<<SS", "toEosAction data: " + data);
        return new EosAction(account, name, getEosActionAuthorization(), Hex.decode(data));
    }

    private EosActionAuthorization[] getEosActionAuthorization() {
        EosActionAuthorization[] array = new EosActionAuthorization[authorization.length];
        for (int i = 0; i < authorization.length; i++) {
            array[i] = authorization[i].toEosActionAuthorization();
        }
        return array;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public Authorization[] getAuthorization() {
        return authorization;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Action{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", authorization=" + authorization +
                ", data='" + data + '\'' +
                '}';
    }
}
