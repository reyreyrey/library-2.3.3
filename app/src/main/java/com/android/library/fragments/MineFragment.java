package com.android.library.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.android.library.R;
import com.android.library.base.UIBaseFragment;
import com.android.library.databinding.FragmentMineBinding;
import com.android.library.manager.UIManager;
import com.android.library.manager.UserManager;
import com.android.library.models.UserModel;
import com.android.library.ui.EditUserMessageActivity;
import com.android.library.ui.FanActivity;
import com.android.library.ui.MyAttentionActivity;
import com.android.library.ui.MyFabuActivity;
import com.android.library.ui.MySaveActivity;
import com.android.library.utils.ScreenUtil;
import com.bumptech.glide.Glide;
import com.zhouwei.blurlibrary.EasyBlur;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Rea.X
 * date: 2017/12/11.
 */

public class MineFragment extends UIBaseFragment<FragmentMineBinding> implements View.OnClickListener {
    private UserModel userModel;

    public static Fragment getInstant() {
        return new MineFragment();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(View view) {
        databinding.tvMyAttation.setOnClickListener(this);
        databinding.tvMyFan.setOnClickListener(this);
        databinding.tvMySave.setOnClickListener(this);
        databinding.ivArrow.setOnClickListener(this);
        databinding.imgHeader.setOnClickListener(this);
        databinding.tvMyFabu.setOnClickListener(this);
        databinding.tvGuanyu.setOnClickListener(this);
        databinding.tvFeedback.setOnClickListener(this);
    }

    @Override
    protected void lzayLoadEveryVisible() {
        super.lzayLoadEveryVisible();
        refreshUser();
    }

    private void refreshUser() {
        userModel = UserManager.getCurrentUser();
        if (userModel == null) return;
        databinding.tvUsername.setText(userModel.getNickname());
        String sign = userModel.getSign();
        if (!TextUtils.isEmpty(sign)) {
            databinding.tvUsersign.setText(sign);
        }
        String photo = userModel.getPhoto();
        if (!TextUtils.isEmpty(photo)) {
            setBackground(photo);
        }
    }

    private void setBackground(String photo) {
        io.reactivex.Observable
                .just(photo)
                .subscribeOn(Schedulers.io())
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String photo) throws Exception {
                        return Glide.with(getContext())
                                .load(photo)
                                .asBitmap() //必须
                                .fitCenter()
                                .into(ScreenUtil.getScreenWidth(getContext()), ScreenUtil.dp2px(getContext(), 180))
                                .get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        databinding.imgHeader.setImageBitmap(bitmap);
                        return EasyBlur.with(getContext())
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
                        databinding.ivBackagoud.setBackground(new BitmapDrawable(bitmap));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUser();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_my_attation) {
            startActivity(new Intent(getContext(), MyAttentionActivity.class));
            return;
        }
        if (view.getId() == R.id.tv_my_fan) {
            startActivity(new Intent(getContext(), FanActivity.class));
            return;
        }
        if (view.getId() == R.id.tv_my_save) {
            startActivity(new Intent(getContext(), MySaveActivity.class));
            return;
        }
        if (view.getId() == R.id.iv_arrow || view.getId() == R.id.img_header) {
            startActivity(new Intent(getContext(), EditUserMessageActivity.class));
            return;
        }
        if (view.getId() == R.id.tv_my_fabu) {
            startActivity(new Intent(getContext(), MyFabuActivity.class));
            return;
        }
        if (view.getId() == R.id.tv_guanyu) {
            UIManager.about(getContext());
            return;
        }
        if (view.getId() == R.id.tv_feedback) {
            UIManager.feedback(getContext());
            return;
        }
    }
}
