package com.android.library.ui;

import android.content.Context;
import android.content.Intent;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityGuigushiDetailBinding;
import com.android.library.models.GuigushiDetailModel;
import com.android.library.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class GuigushiDetailActivity extends UIActivity<ActivityGuigushiDetailBinding> {
    public static void seeGushiDetail(Context context, String id, String title) {
        Intent intent = new Intent(context, GuigushiDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guigushi_detail;
    }

    @Override
    protected void init() {
        tvTitle.setText(getIntent().getStringExtra("title"));
        String id = getIntent().getStringExtra("id");
        query(id);
    }

    private void query(String id) {
        showProgress();
        OkGo
                .<String>post("http://route.showapi.com/955-2")
                .params("showapi_appid", "53651")
                .params("page", "1")
                .params("id", id)
                .params("showapi_sign", "02ea2c1cd9ac49da84a9d8b409f59630")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissProgress();
                        GuigushiDetailModel model = new Gson().fromJson(response.body(), new TypeToken<GuigushiDetailModel>() {
                        }.getType());
                        databinding.tvContent.setText(model.getShowapi_res_body().getText().replaceAll("neirong336", "").replaceAll("&nbsp;", " ").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\;", ""));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissProgress();
                        ToastUtils.toastError(context, "网络错误，请重试");
                        finish();
                    }
                });
    }
}
