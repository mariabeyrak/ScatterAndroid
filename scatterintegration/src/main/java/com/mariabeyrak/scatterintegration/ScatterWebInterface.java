package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.mariabeyrak.scatterintegration.models.ScatterRequest;

import static com.mariabeyrak.scatterintegration.ScatterService.getEosAccount;
import static com.mariabeyrak.scatterintegration.ScatterService.requestMsgSignature;
import static com.mariabeyrak.scatterintegration.ScatterService.requestSignature;

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
            case GetEosAccount: {
                getEosAccount(webView, scatterClient);
                break;
            }
            case RequestSignature: {
                requestSignature(scatterRequest.getParams(), webView, scatterClient);
                break;
            }
            case RequestMsgSignature: {
                requestMsgSignature(scatterRequest.getParams(), webView, scatterClient);
                break;
            }
            default:
                break;
        }
    }

}
