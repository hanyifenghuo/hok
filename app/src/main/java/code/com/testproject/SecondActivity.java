package code.com.testproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by thinkpad on 2017/1/18.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i=1/0;
        setContentView(R.layout.activity_sectond);
    }
}
