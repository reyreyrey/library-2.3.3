package com.android.library.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.library.R;
import com.android.library.adapter.CommentListAdapter;
import com.android.library.base.RefreshActivity;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.Post;
import com.android.library.models.PostCommentModel;
import com.android.library.models.UserModel;
import com.android.library.utils.Cons;
import com.android.library.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * Created by wiki on 2018/1/17.
 */

public class CommentListActivity extends RefreshActivity<PostCommentModel> implements View.OnClickListener {
    public static void commentList(Context context, Post post) {
        Intent intent = new Intent(context, CommentListActivity.class);
        intent.putExtra("data", post);
        context.startActivity(intent);
    }

    private Post post;
    private CommentListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_list;
    }

    @Override
    protected void init() {
        tvTitle.setText("评论");
        post = (Post) getIntent().getSerializableExtra("data");
        adapter = new CommentListAdapter(context, (EditText) findViewById(R.id.edt_reply));
        findViewById(R.id.tv_send).setOnClickListener(this);
        super.init();


        adapter.reset();
    }

    @Override
    protected QuickAdapter<PostCommentModel> getAdapter() {
        return adapter;
    }

    @Override
    protected void query() {
        OkGo.<String>get(Cons.POST_COMMENT_URL + post.getId())
                .params("page", currentPage)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                BaseModel<List<PostCommentModel>> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<List<PostCommentModel>>>() {
                }.getType());
                if (baseModel.getSuccess() == 1) {
                    List<PostCommentModel> list = baseModel.getData();
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
    public void onClick(View v) {
        if (v.getId() == R.id.tv_send) {
            sendComment();
        }
    }

    private void sendComment() {
        final EditText editText = (EditText) findViewById(R.id.edt_reply);
        String comment = editText.getText().toString().trim();
        if (TextUtils.isEmpty(comment)) {
            ToastUtils.toastError(context, "回复内容不能为空");
            return;
        }
        UserModel model = UserManager.getCurrentUser();
        if (model == null) {
            startActivity(new Intent(context, LoginActivity.class));
            ToastUtils.toastError(context, "请先登录");
        }
        String currentUserid = model.getUserid();
        String postId = post.getId();
        String replyCommentId = adapter.getSelectCommentId();
        OkGo.<String>get(Cons.ADD_COMMENT_URL)
                .params("comment", comment)
                .params("user_id", currentUserid)
                .params("post_id", postId)
                .params("comment_id", replyCommentId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body();
                        BaseModel<List<PostCommentModel>> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<List<PostCommentModel>>>() {
                        }.getType());
                        if (baseModel.getSuccess() == 1) {
                            List<PostCommentModel> list = baseModel.getData();
                            adapter.clear();
                            adapter.addAll(list);
                            adapter.reset();
                            editText.setText("");
                        }
                    }
                });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        super.onRefresh(refreshlayout);
        adapter.reset();
    }
}
