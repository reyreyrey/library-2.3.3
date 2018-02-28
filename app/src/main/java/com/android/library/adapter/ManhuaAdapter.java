package com.android.library.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.library.R;
import com.android.library.models.ManhuaModel;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.List;


public class ManhuaAdapter extends QuickAdapter<ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {

    private Context context;

    public ManhuaAdapter(Context context) {
        super(context, R.layout.item_manhua);
        this.context = context;
    }


    @Override
    protected void convert(BaseAdapterHelper helper, ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean model) {
        helper.setText(R.id.tv_item_normal_title, model.getTitle());
        LinearLayout layout = helper.getView(R.id.img_layout);
        ImageView img1 = helper.getView(R.id.img_1);
        ImageView img2 = helper.getView(R.id.img_2);
        ImageView img3 = helper.getView(R.id.img_3);
        ImageView img4 = helper.getView(R.id.img_4);
        List<String> pics = model.getThumbnailList();
        if (pics == null || pics.size() == 0) {
            layout.setVisibility(View.GONE);
        } else {
            layout.setVisibility(View.VISIBLE);
        }

        if (pics.size() >= 1) {
            img1.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.VISIBLE);
            Glide.with(context).load(pics.get(0)).into(img4);
        }
    }
}