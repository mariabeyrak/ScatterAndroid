package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.mariabeyrak.scatterintegration.models.MethodName;
import com.mariabeyrak.scatterintegration.models.ProtocolInfo;
import com.mariabeyrak.scatterintegration.models.ResponseCodeInfo;
import com.mariabeyrak.scatterintegration.models.ScatterResponse;
import com.mariabeyrak.scatterintegration.models.requests.AppInfo.AppInfoResponseData;
import com.mariabeyrak.scatterintegration.models.requests.MsgTransaction.MsgTransactionRequestParams;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.request.TransactionRequestParams;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.response.ReturnedFields;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.response.SignData;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.response.TransactionResponseData;

import static com.mariabeyrak.scatterintegration.models.MethodName.GET_EOS_ACCOUNT;
import static com.mariabeyrak.scatterintegration.models.MethodName.REQUEST_MSG_SIGNATURE;
import static com.mariabeyrak.scatterintegration.models.MethodName.REQUEST_SIGNATURE;

final class ScatterService {
    private static String TAG = "<<SS";
    final static private Gson gson = new Gson();

    private ScatterService() {
    }

    static void getAppInfo(final WebView webView, ScatterClient scatterClient) {
        ScatterClient.AppInfoReceived appInfoReceived = new ScatterClient.AppInfoReceived() {
            @Override
            public void onAppInfoReceivedSuccessCallback(String appName, String appVersion) {
                AppInfoResponseData responseData = new AppInfoResponseData(appName, appVersion, ProtocolInfo.name, ProtocolInfo.version);
                sendSuccessScript(webView, GET_EOS_ACCOUNT, gson.toJson(responseData));
            }

            @Override
            public void onAccountReceivedErrorCallback(Error error) {
            }
        };

        scatterClient.getAppInfo(appInfoReceived);
    }

    static void getEosAccount(final WebView webView, ScatterClient scatterClient) {
        ScatterClient.AccountReceived accountReceived = new ScatterClient.AccountReceived() {
            @Override
            public void onAccountReceivedSuccessCallback(String accountName) {
                sendSuccessScript(webView, GET_EOS_ACCOUNT, gson.toJson(accountName));
            }

            @Override
            public void onAccountReceivedErrorCallback(Error error) {
            }
        };

        scatterClient.getAccount(accountReceived);
    }

    static void requestSignature(String data, final WebView webView, ScatterClient scatterClient) {
        final TransactionRequestParams transactionRequestParams = gson.fromJson(data, TransactionRequestParams.class);

        ScatterClient.TransactionCompleted transactionCompleted = new ScatterClient.TransactionCompleted() {
            @Override
            public void onTransactionCompletedSuccessCallback(String[] signatures) {
                String response = gson.toJson(new TransactionResponseData(new SignData(signatures, new ReturnedFields())));
                sendSuccessScript(webView, REQUEST_SIGNATURE, response);
            }

            @Override
            public void onTransactionCompletedErrorCallback(Error error) {
                sendErrorScript(webView, REQUEST_SIGNATURE);
            }
        };

        scatterClient.completeTransaction(transactionRequestParams, transactionCompleted);
    }

    static void requestMsgSignature(String data, final WebView webView, ScatterClient scatterClient) {
        final MsgTransactionRequestParams msgTransactionRequestParams = gson.fromJson(data, MsgTransactionRequestParams.class);

        ScatterClient.MsgTransactionCompleted msgTransactionCompleted = new ScatterClient.MsgTransactionCompleted() {
            @Override
            public void onMsgTransactionCompletedSuccessCallback(String signature) {
                sendSuccessScript(webView, REQUEST_MSG_SIGNATURE, gson.toJson(signature));
            }

            @Override
            public void onMsgTransactionCompletedErrorCallback(Error error) {
                sendErrorScript(webView, REQUEST_MSG_SIGNATURE);
            }
        };

        scatterClient.completeMsgTransaction(msgTransactionRequestParams, msgTransactionCompleted);
    }

    private static void sendSuccessScript(WebView webView, @MethodName.Methods String methodName, String responseData) {
        injectJs(webView, new ScatterResponse(methodName, ResponseCodeInfo.SUCCESS, responseData).formatResponse());
    }

    private static void sendErrorScript(WebView webView, @MethodName.Methods String methodName) {
        injectJs(webView, new ScatterResponse(methodName, ResponseCodeInfo.ERROR, "\"\"").formatResponse());
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
