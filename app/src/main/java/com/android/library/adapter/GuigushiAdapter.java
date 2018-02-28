package com.android.library.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.android.library.R;
import com.android.library.models.GuigushiModel;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class GuigushiAdapter extends QuickAdapter<GuigushiModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {
    public GuigushiAdapter(Context context) {
        super(context, R.layout.item_weixin);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, GuigushiModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean model) {
        ImageView imageView = helper.getView(R.id.img_head);
        Glide.with(context).load(model.getImg()).into(imageView);
        helper.setText(R.id.tv_item_normal_title, model.getTitle()).setText(R.id.tv_item_normal_detail, model.getDesc());
    }
}
