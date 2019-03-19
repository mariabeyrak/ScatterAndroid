package com.mariabeyrak.scatterintegration;

import android.util.Log;
import android.webkit.WebView;

import java.io.InputStream;
import java.util.Scanner;

import static com.mariabeyrak.scatterintegration.ScatterService.injectJs;

public class Scatter {
    private static String TAG = "<<SS";
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

            final String script = new StringBuilder().append("var SP_SCRIPT = document.createElement('script');\n")
                    .append("var SP_USER_AGENT_ANDROID = \"").append(webView.getSettings().getUserAgentString()).append("\";\n")
                    .append("var SP_USER_AGENT_IOS = \"SP_USER_AGENT_IOS\";\n")
                    .append("var SP_TIMEOUT = ").append(60 * 1000).append(";\n")
                    .append("SP_SCRIPT.type = 'text/javascript';\n")
                    .append("SP_SCRIPT.text = \"")
                    .append(jsScript)
                    .append("\";document.getElementsByTagName('head')[0].appendChild(SP_SCRIPT);").toString();

            injectJs(webView, script);
        } catch (Exception e) {
            Log.d(TAG, "Scatter js file not founded");
        }
    }

    public void removeInterface() {
        webView.removeJavascriptInterface(javascriptInterfaceName);
    }
}
