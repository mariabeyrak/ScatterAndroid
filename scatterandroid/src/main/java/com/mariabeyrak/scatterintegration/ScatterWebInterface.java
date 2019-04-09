package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.mariabeyrak.scatterintegration.models.ScatterRequest;

import static com.mariabeyrak.scatterintegration.ScatterService.getAppInfo;
import static com.mariabeyrak.scatterintegration.ScatterService.getEosAccount;
import static com.mariabeyrak.scatterintegration.ScatterService.requestMsgSignature;
import static com.mariabeyrak.scatterintegration.ScatterService.requestSignature;
import static com.mariabeyrak.scatterintegration.models.MethodName.GET_APP_INFO;
import static com.mariabeyrak.scatterintegration.models.MethodName.GET_EOS_ACCOUNT;
import static com.mariabeyrak.scatterintegration.models.MethodName.REQUEST_MSG_SIGNATURE;
import static com.mariabeyrak.scatterintegration.models.MethodName.REQUEST_SIGNATURE;

class ScatterWebInterface {
    private static String TAG = "<<SS";

    private WebView webView;
    private ScatterClient scatterClient;

    ScatterWebInterface(WebView webView, ScatterClient scatterClient) {
        this.webView = webView;
        this.scatterClient = scatterClient;
    }

    @JavascriptInterface
    public void pushMessage(String data) {
        Log.d(TAG, "pushMessage data: " + data);

        Gson gson = new Gson();
        ScatterRequest scatterRequest = gson.fromJson(data, ScatterRequest.class);

        switch (scatterRequest.getMethodName()) {
            case GET_APP_INFO: {
                getAppInfo(webView, scatterClient);
                break;
            }
            case GET_EOS_ACCOUNT: {
                getEosAccount(webView, scatterClient);
                break;
            }
            case REQUEST_SIGNATURE: {
                requestSignature(scatterRequest.getParams(), webView, scatterClient);
                break;
            }
            case REQUEST_MSG_SIGNATURE: {
                requestMsgSignature(scatterRequest.getParams(), webView, scatterClient);
                break;
            }
            default:
                break;
        }
    }

}
