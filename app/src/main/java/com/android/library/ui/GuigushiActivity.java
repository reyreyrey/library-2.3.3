package com.android.library.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.android.library.adapter.GuigushiAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.models.GuigushiModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class GuigushiActivity extends RefreshActivity<GuigushiModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {
    private GuigushiAdapter adapter;

    private List<String> types;

    private String selectType;

    private void getType() {
        types = new ArrayList<>();
        types.add("dp");
        types.add("cp");
        types.add("xy");
        types.add("yy");
        types.add("jl");
        types.add("mj");
        types.add("ly");
        types.add("yc");
        types.add("neihanguigushi");
        selectType = types.get((int) (Math.random() * types.size()));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getType();
        super.onRefresh(refreshlayout);
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("鬼故事");
        adapter = new GuigushiAdapter(context);
        getType();
        super.init();
    }

    @Override
    protected QuickAdapter<GuigushiModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getAdapter() {
        return adapter;
    }

    @Override
    protected void query() {
        OkGo
                .<String>post("http://route.showapi.com/955-1")
                .params("showapi_appid", "53651")
                .params("page", currentPage + "")
                .params("type", selectType)
                .params("showapi_sign", "02ea2c1cd9ac49da84a9d8b409f59630")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        GuigushiModel model = new Gson().fromJson(response.body(), new TypeToken<GuigushiModel>() {
                        }.getType());
                        List<GuigushiModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = model.getShowapi_res_body().getPagebean().getContentlist();
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        GuigushiDetailActivity.seeGushiDetail(context, adapter.getItem(i).getId(), adapter.getItem(i).getTitle());
    }
}
