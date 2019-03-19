package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.WebView;

import java.io.InputStream;
import java.util.Scanner;

import static com.mariabeyrak.scatterintegration.ScatterService.injectJs;

public class Scatter {
    private static String TAG = "<<SS";

    private String userAgentString = "ANDROID_USER_AGENT";
    private String javascriptInterfaceName = "WebView";

    private WebView webView;
    private ScatterClient scatterClient;

    public Scatter(WebView webView, ScatterClient scatterClient) {
        this.webView = webView;
        this.scatterClient = scatterClient;
    }

    public void initInterface() {
        webView.addJavascriptInterface(new ScatterWebInterface(webView, scatterClient), javascriptInterfaceName);
    }

    public void injectJS() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("assets/scatterkit_script.js");

            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            String jsScript = s.hasNext() ? s.next() : "";

            final String script = "var script = document.createElement('script'); " +
                    "script.type = 'text/javascript'; " +
                    "script.text = \"" + jsScript.replace(userAgentString, webView.getSettings().getUserAgentString());

            injectJs(webView, script);
        } catch (Exception e) {
            Log.d(TAG, "Scatter js file not founded");
        }
    }

    public void removeInterface() {
        webView.removeJavascriptInterface(javascriptInterfaceName);
    }
}
