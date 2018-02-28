package com.android.library.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityChatBinding;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * author: Rea.X
 * date: 2018/1/10.
 */

public class ChatActivity extends UIActivity<ActivityChatBinding> {
    public static void chat(Context appCompatActivity, String chatUserName) {
        Intent intent = new Intent(appCompatActivity, ChatActivity.class);
        intent.putExtra("username", chatUserName);
        appCompatActivity.startActivity(intent);
    }


    private EaseChatFragment chatFragment;
    private String toChatUsername;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void init() {
        toChatUsername = getIntent().getStringExtra("username");


        chatFragment = new EaseChatFragment();


        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, toChatUsername);
        //set arguments
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        // enter to chat activity when click notification bar, here make sure only one chat activiy
        String username = intent.getStringExtra("username");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }
}
