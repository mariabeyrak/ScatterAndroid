package com.mariabeyrak.scatterintegration.models.requests.Transaction.request;

public class Authorization {

    private String actor;
    private String permission;

    public Authorization(String actor, String permission) {
        this.actor = actor;
        this.permission = permission;
    }

    public String getActor() {
        return actor;
    }
    public String getPermission() {
        return permission;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "actor='" + actor + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}