package com.android.library.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.android.library.adapter.ManhuaAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.models.ManhuaModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class ManhuaListActivity extends RefreshActivity<ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {

    private ManhuaAdapter mAdapter;
    private List<String> types;

    private String selectType;

    private static String type1 = "/category/weimanhua/kbmh";
    private static String type2 = "/category/weimanhua/gushimanhua";
    private static String type3 = "/category/duanzishou";
    private static String type4 = "/category/lengzhishi";
    private static String type5 = "/category/qiqu";
    private static String type6 = "/category/dianying";
    private static String type7 = "/category/gaoxiao";
    private static String type8 = "/category/mengchong";
    private static String type9 = "/category/xinqi";
    private static String type10 = "/category/huanqiu";
    private static String type11 = "/category/sheying";
    private static String type12 = "/category/wanyi";
    private static String type13 = "/category/chahua";

    private void getType() {
        types = new ArrayList<>();
        types.add(type1);
        types.add(type2);
        types.add(type3);
        types.add(type4);
        types.add(type5);
        types.add(type6);
        types.add(type7);
        types.add(type8);
        types.add(type9);
        types.add(type10);
        types.add(type11);
        types.add(type12);
        types.add(type13);
        selectType = types.get((int) (Math.random() * types.size()));
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("漫画");
        mAdapter = new ManhuaAdapter(context);
        getType();
        super.init();
    }

    @Override
    protected QuickAdapter<ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getAdapter() {
        return mAdapter;
    }

    @Override
    protected void query() {
        OkGo
                .<String>post("http://route.showapi.com/958-1")
                .params("showapi_appid", "51344")
                .params("page", currentPage + "")
                .params("type", selectType)
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ManhuaModel xiaohuaModel = new Gson().fromJson(response.body(), new TypeToken<ManhuaModel>() {
                        }.getType());
                        List<ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = xiaohuaModel.getShowapi_res_body().getPagebean().getContentlist();
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
        Intent intent = new Intent(context, ManhuaDetailActivity.class);
        intent.putExtra("data", (Serializable) mAdapter.getItem(i));
        startActivity(intent);
    }
}
