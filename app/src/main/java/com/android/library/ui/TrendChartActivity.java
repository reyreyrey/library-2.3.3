package com.android.library.ui;

import android.text.TextUtils;

import com.android.library.adapter.TrendChartAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.models.CaiPiaoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class TrendChartActivity extends RefreshActivity<CaiPiaoModel.ShowapiResBodyBean.ResultBean> {

    private TrendChartAdapter adapter;

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("近期开奖");
        adapter = new TrendChartAdapter(context);
        super.init();
    }

    @Override
    protected QuickAdapter<CaiPiaoModel.ShowapiResBodyBean.ResultBean> getAdapter() {
        return adapter;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore();
    }

    @Override
    protected void query() {
        OkGo
                .<String>post("http://route.showapi.com/44-1")
                .params("showapi_appid", "51344")
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CaiPiaoModel xiaohuaModel = new Gson().fromJson(response.body(), new TypeToken<CaiPiaoModel>() {
                        }.getType());
                        List<CaiPiaoModel.ShowapiResBodyBean.ResultBean> list = xiaohuaModel.getShowapi_res_body().getResult();
                        if (isRefresh) {
                            adapter.clear();
                        }
                        adapter.addAll(list);
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        refreshView();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        smartRefreshLayout.finishRefresh();
                        smartRefreshLayout.finishLoadmore();
                        refreshView();
                    }
                });
    }
}
