package com.android.library.ui;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityManhuaDetailBinding;
import com.android.library.models.ManhuaDetail;
import com.android.library.models.ManhuaModel;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * author: Rea.x
 * date: 2017/12/5.
 */

public class ManhuaDetailActivity extends UIActivity<ActivityManhuaDetailBinding> {
    private ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean model;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_manhua_detail;
    }

    @Override
    protected void init() {
        model = (ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean) getIntent().getSerializableExtra("data");
        tvTitle.setText("漫画详情");
        request();
    }

    private void request() {
        showProgress();
        OkGo
                .<String>post("http://route.showapi.com/958-2")
                .params("showapi_appid", "51344")
                .params("id", model.getId())
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissProgress();
                        ManhuaDetail detail = new Gson().fromJson(response.body(), new TypeToken<ManhuaDetail>() {
                        }.getType());
                        createView(detail);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissProgress();
                    }
                });
    }

    private void createView(ManhuaDetail detail) {
        databinding.tvMhTitle.setText(detail.getShowapi_res_body().getItem().getTitle());
        databinding.tvMhTime.setText(detail.getShowapi_res_body().getItem().getTime());
        List<String> imgs = detail.getShowapi_res_body().getItem().getImgList();
        ImageView imageView;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (String img : imgs) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(this).load(img).into(imageView);
            databinding.layoutMahua.addView(imageView, params);
        }
    }
}
