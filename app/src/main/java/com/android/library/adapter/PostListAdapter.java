package com.android.library.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.library.R;
import com.android.library.models.Post;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wiki on 2018/1/17.
 */

public class PostListAdapter extends QuickAdapter<Post> {
    public PostListAdapter(Context context) {
        super(context, R.layout.item_post_list);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, Post item) {
        CircleImageView ivHeader = helper.getView(R.id.iv_header);
        helper
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_zan_num, item.getStar_count() + "赞")
                .setText(R.id.tv_see_num, item.getSeeNum() + "看过")
                .setText(R.id.tv_save_num, item.getSave_count() + "收藏")
                .setText(R.id.tv_comment_num, item.getComments_count() + "评论")
                .setText(R.id.tv_intro, item.getIntro())
        ;
        if (!TextUtils.isEmpty(item.getUserPhoto())) {
            Glide.with(context).load(item.getUserPhoto()).into(ivHeader);
        }
        if (!TextUtils.isEmpty(item.getImg1())) {
            Glide.with(context).load(item.getImg1()).into((ImageView) helper.getView(R.id.iv_1));
        }
        if (!TextUtils.isEmpty(item.getImg2())) {
            Glide.with(context).load(item.getImg2()).into((ImageView) helper.getView(R.id.iv_2));
        }
        if (!TextUtils.isEmpty(item.getImg3())) {
            Glide.with(context).load(item.getImg3()).into((ImageView) helper.getView(R.id.iv_3));
        }
    }
}
