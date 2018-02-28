package com.android.library.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;

import com.android.library.R;
import com.android.library.adapter.OtherUserPagerAdapter;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityOtherUserBinding;
import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.UserModel;
import com.android.library.ui.dialogs.PhotoViewDialog;
import com.android.library.utils.ProgressDialogUtils;
import com.android.library.utils.ScreenUtil;
import com.android.library.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.zhouwei.blurlibrary.EasyBlur;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.android.library.utils.Cons.USERINFO_BY_ID_URL;
import static com.android.library.utils.Cons.USER_ATT_URL;

/**
 * Created by wiki on 2018/1/19.
 */

public class OtherUserActivity extends UIActivity<ActivityOtherUserBinding> implements View.OnClickListener {
    private OtherUserPagerAdapter adapter;
    private String otherUserid;
    private UserModel otherUserModel;

    public static void seeOtherUser(Context context, String userid, String username) {
        Intent intent = new Intent(context, OtherUserActivity.class);
        intent.putExtra("userid", userid);
        intent.putExtra("username", username);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_user;
    }

    @Override
    protected void init() {
        String username = getIntent().getStringExtra("username");
        tvTitle.setText(username);
        otherUserid = getIntent().getStringExtra("userid");
        adapter = new OtherUserPagerAdapter(getSupportFragmentManager(), otherUserid);
        databinding.viewpager.setAdapter(adapter);
        databinding.tablayout.setupWithViewPager(databinding.viewpager);
        databinding.imgHeader.setOnClickListener(this);
        databinding.tvChat.setOnClickListener(this);
        databinding.tvAttation.setOnClickListener(this);
        queryUser();
    }

    private void queryUser() {
        showProgress();
        UserModel model = UserManager.getCurrentUser();
        GetRequest request = OkGo.<String>get(USERINFO_BY_ID_URL + otherUserid);
        if (model != null) {
            request.params("current_userid", model.getUserid());
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                dismissProgress();
                String result = response.body();
                BaseModel<UserModel> baseModel = new Gson().fromJson(result, new TypeToken<BaseModel<UserModel>>() {
                }.getType());
                if (baseModel.getSuccess() == 1) {
                    otherUserModel = baseModel.getData();
                    UserManager.saveOtherUser(otherUserModel);
                    refreshUI();
                } else {
                    dismissProgress();
                    ToastUtils.toastError(context, baseModel.getMsg());
                }
            }
        });
    }

    private void refreshUI() {
        String photo = otherUserModel.getPhoto();
        databinding.tvUsername.setText(otherUserModel.getNickname());
        databinding.tvUsersign.setText(otherUserModel.getSign());
        databinding.tvAttFan.setText("关注：" + (TextUtils.isEmpty(otherUserModel.getAttCount()) ? "0" :otherUserModel.getAttCount()) + " | 粉丝：" + (TextUtils.isEmpty(otherUserModel.getFanCount()) ? "0" : otherUserModel.getFanCount()));
        boolean isAtt = otherUserModel.isIs_attention();
        if (isAtt) {
            databinding.tvAttation.setText("取消关注");
        } else {
            databinding.tvAttation.setText("关注");
        }

        if (!TextUtils.isEmpty(photo)) {
            io.reactivex.Observable
                    .just(photo)
                    .subscribeOn(Schedulers.io())
                    .map(new Function<String, Bitmap>() {
                        @Override
                        public Bitmap apply(String photo) throws Exception {
                            return Glide.with(context)
                                    .load(photo)
                                    .asBitmap() //必须
                                    .fitCenter()
                                    .into(ScreenUtil.getScreenWidth(context), ScreenUtil.dp2px(context, 180))
                                    .get();
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(new Function<Bitmap, Bitmap>() {
                        @Override
                        public Bitmap apply(Bitmap bitmap) throws Exception {
                            databinding.imgHeader.setImageBitmap(bitmap);
                            return EasyBlur.with(context)
                                    .bitmap(bitmap) //要模糊的图片
                                    .radius(100)//模糊半径
                                    .scale(1)
                                    .policy(EasyBlur.BlurPolicy.FAST_BLUR)
                                    .blur();
                        }
                    })
                    .subscribe(new Consumer<Bitmap>() {
                        @Override
                        public void accept(Bitmap bitmap) throws Exception {
                            databinding.ivBackground.setBackground(new BitmapDrawable(bitmap));
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_attation) {
            flow();
            return;
        }
        if (v.getId() == R.id.tv_chat) {
            ChatActivity.chat(this, otherUserModel.getUsername());
            return;
        }
        if (v.getId() == R.id.img_header) {
            String src = otherUserModel.getPhoto();
            if (!TextUtils.isEmpty(src))
                PhotoViewDialog.seeBigImage(this, src);
            return;
        }
    }

    private void flow() {
        UserModel model = UserManager.getCurrentUser();
        if (model == null) {
            ToastUtils.toastError(context, "请先登录");
            startActivity(new Intent(context, LoginActivity.class));
            return;
        }
        showProgress();
        OkGo.<String>get(USER_ATT_URL)
                .params("user_id", model.getUserid())
                .params("attention_user_id", otherUserModel.getUserid())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dismissProgress();
                        String result = response.body();
                        BaseModel<Boolean> baseModel = new Gson().fromJson(result, BaseModel.class);
                        if (baseModel.getSuccess() == 1) {
                            boolean isAtt = baseModel.getData();
                            otherUserModel.setIs_attention(isAtt);
                            refreshUI();
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
}
