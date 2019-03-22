package com.mariabeyrak.scatterintegration.models.requests.Transaction.request;

import com.paytomat.eos.transaction.EosActionAuthorization;
import com.paytomat.eos.transaction.EosActionAuthorizationPermission;

public class Authorization {

    private String actor;
    private String permission;

    public Authorization(String actor, String permission) {
        this.actor = actor;
        this.permission = permission;
    }

    public EosActionAuthorization toEosActionAuthorization() {
        return new EosActionAuthorization(actor, getEosActionAuthorizationPermissionByValue(permission));
    }

    private EosActionAuthorizationPermission getEosActionAuthorizationPermissionByValue(String value) {
        for (EosActionAuthorizationPermission e : EosActionAuthorizationPermission.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return EosActionAuthorizationPermission.ACTIVE;
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