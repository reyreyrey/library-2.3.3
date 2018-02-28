package com.android.library.ui;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityRegisterBinding;
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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RegisterActivity extends UIActivity<ActivityRegisterBinding> implements View.OnClickListener, Runnable {
    private boolean isShow = false;

    public static void reg(Activity context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivityForResult(intent, 1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        tvTitle.setText("注册");
        handler.postDelayed(this, 300);
        databinding.btnSummit.setOnClickListener(this);
        databinding.ivAlShow.setOnClickListener(this);
    }

    private void reg() {
        String username = databinding.etArUsername.getText().toString().trim();
        String pwd = databinding.etArPwd.getText().toString().trim();
        String rePwd = databinding.etArAffirm.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(rePwd)) {
            ToastUtils.toastError(context, "用户名或密码不能为空！");
            return;
        }
        if (pwd.length() < 8 || pwd.length() > 12) {
            ToastUtils.toastError(context, "密码为8-12位包含字母和数字");
            return;
        }
        if (!pwd.equalsIgnoreCase(rePwd)) {
            ToastUtils.toastError(context, "两次密码输入不一致");
            return;
        }
        reg(username, pwd);
    }

    private void reg(final String username, final String pwd) {
        showProgress();
        OkGo.<String>get(Cons.REG_URL).params("username", username).params("password", pwd).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                BaseModel<UserModel> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<UserModel>>() {
                }.getType());
                if (baseModel.getSuccess() == 1) {
                    UserManager.saveUser(baseModel.getData());
                    Observable
                            .just(baseModel.getData())
                            .subscribeOn(Schedulers.io())
                            .map(new Function<UserModel, Boolean>() {

                                @Override
                                public Boolean apply(UserModel userModel) throws Exception {
                                    EMClient.getInstance().createAccount(username, pwd);
                                    return true;
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) throws Exception {
                                    loginChat(username, pwd);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    ToastUtils.toastError(context, throwable.toString());
                                    dismissProgress();
                                }
                            });
                } else {
                    ToastUtils.toastError(context, baseModel.getMsg());
                    dismissProgress();
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
                        ToastUtils.toastSuccess(context, "注册成功！");
                        setResult(RESULT_OK);
                        finish();
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String error) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        ToastUtils.toastSuccess(context, "注册失败！");
                    }
                });
            }
        });
    }

    @Override
    public void run() {
        SoftKeyboardUtils.showSoftKeyboard(context, databinding.etArUsername);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_summit) {
            reg();
            return;
        }
        if (view.getId() == R.id.iv_al_show) {
            if (isShow) {
                databinding.etArPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                isShow = false;
                databinding.ivAlShow.setImageResource(R.drawable.icon_login_normal);
            } else {
                databinding.etArPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                isShow = true;
                databinding.ivAlShow.setImageResource(R.drawable.icon_login_click);
            }
            return;
        }
    }
}
