package com.android.library.ui;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.library.R;
import com.android.library.models.BaseModel;
import com.android.library.models.ControlModel;
import com.android.library.utils.GuideTools;
import com.android.library.utils.SplashTools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.Date;

import static com.android.library.utils.ChatUtils.isLoggedIn;
import static com.android.library.utils.ChatUtils.loadAll;
import static com.android.library.utils.Cons.CONTROL_URL;

/**
 * author: Rea.X
 * date: 2017/12/20.
 */

public abstract class SplashBaseActivity extends AppCompatActivity {
    private long timeStamp = 0;
    private static final String CHECKURL = CONTROL_URL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView im = new ImageView(this);
        setContentView(im);
        ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .hideBar(BarHide.FLAG_HIDE_BAR).init();
        if (isShowSplash()) {
            im.setBackgroundResource(getSplashImageRes());
        } else {
            if(getNormalImageRes() > 0){
                im.setBackgroundResource(getNormalImageRes());
            }else{
                im.setBackgroundResource(R.drawable.guide);
            }
        }

        timeStamp = System.currentTimeMillis();
        query();
        if (isLoggedIn()) {
            loadAll();
        }
    }


    private void query() {
        OkGo.<String>get(CHECKURL + getAppID())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            String result = response.body();
                            final BaseModel<ControlModel> model = new Gson().fromJson(result, new TypeToken<BaseModel<ControlModel>>() {
                            }.getType());
                            SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
                                @Override
                                public void done() {
                                    if (model != null) {
                                        ControlModel controlModel = model.getData();
                                        if (controlModel != null && controlModel.isOpen()
                                                && !TextUtils.isEmpty(controlModel.getUrl())) {
                                            if (isShowGuide() && GuideTools.needShowGuide()) {
                                                GuideActivity.showGuide(SplashBaseActivity.this, controlModel, guideRess());
                                            } else {
                                                WebViewActivity.load(SplashBaseActivity.this, controlModel.getUrl());
                                                finish();
                                            }
                                            return;
                                        }
                                    }
                                    toMain();
                                }
                            });
                        } catch (Exception e) {
                            SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
                                @Override
                                public void done() {
                                    toMain();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        SplashTools.checkTime(timeStamp, new SplashTools.SplashCallback() {
                            @Override
                            public void done() {
                                toMain();
                            }
                        });
                    }
                });
    }

    protected abstract void toMain();

    protected abstract String getAppID();


    protected abstract
    @DrawableRes
    int getSplashImageRes();

    protected abstract boolean isShowGuide();

    protected abstract int[] guideRess();

    protected abstract Date showCaipiaoSplashPicTime();

    protected @DrawableRes
    int getNormalImageRes() {
        return -1;
    }


    private boolean isShowSplash() {
        Date date = new Date(System.currentTimeMillis());
        Date showDate = showCaipiaoSplashPicTime();
        if (date.after(showDate)) {
            return true;
        }
        return false;
    }
}
