package com.android.webview.x5;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebView;

import java.util.Map;



/**
 * author: Rea.X
 * date: 2017/9/23.
 */

public class PostWebView extends WebView {
    public PostWebView(Context context) {
        super(context);
    }

    private OnScrollChangedCallback callback;

    public PostWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PostWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PostWebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
    }

    public PostWebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
    }


    @Override
    public void loadUrl(String url) {
        loadUrl(url, null);
    }

    @Override
    public void loadUrl(String url, Map<String, String> map) {
        super.loadUrl(url, map);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (callback != null) {
            callback.onScroll(l, oldl, t, oldt);
        }
    }


    public interface OnScrollChangedCallback {
        void onScroll(int x, int oldx, int y, int oldy);
    }

    public void setOnScrollChangedCallback(OnScrollChangedCallback callback) {
        this.callback = callback;
    }


}
