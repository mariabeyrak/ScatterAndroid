package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.mariabeyrak.scatterintegration.models.MethodName;
import com.mariabeyrak.scatterintegration.models.ResponseCodeInfo;
import com.mariabeyrak.scatterintegration.models.ScatterResponse;
import com.mariabeyrak.scatterintegration.models.requests.MsgTransaction.MsgTransactionRequestParams;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.request.TransactionRequestParams;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.response.ReturnedFields;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.response.SignData;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.response.TransactionResponseData;
import com.paytomat.eos.Eos;
import com.paytomat.eos.PrivateKey;
import com.paytomat.eos.signature.Signature;
import com.paytomat.eos.transaction.EosPackedTransaction;
import com.paytomat.eos.transaction.EosTransaction;

import org.bouncycastle.util.encoders.Hex;

final class ScatterService {
    private static String TAG = "<<SS";
    final static private Gson gson = new Gson();

    private ScatterService() {
    }

    static void getEosAccount(final WebView webView, ScatterClient scatterClient) {
        ScatterClient.AccountReceived accountReceived = new ScatterClient.AccountReceived() {
            @Override
            public void onAccountReceivedCallback(String accountName) {
                String script = new ScatterResponse(MethodName.GetEosAccount.getMethod(), ResponseCodeInfo.SUCCESS, gson.toJson(accountName)).formatResponse();
                injectJs(webView, script);
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
            public void onTransactionCompletedCallback(PrivateKey key) {
                EosTransaction transaction = transactionRequestParams.toEosTransaction(key);
                EosPackedTransaction packedTransaction = transaction.getPackedTx();

                final String[] signatures = packedTransaction.getSignatures();
                String responseData = gson.toJson(new TransactionResponseData(new SignData(signatures, new ReturnedFields())));
                String script = new ScatterResponse(MethodName.RequestSignature.getMethod(), ResponseCodeInfo.SUCCESS, responseData).formatResponse();
                injectJs(webView, script);
            }

            @Override
            public void onTransactionCompletedErrorCallback(Error error) {

            }
        };

        scatterClient.completeTransaction(transactionRequestParams, transactionCompleted);
    }

    static void requestMsgSignature(String data, final WebView webView, ScatterClient scatterClient) {
        final MsgTransactionRequestParams msgTransactionRequestParams = gson.fromJson(data, MsgTransactionRequestParams.class);

        ScatterClient.TransactionMsgCompleted msgTransactionCompleted = new ScatterClient.TransactionMsgCompleted() {
            @Override
            public void onTransactionCompletedCallback(PrivateKey key) {
                final Signature signature = Eos.signTransactionRaw(Hex.decode(msgTransactionRequestParams.getData()), key);
                String responseData = gson.toJson(signature.toString());
                String script = new ScatterResponse(MethodName.RequestMsgSignature.getMethod(), ResponseCodeInfo.SUCCESS, responseData).formatResponse();
                injectJs(webView, script);
            }

            @Override
            public void onTransactionCompletedErrorCallback(Error error) {

            }
        };

        scatterClient.completeMsgTransaction(msgTransactionRequestParams, msgTransactionCompleted);
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
