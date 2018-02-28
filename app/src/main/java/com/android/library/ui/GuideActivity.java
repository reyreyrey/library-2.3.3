package com.android.library.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.library.R;
import com.android.library.models.ControlModel;
import com.android.library.utils.GuideTools;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;


import cn.bingoogolapple.bgabanner.BGABanner;


public class GuideActivity extends AppCompatActivity {
    private ControlModel configModel;
    private int[] ress;
    private BGABanner bannerGuideForeground;

    public static void showGuide(Activity context, ControlModel data, int[] ress) {
        Intent intent = new Intent(context, GuideActivity.class);
        intent.putExtra("data", data);
        intent.putExtra("ress", ress);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        bannerGuideForeground = (BGABanner) findViewById(R.id.banner_guide_foreground);
        init();
    }

    protected void init() {
        ress = getIntent().getIntArrayExtra("ress");
        configModel = (ControlModel) getIntent().getSerializableExtra("data");
        ImmersionBar.with(this)
                .fitsSystemWindows(false)
                .hideBar(BarHide.FLAG_HIDE_BAR).init();
        bannerGuideForeground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                GuideTools.guideDismiss();
                if (configModel.isOpen()) {
                    String url = configModel.getUrl();
                    WebViewActivity.load(GuideActivity.this, url);
                    finish();
                }
            }
        });
       bannerGuideForeground.setData(ress);
    }
}
