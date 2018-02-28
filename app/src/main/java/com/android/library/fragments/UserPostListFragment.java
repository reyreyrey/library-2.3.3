package com.android.library.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.library.adapter.PostListAdapter;
import com.android.library.base.RefreshFragment;
import com.android.library.models.BaseModel;
import com.android.library.models.Post;
import com.android.library.ui.PostDetailActivity;
import com.android.library.utils.Cons;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by wiki on 2018/1/17.
 * 他人发表的文章列表
 */

public class UserPostListFragment extends RefreshFragment<Post> {
    private PostListAdapter adapter;
    private String currentUserid;

    public static UserPostListFragment instants(String user_id) {
        UserPostListFragment fragment = new UserPostListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userid", user_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init(View view) {
        currentUserid = getArguments().getString("userid");
        adapter = new PostListAdapter(getContext());
        super.init(view);
    }

    @Override
    protected QuickAdapter<Post> getAdapter() {
        return adapter;
    }

    @Override
    protected void query() {
        OkGo.<String>get(Cons.MY_POST_URL + currentUserid).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                BaseModel<List<Post>> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<List<Post>>>() {
                }.getType());
                if (baseModel.getSuccess() == 1) {
                    List<Post> list = baseModel.getData();
                    if (isRefresh) {
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
    protected boolean enableLoadmore() {
        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        Post post = adapter.getItem(i);
        PostDetailActivity.seePostDetail(getContext(), post);
    }
}
