package com.android.library;

import android.app.Application;

import com.android.library.manager.UserManager;
import com.android.library.models.BaseModel;
import com.android.library.models.UserModel;
import com.android.library.utils.activity_manager.ActivityManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import cn.jpush.android.api.JPushInterface;
import library.app.AppContext;
import library.app.ReturnCode;
import library.app.ReturnCodeConfig;
import library.cache.DataProvider;
import library.network.NetworkConfig;
import library.util.LogUtil;

import static com.android.library.utils.Cons.USERINFO_URL;

/**
 * author: Rea.X
 * date: 2017/12/18.
 */

public class Library {

    public static void init(Application app, boolean isDebug) {
        Library.app = app;
        Library.isDebug = isDebug;
        JPushInterface.setDebugMode(isDebug);
        JPushInterface.init(app);
        ActivityManager.startWatcher(app);

        initEaseUI(app);
        AppContext.getInstance().init(app);
        LogUtil.init(BuildConfig.DEBUG_LOG, "lucky");
        DataProvider.init(app);
        NetworkConfig.setBaseUrl(BuildConfig.HOST_URL);
        ReturnCodeConfig.getInstance().initReturnCode(ReturnCode.CODE_SUCCESS, ReturnCode.CODE_EMPTY);
    }


    private static void initEaseUI(Application app) {
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(true);
        EaseUI.getInstance().init(app, options);
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {
                EaseUser easeUser = new EaseUser(username);
                UserModel userModel = UserManager.getUser(username);
                if (userModel != null) {
                    easeUser.setNickname(userModel.getNickname());
                    easeUser.setAvatar(userModel.getPhoto());
                    return easeUser;
                }
                requestSaveUserInfo(username);
                return null;
            }
        });
    }

    private static Application app;
    private static boolean isDebug;

    public static Application get() {
        return app;
    }

    public static boolean isDebug() {
        return isDebug;
    }


    public static void requestSaveUserInfo(String username){
        OkGo.<String>get(USERINFO_URL + username).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body();
                BaseModel<UserModel> model = new Gson().fromJson(result, new TypeToken<BaseModel<UserModel>>() {
                }.getType());
                UserModel userModel1 = model.getData();
                UserManager.saveOtherUser(userModel1);
            }
        });
    }
}
