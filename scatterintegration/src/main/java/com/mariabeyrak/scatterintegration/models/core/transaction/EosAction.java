package com.mariabeyrak.scatterintegration.models.core.transaction;

import com.mariabeyrak.scatterintegration.util.ByteSerializer;
import com.mariabeyrak.scatterintegration.util.Serializable;

import static com.mariabeyrak.scatterintegration.util.EosUtils.encodeName;

/**
 * created by Alex Ivanov on 2019-02-12.
 */
public class EosAction implements Serializable {

    private final String account;
    private final String name;
    private final EosActionAuthorization[] authorization;
    private final byte[] data;

    private static EosActionAuthorization[] provideAuthorization(String... from) {
        EosActionAuthorization[] authorizationArray = new EosActionAuthorization[from.length];
        for (int i = 0; i < from.length; i++) {
            authorizationArray[i] = new EosActionAuthorization(from[i], EosActionAuthorizationPermission.ACTIVE);
        }
        return authorizationArray;
    }

    public EosAction(String account,
                     String name,
                     EosActionAuthorization[] authorization,
                     byte[] data) {
        this.account = account;
        this.name = name;
        this.authorization = authorization;
        this.data = data;
    }

    @Override
    public byte[] serialize() {
        return new ByteSerializer()
                .write(encodeName(account))
                .write(encodeName(name))
                .write(authorization, true)
                .writeVarInt32(data.length)
                .write(data)
                .serialize();
    }
}