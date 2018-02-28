package com.android.library.utils;


/**
 * author: Rea.X
 * date: 2017/5/16.
 */

public class Cons {




    /**
     * Prefrences
     */
    public static class PrefrencesKeys {
        public static final String VERSION = "version_app";
    }


    /**
     * 每一页的展示数量
     */
    public static final int PAGE_COUNT = 20;


    public static final String BASE_URL = "http://59.110.228.73/mj/interface/";

    public static final String CONTROL_URL = BASE_URL + "control.php?appid=";
    public static final String USERINFO_URL = BASE_URL + "userinfo.php?other_user_name=";
    public static final String USERINFO_BY_ID_URL = BASE_URL + "userinfoById.php?other_user_id=";
    public static final String POST_LIST_URL = BASE_URL + "postList.php";
    public static final String POST_DETAIL_URL = BASE_URL + "postDetail.php?id=";
    public static final String REG_URL = BASE_URL + "reg.php";
    public static final String LOGIN_URL = BASE_URL + "login.php";

    public static final String POST_SAVE_URL = BASE_URL + "postSave.php";
    public static final String POST_STAR_URL = BASE_URL + "postStar.php";

    public static final String POST_COMMENT_URL = BASE_URL + "postComment.php?post_id=";
    public static final String ADD_COMMENT_URL = BASE_URL + "addComment.php";
    public static final String ADD_POST_URL = BASE_URL + "addPost.php";
    public static final String USER_ATT_URL = BASE_URL + "userAttention.php";
    public static final String MY_ATTENTION_URL = BASE_URL + "myAttention.php?userid=";
    public static final String MY_FANS_URL = BASE_URL + "MyFans.php?userid=";
    public static final String MY_SAVE_URL = BASE_URL + "mySav.php?user_id=";
    public static final String MY_POST_URL = BASE_URL + "myPost.php?user_id=";
    public static final String USER_FAV_POST_URL = BASE_URL + "MyFavPost.php?user_id=";
    public static final String EDIT_USER_INFO = BASE_URL + "editUser.php";
    public static final String SEARCH_URL= BASE_URL + "searchUser.php?key=";
    public static final String SYSTEM_NOTIFY_URL = BASE_URL + "systemNotify.php";
    public static final String SYSTEM_NOTIFY_DETAIL_URL = BASE_URL + "systemNotifyDetail.php?notify_id=";
}
