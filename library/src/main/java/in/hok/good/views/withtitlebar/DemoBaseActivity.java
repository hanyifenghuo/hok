package in.hok.good.views.withtitlebar;

import in.hok.good.R;
import in.hok.good.app.XActivity;


public abstract class DemoBaseActivity extends XActivity {

    @Override
    protected String getCloseWarning() {
        return getString(R.string.cube_mints_exit_tip);
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }
}