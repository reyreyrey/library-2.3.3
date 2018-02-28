package com.android.library.utils;

import com.hyphenate.chat.EMClient;

/**
 * Created by wiki on 11/01/2018.
 */

public class ChatUtils {

    public static void loadAll(){
        EMClient.getInstance().chatManager().loadAllConversations();
        EMClient.getInstance().groupManager().loadAllGroups();
    }

    public static boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore();
    }


}
