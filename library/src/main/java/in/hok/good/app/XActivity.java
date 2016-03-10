package in.hok.good.app;

import in.hok.good.app.lifecycle.IComponentContainer;
import in.hok.good.app.lifecycle.LifeCycleComponent;
import in.hok.good.app.lifecycle.LifeCycleComponentManager;
import in.hok.good.utils.Debug;
import android.os.Bundle;
import android.util.Log;

/**
 * 1. manager the components when move from a lifetime to another
 *
 * @author http://www.liaohuqiu.net
 */
public abstract class XActivity extends CubeFragmentActivity implements IComponentContainer {

    private LifeCycleComponentManager mComponentContainer = new LifeCycleComponentManager();

    private static final boolean DEBUG = Debug.DEBUG_LIFE_CYCLE;

    @Override
    protected void onRestart() {
        super.onStart();
        mComponentContainer.onBecomesVisibleFromTotallyInvisible();
        if (DEBUG) {
            showStatus("onRestart");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mComponentContainer.onBecomesPartiallyInvisible();
        if (DEBUG) {
            showStatus("onPause");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mComponentContainer.onBecomesVisibleFromPartiallyInvisible();
        if (DEBUG) {
            showStatus("onResume");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            showStatus("onCreate");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mComponentContainer.onBecomesTotallyInvisible();
        if (DEBUG) {
            showStatus("onStop");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mComponentContainer.onDestroy();
        if (DEBUG) {
            showStatus("onDestroy");
        }
    }

    @Override
    public void addComponent(LifeCycleComponent component) {
        mComponentContainer.addComponent(component);
    }

    private void showStatus(String status) {
        final String[] className = ((Object) this).getClass().getName().split("\\.");
        Log.d("cube-lifecycle", String.format("%s %s", className[className.length - 1], status));
    }
}
