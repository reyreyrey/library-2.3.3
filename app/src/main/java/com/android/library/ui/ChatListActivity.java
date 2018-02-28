package com.android.library.ui;

import android.content.Context;
import android.content.Intent;

import com.android.library.R;
import com.android.library.base.UIActivity;
import com.android.library.databinding.ActivityChatListBinding;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.ui.EaseConversationListFragment;


/**
 * Created by wiki on 11/01/2018.
 */

public class ChatListActivity extends UIActivity<ActivityChatListBinding> {
    public static void chatList(Context context) {
        Intent intent = new Intent(context, ChatListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_list;
    }

    @Override
    protected void init() {
        EaseConversationListFragment conversationListFragment = new EaseConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {
                String username = conversation.conversationId();
                ChatActivity.chat(ChatListActivity.this, username);
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.container, conversationListFragment).commit();
    }
}
