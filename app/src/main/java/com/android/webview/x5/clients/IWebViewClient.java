package com.android.webview.x5.clients;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;

import com.android.webview.x5.ProgressWebView;
import com.android.webview.x5.clients.cache.CacheResourceClient;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class IWebViewClient extends CacheResourceClient {
    protected ProgressWebView view;
    protected Context context;
    protected ProgressBar lineProgressBar;
    protected ProgressBar circleProgressbar;
    protected boolean isShowLineProgress, isShowCircleProgress;

    public IWebViewClient(ProgressWebView view) {
        this.view = view;
        this.context = view.getContext();
        this.lineProgressBar = view.getlineProgressbar();
        this.circleProgressbar = view.getcircleProgressbar();
        this.isShowLineProgress = false;
        this.isShowCircleProgress = true;
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (isShowLineProgress) {
            this.lineProgressBar.setVisibility(View.VISIBLE);
            this.lineProgressBar.setProgress(0);
        }
        if (isShowCircleProgress)
            this.circleProgressbar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        sslErrorHandler.proceed();
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String s) {
        if (s.contains("javascript: void(0)") || s.contains("javascript:void(0)")) return false;
        if (isShowLineProgress) {
            this.lineProgressBar.setVisibility(View.VISIBLE);
            this.lineProgressBar.setProgress(0);
        }
        if (isShowCircleProgress)
            this.circleProgressbar.setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(s);
        String schem = uri.getScheme();
        if (schem != null && schem.contains("file")) return false;
        if (schem != null && (schem.contains("http") || schem.contains("https"))) {
            webView.loadUrl(s);
            return true;
        } else {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(s));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                webView.getContext().startActivity(intent);
            } catch (Exception e) {
            }
        }

        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        this.circleProgressbar.setVisibility(View.GONE);
        this.lineProgressBar.setVisibility(View.GONE);
    }
}
