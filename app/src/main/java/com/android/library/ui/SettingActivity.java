package com.android.library.ui;

import android.text.TextUtils;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivitySettingBinding;
import com.android.library.manager.UserManager;
import com.android.library.utils.ToastUtils;

/**
 * Created by wiki on 2018/1/23.
 */

public class SettingActivity extends UIActivity<ActivitySettingBinding> implements View.OnClickListener{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("设置");
        databinding.tvLoginout.setOnClickListener(this);
        if(UserManager.isLogin()){
            databinding.tvLoginout.setVisibility(View.VISIBLE);
        }else{
            databinding.tvLoginout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_loginout){
            UserManager.loginOut();
            ToastUtils.toastSuccess(context, "退出登录成功！");
            databinding.tvLoginout.setVisibility(View.GONE);
        }
    }
}
