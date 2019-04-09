package com.mariabeyrak.scatter.models;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MethodName {
    public static final String GET_APP_INFO = "callbackGetAppInfo";
    public static final String GET_EOS_ACCOUNT = "callbackGetEosAccount";
    public static final String REQUEST_SIGNATURE = "callbackRequestSignature";
    public static final String REQUEST_MSG_SIGNATURE = "callbackRequestMsgSignature";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GET_APP_INFO, GET_EOS_ACCOUNT, REQUEST_SIGNATURE, REQUEST_MSG_SIGNATURE})
    public @interface Methods {
    }
}