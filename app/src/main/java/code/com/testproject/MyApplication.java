package code.com.testproject;

import android.app.Application;

import com.hok.expection.core.Recovery;

/**
 * Created by thinkpad on 2017/1/18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .callback(new MyCrashCallback(this))
                .silent(true, Recovery.SilentMode.STOP_SELF )
                .init(this);
    }
}
