package module.com.test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.library.manager.UIManager;
import com.android.library.ui.AddPostActivity;
import com.android.library.ui.LoginActivity;
import com.android.library.ui.MineActivity;
import com.android.library.ui.OtherUserActivity;
import com.android.library.ui.PostListActivity;
import com.android.library.ui.RegisterActivity;
import com.android.library.ui.SearchActivity;
import com.android.library.utils.FileCacheUtils;

import module.com.test.R;
import ticketsystem.ui.LibraryMainActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv_cache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_cache = (TextView) findViewById(R.id.tv_cache);
        try {
            tv_cache.setText(FileCacheUtils.getCacheSize(getExternalCacheDir()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPost(View v) {
        startActivity(new Intent(this, AddPostActivity.class));
    }

    public void postList(View view) {
        startActivity(new Intent(this, PostListActivity.class));
    }

    public void reg(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void mine(View view) {
        startActivity(new Intent(this, MineActivity.class));
    }

    public void search(View v) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void other(View v) {
        OtherUserActivity.seeOtherUser(this, "d9cf9e8c-99d8-de5c-2ab2-10f77cdc8849", "kk");
    }

    public void notify(View v) {
        startActivity(new Intent(this, LibraryMainActivity.class));
    }

    public void checkUpdate(View v){
        UIManager.checkUpdate(this);
    }
    public void setting(View v){
        UIManager.setting(this);
    }
}
