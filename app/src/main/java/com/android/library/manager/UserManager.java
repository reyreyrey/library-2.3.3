package com.android.library.manager;

import com.android.library.Library;
import com.android.library.models.UserModel;
import com.android.library.utils.SharedPreferencesUtils;

/**
 * author: Rea.X
 * date: 2018/1/10.
 */

public class UserManager {
    private static final String KEY_CURRENT_USER = "current_user";

    public static UserModel getCurrentUser() {
        Object o = SharedPreferencesUtils.readObject(Library.get(), KEY_CURRENT_USER);
        if (o != null && !o.equals("")) {
            return (UserModel) o;
        }
        return null;
    }

    public static void loginOut(){
        SharedPreferencesUtils.saveObject(Library.get(), KEY_CURRENT_USER, null);
    }

    public static boolean isLogin() {
        return getCurrentUser() != null;
    }
    public static void saveUser(UserModel model) {
        SharedPreferencesUtils.saveObject(Library.get(), KEY_CURRENT_USER, model);
    }
    public static void saveOtherUser(UserModel model) {
        SharedPreferencesUtils.saveObject(Library.get(), model.getUsername(), model);
    }

    public static UserModel getUser(String username) {
        Object o = SharedPreferencesUtils.readObject(Library.get(), username);
        if (o != null)
            return (UserModel) o;
        return null;
    }
}
