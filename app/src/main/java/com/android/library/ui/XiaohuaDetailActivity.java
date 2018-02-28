package com.android.library.ui;

import android.text.Html;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityXiaohuaDetailBinding;
import com.android.library.models.XiaohuaModel;


/**
 * Created by xinru on 2017/12/3.
 */

public class XiaohuaDetailActivity extends UIActivity<ActivityXiaohuaDetailBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiaohua_detail;
    }

    @Override
    protected void init() {
        XiaohuaModel.ShowapiResBodyBean.ContentlistBean model = (XiaohuaModel.ShowapiResBodyBean.ContentlistBean) getIntent().getSerializableExtra("data");
        tvTitle.setText(model.getTitle());
        databinding.tvTitle1.setText(model.getTitle());
        databinding.tvContent.setText(Html.fromHtml(model.getText()));
    }
}
