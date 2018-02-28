package com.android.library.utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.android.library.R;


/**
 * Created by fei on 2015/7/30.
 */
public class MyAnimationUtils {

    public static void viewShake(View view){
        TranslateAnimation animation = new TranslateAnimation(0, 100, 0, 0);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(200);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.RESTART);
        view.startAnimation(animation);
    }
    /**
     * 进入页面动画
     * @param activity
     */
    public static void enterActivity(Activity activity){
        activity.overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
    }

    /**
     * 退出页面动画
     * @param activity
     */
    public static void exitActivity(Activity activity){
        activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
