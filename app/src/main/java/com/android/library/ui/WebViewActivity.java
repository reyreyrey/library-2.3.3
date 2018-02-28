package com.android.library.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.android.library.R;
import com.android.library.utils.ToastUtils;
import com.android.webview.x5.ProgressWebView;


public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressWebView progressWebview;

    public static void load(Context context, String url, boolean isShowBack) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("isShowBack", isShowBack);
        context.startActivity(intent);
    }

    public static void openWebViewUrl(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("isShowBack", true);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void load(Context context, String url) {
        load(context, url, false);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        init();
    }


    private boolean isShowBack;

    protected void init() {
        progressWebview = (ProgressWebView) findViewById(R.id.progress_webview);
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        isShowBack = getIntent().getBooleanExtra("isShowBack", false);
        progressWebview.getWebView().loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        if (progressWebview.getWebView().canGoBack()) {
            progressWebview.getWebView().goBack();
        }
        if (isShowBack) finish();
    }


    private long backTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isShowBack) return super.onKeyDown(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backTime == 0) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            if ((System.currentTimeMillis() - backTime) >= 2000) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
