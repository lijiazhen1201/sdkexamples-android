package com.easemob.chatuidemo.ui;

import android.content.Intent;
import android.os.Bundle;

import com.easemob.chatuidemo.R;

/**
 * 聊天页面，需要fragment的使用{@link #EMChatFragment}
 *
 */
public class ChatActivity extends BaseActivity{
    public static ChatActivity activityInstance;
    private ChatFragment chatFragment;
    String toChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        
        toChatUsername = getIntent().getExtras().getString("userId");
        chatFragment = new ChatFragment();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
        
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        // 点击notification bar进入聊天页面，保证只有一个聊天页面
        String username = intent.getStringExtra("userId");
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
    
    public String getToChatUsername(){
        return toChatUsername;
    }
}
