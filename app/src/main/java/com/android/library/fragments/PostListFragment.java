package com.android.library.fragments;

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
 */

public class PostListFragment extends RefreshFragment<Post>{
    private PostListAdapter adapter;

    @Override
    protected void init(View view) {
        adapter = new PostListAdapter(getContext());
        super.init(view);
    }

    @Override
    protected QuickAdapter<Post> getAdapter() {
        return adapter;
    }

    @Override
    protected void query() {
        OkGo.<String>get(Cons.POST_LIST_URL).params("page", currentPage).execute(new StringCallback() {
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
        Post post = adapter.getItem(i);
        PostDetailActivity.seePostDetail(getContext(), post);
    }
}
