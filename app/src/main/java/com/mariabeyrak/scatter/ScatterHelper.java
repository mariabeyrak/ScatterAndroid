package com.mariabeyrak.scatter;

import com.mariabeyrak.scatter.models.requests.Transaction.request.Action;
import com.mariabeyrak.scatter.models.requests.Transaction.request.Authorization;
import com.mariabeyrak.scatter.models.requests.Transaction.request.Transaction;
import com.mariabeyrak.scatter.models.requests.Transaction.request.TransactionRequestParams;
import com.paytomat.eos.PrivateKey;
import com.paytomat.eos.transaction.EosAction;
import com.paytomat.eos.transaction.EosActionAuthorization;
import com.paytomat.eos.transaction.EosActionAuthorizationPermission;
import com.paytomat.eos.transaction.EosExtentionType;
import com.paytomat.eos.transaction.EosTransaction;

import org.bouncycastle.util.encoders.Hex;

class ScatterHelper {

    private static String getChainIdHex(TransactionRequestParams params) {
        byte[] byteArray = new byte[32];
        System.arraycopy(params.getBuf().getData(), 0, byteArray, 0, 32);
        return Hex.toHexString(byteArray);
    }

    static EosTransaction toEosTransaction(TransactionRequestParams params, PrivateKey privateKey) {
        return new EosTransaction(
                privateKey, getChainIdHex(params), params.getEstimatedInSeconds(),
                (short) params.getTransaction().getRefBlockNum(), (int) params.getTransaction().getRefBlockPrefix(),
                params.getTransaction().getMaxNetUsageWords(), (byte) params.getTransaction().getMaxCpuUsageMs(),
                params.getTransaction().getDelaySec(), new EosAction[]{}, getEosActions(params.getTransaction()), new EosExtentionType[]{}
        );
    }

    static EosAction[] getEosActions(Transaction transaction) {
        EosAction[] array = new EosAction[transaction.getActions().length];
        for (int i = 0; i < transaction.getActions().length; i++) {
            array[i] = toEosAction(transaction.getActions()[i]);
        }
        return array;
    }

    static EosAction toEosAction(Action action) {
        return new EosAction(action.getAccount(), action.getName(), getEosActionAuthorization(action), Hex.decode(action.getData()));
    }

    private static EosActionAuthorization[] getEosActionAuthorization(Action action) {
        EosActionAuthorization[] array = new EosActionAuthorization[action.getAuthorization().length];
        for (int i = 0; i < action.getAuthorization().length; i++) {
            array[i] = toEosActionAuthorization(action.getAuthorization()[i]);
        }
        return array;
    }

    static EosActionAuthorization toEosActionAuthorization(Authorization authorization) {
        return new EosActionAuthorization(authorization.getActor(), getEosActionAuthorizationPermissionByValue(authorization.getPermission()));
    }

    private static EosActionAuthorizationPermission getEosActionAuthorizationPermissionByValue(String value) {
        for (EosActionAuthorizationPermission e : EosActionAuthorizationPermission.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return EosActionAuthorizationPermission.ACTIVE;
    }
}
