package com.android.library.ui;

import android.text.TextUtils;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityGuanyuBinding;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class AboutActivity extends UIActivity<ActivityGuanyuBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_guanyu;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("关于我们");
    }
}
