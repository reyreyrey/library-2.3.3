package com.android.library.ui;

import android.text.TextUtils;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityMineBinding;

/**
 * Created by wiki on 2018/1/18.
 */

public class MineActivity extends UIActivity<ActivityMineBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("我的");
    }
}
