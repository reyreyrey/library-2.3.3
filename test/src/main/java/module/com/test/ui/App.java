package module.com.test.ui;

import android.app.Application;

import com.android.library.Library;

import module.com.test.BuildConfig;

/**
 * author: Rea.X
 * date: 2018/1/10.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Library.init(this, BuildConfig.DEBUG);
    }
}
