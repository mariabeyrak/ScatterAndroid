package com.mariabeyrak.scatterintegration.models.core.transaction;

import com.mariabeyrak.scatterintegration.util.ByteSerializer;
import com.mariabeyrak.scatterintegration.util.Serializable;

import static com.mariabeyrak.scatterintegration.util.EosUtils.encodeName;

/**
 * created by Alex Ivanov on 2019-02-12.
 */
public class EosActionAuthorization implements Serializable {

    private final String actor;
    private final String permission;

    private EosActionAuthorization(String actor, String permission) {
        this.actor = actor;
        this.permission = permission;
    }

    public EosActionAuthorization(String actor, EosActionAuthorizationPermission permission) {
        this(actor, permission.getValue());
    }

    public String getActor() {
        return actor;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public byte[] serialize() {
        return new ByteSerializer()
                .write(encodeName(actor))
                .write(encodeName(permission))
                .serialize();
    }
}
