package com.android.library.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.android.library.R;
import com.android.library.models.PostCommentModel;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 * Created by wiki on 2018/1/17.
 */

public class CommentReplyAdapter extends QuickAdapter<PostCommentModel.ReplyBean>{
    public CommentReplyAdapter(Context context) {
        super(context, R.layout.item_comment_reply);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, PostCommentModel.ReplyBean item) {
        TextView textView = helper.getView(R.id.tv_content);
        String username = item.getUserNmae();
        String content = item.getReplyText();

        String str = username + ":" + content;
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.BLUE), 0, username.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), username.length() + 1, (str.length()-1), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(style);
    }
}
