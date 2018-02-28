package com.android.library.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.library.R;
import com.android.library.models.PostCommentModel;
import com.android.library.utils.SoftKeyboardUtils;
import com.bumptech.glide.Glide;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wiki on 2018/1/17.
 */

public class CommentListAdapter extends QuickAdapter<PostCommentModel> {
    private String selectCommentId;
    private EditText editText;

    public CommentListAdapter(Context context, EditText editText) {
        super(context, R.layout.item_comment_list);
        this.editText = editText;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final PostCommentModel item) {
        CircleImageView ivHeader = helper.getView(R.id.iv_header);
        ListView listview = helper.getView(R.id.lv_reply);
        helper.setText(R.id.tv_username, item.getUserNmae())
                .setText(R.id.tv_content, item.getCommentText());
        if (!TextUtils.isEmpty(item.getUserPhoto())) {
            Glide.with(context).load(item.getUserPhoto()).into(ivHeader);
        }

        CommentReplyAdapter adapter = new CommentReplyAdapter(context);
        listview.setAdapter(adapter);
        adapter.addAll(item.getReply());

        helper.setOnClickListener(R.id.tv_reply, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setHint("回复：" + item.getUserNmae());
                SoftKeyboardUtils.showSoftKeyboard(context, editText);
                selectCommentId = item.getCommentId();
            }
        });
    }

    public String getSelectCommentId() {
        return selectCommentId;
    }

    public void reset() {
        editText.setHint("请输入内容" );
        SoftKeyboardUtils.togSoftkeybord(context, editText, false);
        SoftKeyboardUtils.hideSoftKeyboard(context);
        SoftKeyboardUtils.hideSoftKeyboard(context, editText);
        this.selectCommentId = null;
    }
}
