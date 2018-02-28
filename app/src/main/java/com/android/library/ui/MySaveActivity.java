package com.android.library.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.android.library.adapter.PostListAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.Post;
import com.android.library.utils.Cons;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class MySaveActivity extends RefreshActivity<Post> {
    @Override
    protected void init() {
        super.init();
        String title = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(title))
            tvTitle.setText(title);
        else
            tvTitle.setText("我的收藏");
    }


    @Override
    protected QuickAdapter<Post> getAdapter() {
        return new PostListAdapter(context);
    }

    @Override
    protected void query() {
        OkGo.<String>get(Cons.MY_SAVE_URL+ UserManager.getCurrentUser().getUserid()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                BaseModel<List<Post>> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<List<Post>>>(){}.getType());
                if(baseModel.getSuccess() == 1){
                    List<Post> list = baseModel.getData();
                    if(isRefresh){
                        adapter.clear();
                    }
                    adapter.addAll(list);
                }
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
        PostDetailActivity.seePostDetail(context, adapter.getItem(i));
    }

    @Override
    protected boolean enableLoadmore() {
        return false;
    }
}
