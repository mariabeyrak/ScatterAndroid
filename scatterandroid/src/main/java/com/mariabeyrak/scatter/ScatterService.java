package com.mariabeyrak.scatter;

import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.mariabeyrak.scatter.models.MethodName;
import com.mariabeyrak.scatter.models.ProtocolInfo;
import com.mariabeyrak.scatter.models.ScatterResponse;
import com.mariabeyrak.scatter.models.requests.AppInfo.AppInfoResponseData;
import com.mariabeyrak.scatter.models.requests.MsgTransaction.MsgTransactionRequestParams;
import com.mariabeyrak.scatter.models.requests.Transaction.request.TransactionRequestParams;
import com.mariabeyrak.scatter.models.requests.Transaction.response.ReturnedFields;
import com.mariabeyrak.scatter.models.requests.Transaction.response.SignData;
import com.mariabeyrak.scatter.models.requests.Transaction.response.TransactionResponseData;
import com.mariabeyrak.scatter.models.response.ResponseCode;
import com.mariabeyrak.scatter.models.response.ResponseCodeInfo;
import com.mariabeyrak.scatter.models.response.ResponseType;

import static com.mariabeyrak.scatter.models.MethodName.GET_EOS_ACCOUNT;
import static com.mariabeyrak.scatter.models.MethodName.REQUEST_MSG_SIGNATURE;
import static com.mariabeyrak.scatter.models.MethodName.REQUEST_SIGNATURE;

final class ScatterService {
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
            public void onTransactionCompletedErrorCallback(ResponseCodeInfo errorInfo, String messageToUser) {
                sendErrorScript(webView, REQUEST_SIGNATURE, errorInfo == null ?
                        new ResponseCodeInfo(ResponseType.UNKNOWN_ERROR, ResponseCode.UNKNOWN_ERROR) : errorInfo, messageToUser);
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
            public void onMsgTransactionCompletedErrorCallback(ResponseCodeInfo errorInfo, String messageToUser) {
                sendErrorScript(webView, REQUEST_MSG_SIGNATURE, errorInfo == null ?
                        new ResponseCodeInfo(ResponseType.UNKNOWN_ERROR, ResponseCode.UNKNOWN_ERROR) : errorInfo, messageToUser);
            }
        };

        scatterClient.completeMsgTransaction(msgTransactionRequestParams, msgTransactionCompleted);
    }

    private static void sendSuccessScript(WebView webView, @MethodName.Methods String methodName, String responseData) {
        injectJs(webView, new ScatterResponse(methodName, new ResponseCodeInfo(ResponseType.SUCCESS, ResponseCode.SUCCESS), responseData).formatSuccessResponse());
    }

    private static void sendErrorScript(WebView webView, @MethodName.Methods String methodName, ResponseCodeInfo responseCodeInfo, String messageToUser) {
        injectJs(webView, new ScatterResponse(methodName, responseCodeInfo, "\"\"").formatErrorResponse(messageToUser));
    }

    static void injectJs(final WebView webView, final String script) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.evaluateJavascript(script, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                    }
                });
            }
        });
    }
}
