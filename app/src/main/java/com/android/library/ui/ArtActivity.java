package com.android.library.ui;


import android.text.TextUtils;

import com.android.library.R;
import com.android.library.adapter.ArtListAdapter;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityArtListBinding;
import com.android.library.models.ArtListModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * author: Rea.X
 * date: 2018/1/3.
 */

public class ArtActivity extends UIActivity<ActivityArtListBinding> implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ArtListAdapter adapter;
    private int currentPage = 1;

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
            tvTitle.setText("艺术鉴赏");
        databinding.pullLoadMoreRecyclerView.setStaggeredGridLayout(2);//参数为列数
        adapter = new ArtListAdapter(this);
        databinding.pullLoadMoreRecyclerView.setAdapter(adapter);
        databinding.pullLoadMoreRecyclerView.setRefreshing(true);
        databinding.pullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        query(true);
    }

    private void query(final boolean isRefresh) {
        OkGo
                .<String>get("http://api.artgoer.cn:8084/artgoer/api/v1/user/0/v3/disExhibitsAndInit?citySort=0&isRecommend=1&pageIndex=" + currentPage + "&timeSort=0&token=df68e038-143e-41cb-b554-456f78f184fc")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ArtListModel model = new Gson().fromJson(response.body(), new TypeToken<ArtListModel>() {
                        }.getType());
                        if (isRefresh) {
                            adapter.clear();
                        }
                        adapter.setData(model);
                        databinding.pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
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
