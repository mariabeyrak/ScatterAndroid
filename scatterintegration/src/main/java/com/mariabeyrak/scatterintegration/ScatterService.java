package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.mariabeyrak.scatterintegration.models.MethodName;
import com.mariabeyrak.scatterintegration.models.ResponseCodeInfo;
import com.mariabeyrak.scatterintegration.models.ScatterResponse;

final class ScatterService {
    private static String TAG = "<<SS";

    private ScatterService() {
    }

    static void getEosAccount(final WebView webView, ScatterClient scatterClient) {
        ScatterClient.AccountReceived accountReceived = new ScatterClient.AccountReceived() {
            @Override
            public void onAccountReceivedCallback(String accountName) {
                String script = new ScatterResponse(MethodName.GetEosAccount.getMethod(), ResponseCodeInfo.SUCCESS, accountName).formatResponse();
                injectJs(webView, script);
            }

            @Override
            public void onAccountReceivedErrorCallback(Error error) {

            }
        };

        scatterClient.getAccount(accountReceived);
    }

    static void injectJs(final WebView webView, final String script) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.d(TAG, "onReceiveValue: " + value);
                    }
                });
            }
        });
    }
}
