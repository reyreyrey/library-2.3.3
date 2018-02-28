package com.android.library.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityPostDetailBinding;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.Post;
import com.android.library.models.PostDetailModel;
import com.android.library.models.UserModel;
import com.android.library.utils.Cons;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.smtt.sdk.WebSettings;
import com.wx.goodview.GoodView;

/**
 * Created by wiki on 2018/1/17.
 */

public class PostDetailActivity extends UIActivity<ActivityPostDetailBinding> implements View.OnClickListener {
    public static void seePostDetail(Context context, Post post) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("data", post);
        context.startActivity(intent);
    }

    private Post post;
    private GoodView goodView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_detail;
    }

    @Override
    protected void init() {
        tvTitle.setText("详情");
        goodView = new GoodView(this);
        post = (Post) getIntent().getSerializableExtra("data");
        databinding.ivStar.setOnClickListener(this);
        databinding.ivSave.setOnClickListener(this);
        databinding.tvCommentCount.setText(post.getComments_count());
        databinding.ivComment.setOnClickListener(this);
        databinding.wbContent.getWebView().getSettings().setTextSize(WebSettings.TextSize.LARGEST);
        getDetail();
    }

    private void getDetail() {
        OkGo.<String>get(Cons.POST_DETAIL_URL + post.getId())
                .params("user_id", UserManager.isLogin() ? UserManager.getCurrentUser().getUserid() : "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body();
                        BaseModel<PostDetailModel> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<PostDetailModel>>() {
                        }.getType());
                        databinding.wbContent.getWebView().loadDataWithBaseURL(Cons.BASE_URL, baseModel.getData().getContent(), "text/html", "utf-8", null);
                        databinding.ivStar.setImageResource(baseModel.getData().isUserStar() ? R.drawable.ic_zan_detail_success : R.drawable.ic_zan_detail);
                        databinding.ivSave.setImageResource(baseModel.getData().isUserSave() ? R.drawable.ic_save_success : R.drawable.ic_save_detail);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_star) {
            if(UserManager.isLogin()){
                postStar();
            }else{
                startActivity(new Intent(context, LoginActivity.class));
            }
            return;
        }
        if (v.getId() == R.id.iv_save) {
            if(UserManager.isLogin()){
                postSave();
            }else{
                startActivity(new Intent(context, LoginActivity.class));
            }
            return;
        }
        if(v.getId() == R.id.iv_comment){
            if(UserManager.isLogin()){
                CommentListActivity.commentList(context, post);
            }else{
                startActivity(new Intent(context, LoginActivity.class));
            }
            return;
        }
    }

    private void postSave(){
        OkGo.<String>get(Cons.POST_SAVE_URL)
                .params("user_id", UserManager.getCurrentUser().getUserid())
                .params("post_id", post.getId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body();
                        BaseModel<Boolean> baseModel = new Gson().fromJson(result, BaseModel.class);
                        int res = baseModel.getData() ? R.drawable.ic_save_success : R.drawable.ic_save_detail;
                        databinding.ivSave.setImageResource(res);
                        goodView.setImage(res);
                        goodView.show(databinding.ivSave);
                    }
                });
    }

    private void postStar(){
        OkGo.<String>get(Cons.POST_STAR_URL)
                .params("user_id", UserManager.getCurrentUser().getUserid())
                .params("post_id", post.getId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String result = response.body();
                        BaseModel<Boolean> baseModel = new Gson().fromJson(result, BaseModel.class);
                        int res = baseModel.getData() ? R.drawable.ic_zan_detail_success : R.drawable.ic_zan_detail;
                        databinding.ivStar.setImageResource(res);
                        goodView.setImage(res);
                        goodView.show(databinding.ivStar);
                    }
                });
    }
}
