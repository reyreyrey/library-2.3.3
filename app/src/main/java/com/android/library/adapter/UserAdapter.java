package com.android.library.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.library.R;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.UserModel;
import com.android.library.ui.ChatActivity;
import com.android.library.utils.ProgressDialogUtils;
import com.android.library.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import static com.android.library.utils.Cons.USER_ATT_URL;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class UserAdapter extends QuickAdapter<UserModel> {
    private boolean showAttation = true;
    private AppCompatActivity appCompatActivity;
    private String key;

    public UserAdapter(Context context) {
        super(context, R.layout.item_user);
        showAttation = true;
        this.appCompatActivity = (AppCompatActivity) context;
    }

    public UserAdapter(Context context, boolean showAttation) {
        super(context, R.layout.item_user);
        this.showAttation = showAttation;
        this.appCompatActivity = (AppCompatActivity) context;
    }

    public UserAdapter(Context context, boolean showAttation, String key) {
        super(context, R.layout.item_user);
        this.showAttation = showAttation;
        this.appCompatActivity = (AppCompatActivity) context;
        this.key = key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, final UserModel item) {
        helper.setText(R.id.tv_sign, item.getSign());
        ImageView imageView = helper.getView(R.id.iv_header);
        String photo = item.getPhoto();
        if (!TextUtils.isEmpty(photo)) {
            Glide.with(context).load(photo).into(imageView);
        }
        final boolean isAtt = item.isIs_attention();
        ImageView ivGuanzhu = helper.getView(R.id.iv_guanzhu);
        ImageView ivChat = helper.getView(R.id.iv_chat);
        if (showAttation) {
            ivGuanzhu.setVisibility(View.VISIBLE);
        } else {
            ivGuanzhu.setVisibility(View.GONE);
        }
        ivGuanzhu.setImageResource(isAtt ? R.drawable.ic_item_guanzhued : R.drawable.ic_item_guanzhu);
        ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toChat(item);
            }
        });
        ivGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flower(item);
            }
        });


        String str = item.getNickname() + "(" + item.getUsername() + ")";
        TextView tvName = helper.getView(R.id.tv_username);
        if (!TextUtils.isEmpty(key)) {
            int index_username = str.indexOf(key);
            if (index_username >= 0) {
                tvName.setText(colorStyle(str, index_username, key.length()));
            } else {
                helper.setText(R.id.tv_username, item.getNickname() + "(" + item.getUsername() + ")");
            }
        } else {
            helper.setText(R.id.tv_username, item.getNickname() + "(" + item.getUsername() + ")");
        }
    }

    private SpannableStringBuilder colorStyle(String str, int index, int len) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(Color.BLUE), index, index+len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    private void flower(final UserModel item) {
        ProgressDialogUtils.showProgress(context);
        OkGo.<String>get(USER_ATT_URL)
                .params("user_id", UserManager.getCurrentUser().getUserid())
                .params("attention_user_id", item.getUserid())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ProgressDialogUtils.dismissProgress();
                        String result = response.body();
                        BaseModel<Boolean> baseModel = new Gson().fromJson(result, BaseModel.class);
                        if (baseModel.getSuccess() == 1) {
                            boolean isAtt = baseModel.getData();
                            item.setIs_attention(isAtt);
                            notifyDataSetChanged();
                            ToastUtils.toastError(context, "操作成功");
                        } else {
                            ToastUtils.toastError(context, "操作失败");
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ProgressDialogUtils.dismissProgress();
                        ToastUtils.toastError(context, "操作失败");
                    }
                });

    }

    private void toChat(final UserModel item) {
        ChatActivity.chat(appCompatActivity, item.getUsername());
    }
}
