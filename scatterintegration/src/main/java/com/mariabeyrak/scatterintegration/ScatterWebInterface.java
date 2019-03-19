package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.mariabeyrak.scatterintegration.models.ScatterRequest;

import static com.mariabeyrak.scatterintegration.ScatterService.getEosAccount;

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
        Log.d(TAG, "scatterRequest: " + scatterRequest.toString());

        switch (scatterRequest.getMethodName()) {
            case GetEosAccount: {
                getEosAccount(webView, scatterClient);
                break;
            }
            default:
                break;
        }
    }

}
