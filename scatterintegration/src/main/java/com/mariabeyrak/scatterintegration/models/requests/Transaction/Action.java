package com.mariabeyrak.scatterintegration.models.requests.Transaction;

import java.util.List;

public class Action {
    private String account;
    private String name;
    private List<Authorization> authorization = null;
    private String data;

    public Action(String account, String name, List<Authorization> authorization, String data) {
        super();
        this.account = account;
        this.name = name;
        this.authorization = authorization;
        this.data = data;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Authorization> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(List<Authorization> authorization) {
        this.authorization = authorization;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
