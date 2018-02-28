package com.android.library.ui;

import android.text.TextUtils;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityCheckUpdateBinding;

import java.util.logging.Handler;

/**
 * Created by wiki on 2018/1/23.
 */

public class CheckUpdateActivity extends UIActivity<ActivityCheckUpdateBinding> implements Runnable{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_update;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("检查更新");
        databinding.layoutContent.setVisibility(View.GONE);
        databinding.layoutLoading.setVisibility(View.VISIBLE);
        new android.os.Handler().postDelayed(this, 1500);
    }

    @Override
    public void run() {
        databinding.layoutContent.setVisibility(View.VISIBLE);
        databinding.layoutLoading.setVisibility(View.GONE);
    }
}
