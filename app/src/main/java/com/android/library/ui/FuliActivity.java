package com.android.library.ui;


import android.text.TextUtils;

import com.android.library.R;
import com.android.library.adapter.FuliAdapter;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityArtListBinding;
import com.android.library.models.FuliModel;
import com.android.library.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * author: Rea.X
 * date: 2018/1/5.
 */

public class FuliActivity extends UIActivity<ActivityArtListBinding> implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private FuliAdapter adapter;
    private int currentPage = 1;
    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_art_list;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("福利");
        databinding.pullLoadMoreRecyclerView.setStaggeredGridLayout(3);//参数为列数
        adapter = new FuliAdapter(this);
        databinding.pullLoadMoreRecyclerView.setAdapter(adapter);
        databinding.pullLoadMoreRecyclerView.setRefreshing(true);
        databinding.pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        type = (int) (Math.random() * 4);
        query(true);
    }


    private void query(final boolean isRefresh) {
        OkGo
                .<String>post("http://route.showapi.com/1208-2")
                .params("showapi_appid", "53651")
                .params("page", "" + currentPage)
                .params("type", type + "")
                .params("rows", "20")
                .params("showapi_sign", "02ea2c1cd9ac49da84a9d8b409f59630")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        FuliModel model = new Gson().fromJson(response.body(), new TypeToken<FuliModel>() {
                        }.getType());
                        if (isRefresh) {
                            adapter.clear();
                        }
                        adapter.setData(model.getShowapi_res_body().getData());
                        databinding.pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
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


    @Override
    public void onRefresh() {
        currentPage = 1;
        query(true);
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        query(false);
    }
}
