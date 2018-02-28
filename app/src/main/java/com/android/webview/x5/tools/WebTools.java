package com.android.webview.x5.tools;

import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebView;

/**
 * author: Rea.X
 * date: 2017/11/2.
 */

public class WebTools {


    public static synchronized void releaseWebView(WebView webview) {
        if (webview != null) {
            try {
                if (webview.getParent() != null) {
                    ((ViewGroup) webview.getParent()).removeView(webview);
                }
                webview.stopLoading();
                webview.getSettings().setJavaScriptEnabled(false);
                webview.clearHistory();
                webview.clearView();
                webview.removeAllViews();
                webview.destroy();
//                System.exit(0);
            } catch (Throwable e) {
            }
        }
    }
}
