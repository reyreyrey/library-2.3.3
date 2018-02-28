package com.android.library.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.android.library.adapter.XiaohuaAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.models.XiaohuaModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;


public class XiaohuaActivity extends RefreshActivity<XiaohuaModel.ShowapiResBodyBean.ContentlistBean> {
    private XiaohuaAdapter mAdapter;


    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("每日一笑");
        mAdapter = new XiaohuaAdapter(context);
        super.init();
    }

    @Override
    protected QuickAdapter<XiaohuaModel.ShowapiResBodyBean.ContentlistBean> getAdapter() {
        return mAdapter;
    }

    @Override
    protected void query() {
        OkGo
                .<String>post("http://route.showapi.com/341-1")
                .params("showapi_appid", "51344")
                .params("page", (currentPage + 1) + "")
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        XiaohuaModel xiaohuaModel = new Gson().fromJson(response.body(), new TypeToken<XiaohuaModel>() {
                        }.getType());
                        List<XiaohuaModel.ShowapiResBodyBean.ContentlistBean> list = xiaohuaModel.getShowapi_res_body().getContentlist();
                        if (isRefresh) {
                            mAdapter.clear();
                        }
                        mAdapter.addAll(list);
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, XiaohuaDetailActivity.class);
        intent.putExtra("data", adapter.getItem(position));
        startActivity(intent);
    }

}
