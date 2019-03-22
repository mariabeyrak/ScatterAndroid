package com.mariabeyrak.scatterintegrationandroid;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mariabeyrak.scatterintegration.Scatter;
import com.mariabeyrak.scatterintegration.ScatterClient;
import com.mariabeyrak.scatterintegration.models.requests.MsgTransaction.MsgTransactionRequestParams;
import com.mariabeyrak.scatterintegration.models.requests.Transaction.request.TransactionRequestParams;
import com.paytomat.eos.PrivateKey;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "<<SS";

    private Scatter scatterImplementation;

    private static final String accountName = "YOUR_ACCOUNT_NAME";
    private static final PrivateKey key = new PrivateKey("YOUR_PRIVATE_KEY");

    private ScatterClient scatterClient = new ScatterClient() {
        @Override
        public void getAccount(AccountReceived onAccountReceived) {
            Log.d(TAG, "getAccount");
            onAccountReceived.onAccountReceivedCallback(accountName);
        }

        @Override
        public void completeTransaction(TransactionRequestParams transactionRequestParams, TransactionCompleted onTransactionCompleted) {
            Log.d(TAG, "completeTransaction");
            onTransactionCompleted.onTransactionCompletedCallback(key);
        }

        @Override
        public void completeMsgTransaction(MsgTransactionRequestParams params, TransactionMsgCompleted onTransactionCompleted) {
            Log.d(TAG, "completeMsgTransaction");
            onTransactionCompleted.onTransactionMsgCompletedCallback(key);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);

        scatterImplementation = new Scatter(webView, scatterClient);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebViewClient(new ScatterWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        scatterImplementation.initInterface();

        webView.loadUrl("https://eostowergame.com");
    }

    @Override
    protected void onDestroy() {
        scatterImplementation.removeInterface();
        super.onDestroy();
    }

    private class ScatterWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            Log.d(TAG, "request: " + request.getUrl().toString());
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            Log.d(TAG, "request: " + url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            scatterImplementation.injectJS();
        }
    }
}
