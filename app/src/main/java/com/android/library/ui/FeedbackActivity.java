package com.android.library.ui;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityFeedbackBinding;
import com.android.library.utils.ToastUtils;


public class FeedbackActivity extends UIActivity<ActivityFeedbackBinding> implements Runnable {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("反馈");
    }

    public void send(View v) {
        String content = databinding.edtFeedback.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.toastSuccess(context, "请输入您的宝贵意见");
            return;
        }
        showProgress();
        new Handler().postDelayed(this, 1500);
    }

    @Override
    public void run() {
        ToastUtils.toastSuccess(context, "我们已经收到您的意见，非常感谢！");
        finish();
    }
}
