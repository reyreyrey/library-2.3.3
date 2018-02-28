package com.android.library.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.android.library.R;
import com.android.library.models.WXMeiwen;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

public class WeixinmeiwenAdapter extends QuickAdapter<WXMeiwen.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {

    private Activity activity;

    public WeixinmeiwenAdapter(Context context) {
        super(context, R.layout.item_weixin);
        this.activity = (Activity) context;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, WXMeiwen.ShowapiResBodyBean.PagebeanBean.ContentlistBean model) {
        ImageView imageView = helper.getView(R.id.img_head);
        Glide.with(activity).load(model.getContentImg()).into(imageView);
        helper.setText(R.id.tv_item_normal_title, model.getTitle()).setText(R.id.tv_item_normal_detail, model.getUserName() + "  " + model.getDate());
    }
}