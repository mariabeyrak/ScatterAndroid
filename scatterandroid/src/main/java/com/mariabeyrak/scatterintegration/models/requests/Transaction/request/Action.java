package com.mariabeyrak.scatterintegration.models.requests.Transaction.request;

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
