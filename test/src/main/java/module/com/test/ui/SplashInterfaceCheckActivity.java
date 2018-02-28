package module.com.test.ui;

import android.content.Intent;

import com.android.library.ui.SplashBaseActivity;
import com.android.library.utils.ResourceUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import module.com.test.BuildConfig;
import module.com.test.R;

/**
 * author: Rea.X
 * date: 2017/12/20.
 */

public class SplashInterfaceCheckActivity extends SplashBaseActivity{

    @Override
    protected void toMain() {
        //跳转到主界面
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    @Override
    protected String getAppID() {
        //返回在后台添加的appid
        return "2";
    }
    @Override
    protected int getSplashImageRes() {
        //无需修改
        return ResourceUtil.getDrawableId(this, BuildConfig.SPLASH_PIC);
    }
    @Override
    protected boolean isShowGuide() {
        //是否显示引导页（开关开启的时候才会读这个配置）
        return false;
    }
    @Override
    protected int[] guideRess() {
        //引导页图片资源数组
        return null;
    }

    @Override
    protected Date showCaipiaoSplashPicTime() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-01-24 11:00:00");
        } catch (ParseException e) {
        }
        return null;
    }

}
