package com.android.library.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityLoginBinding;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.UserModel;
import com.android.library.utils.Cons;
import com.android.library.utils.SoftKeyboardUtils;
import com.android.library.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;


public class LoginActivity extends UIActivity<ActivityLoginBinding> implements View.OnClickListener, Runnable {
    private boolean isShow = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        tvTitle.setText("登录");
        handler.postDelayed(this, 300);
        databinding.ivAlShow.setOnClickListener(this);
        databinding.tvAlRegister.setOnClickListener(this);
        databinding.btnSummit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_al_register) {
            RegisterActivity.reg(context);
            return;
        }
        if (view.getId() == R.id.iv_al_show) {
            if (isShow) {
                databinding.etAlPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                isShow = false;
                databinding.ivAlShow.setImageResource(R.drawable.icon_login_normal);
            } else {
                databinding.etAlPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                isShow = true;
                databinding.ivAlShow.setImageResource(R.drawable.icon_login_click);
            }
            return;
        }
        if (view.getId() == R.id.btn_summit) {
            login();
            return;
        }
    }

    @Override
    public void run() {
        SoftKeyboardUtils.showSoftKeyboard(context, databinding.etAlAccount);
    }

    private void login() {
        String username = databinding.etAlAccount.getText().toString().trim();
        String pwd = databinding.etAlPwd.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwd)) {
            ToastUtils.toastError(context, "用户名或密码不能为空！");
            return;
        }
        if (pwd.length() < 8 || pwd.length() > 12) {
            ToastUtils.toastError(context, "密码为8-12位包含字母和数字");
            return;
        }
        login(username, pwd);
    }

    private void login(final String username, final String pwd) {
        showProgress();
        OkGo.<String>get(Cons.LOGIN_URL)
                .params("username", username)
                .params("password", pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body();
                        BaseModel<UserModel> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<UserModel>>() {
                        }.getType());
                        if (baseModel.getSuccess() == 1) {
                            UserManager.saveUser(baseModel.getData());
                            loginChat(username, pwd);
                        } else {
                            dismissProgress();
                            ToastUtils.toastError(context, baseModel.getMsg());
                        }
                    }
                });
    }

    private void loginChat(String username, String pwd) {
        EMClient.getInstance().login(username, pwd, new EMCallBack() {

            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        dismissProgress();
                        ToastUtils.toastSuccess(context, "登录成功！");
                        finish();
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(final int code, final String error) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        dismissProgress();
                        if(code == 200){
                            ToastUtils.toastSuccess(context, "登录成功！");
                            finish();
                            return;
                        }
                        ToastUtils.toastSuccess(context, "登录失败！" + error);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            finish();
    }
}
