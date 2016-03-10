package in.hok.good.app;

import in.hok.good.utils.LogManager;
import in.hok.good.utils.clog.CLog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public abstract class CubeFragmentActivity extends FragmentActivity {

    private final static String LOG_TAG = "cube-fragment";

    public static boolean DEBUG = true;
    protected CubeFragment currentFragment;
    private boolean mCloseWarned;
    public boolean isBackOtherAsk=false;//返回监听，是否还有其他先行动作
    /**
     * return the string id of close warning
     * <p/>
     * return value which lower than 1 will exit instantly when press back key
     *
     * @return
     */
    protected abstract String getCloseWarning();

    protected abstract int getFragmentContainerId();
    
    /**
     * 返回监听，其他先行动作，true为继续执行返回动作，false截取返回
     * @return
     */
    public abstract boolean doOtherBackThing();
    

    public void pushFragmentToBackStack(Class<?> cls, Object data) {
        FragmentParam param = new FragmentParam();
        param.cls = cls;
        param.data = data;
        goToThisFragment(param);
    }

    protected String getFragmentTag(FragmentParam param) {
        StringBuilder sb = new StringBuilder(param.cls.toString());
        return sb.toString();
    }

    private void goToThisFragment(FragmentParam param) {
        int containerId = getFragmentContainerId();
        Class<?> cls = param.cls;
        if (cls == null) {
            return;
        }
        try {
            String fragmentTag = getFragmentTag(param);
            FragmentManager fm = getSupportFragmentManager();
            if (DEBUG) {
                CLog.d(LOG_TAG, "before operate, stack entry count: %s", fm.getBackStackEntryCount());
            }
            CubeFragment fragment = (CubeFragment) fm.findFragmentByTag(fragmentTag);
            if (fragment == null) {
                fragment = (CubeFragment) cls.newInstance();
            }
            if (currentFragment != null && currentFragment != fragment) {
                currentFragment.onLeave();
            }
            fragment.onEnter(param.data);

            FragmentTransaction ft = fm.beginTransaction();
            if (fragment.isAdded()) {
                if (DEBUG) {
                    CLog.d(LOG_TAG, "%s has been added, will be shown again.", fragmentTag);
                }
                ft.show(fragment);
            } else {
                if (DEBUG) {
                    CLog.d(LOG_TAG, "%s is added.", fragmentTag);
                }
                ft.add(containerId, fragment, fragmentTag);
                
            }
            currentFragment = fragment;

            ft.addToBackStack(fragmentTag);
            ft.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mCloseWarned = false;
    }

    public void goToFragment(Class<?> cls, Object data) {
        if (cls == null) {
            return;
        }
        CubeFragment fragment = (CubeFragment) getSupportFragmentManager().findFragmentByTag(cls.toString());
        if (fragment != null) {
            currentFragment = fragment;
            fragment.onBackWithData(data);
        }
        getSupportFragmentManager().popBackStackImmediate(cls.toString(), 0);
    }

    public void popTopFragment(Object data) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        if (tryToUpdateCurrentAfterPop() && currentFragment != null) {
            currentFragment.onBackWithData(data);
        }
    }

    public void popToRoot(Object data) {
        FragmentManager fm = getSupportFragmentManager();
        while (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
        }
        popTopFragment(data);
    }

    /**
     * process the return back logic
     * return true if back pressed event has been processed and should stay in current view
     *
     * @return
     */
    protected boolean processBackPressed() {
        return false;
    }

    /**
     * process back pressed
     */
    
    @Override
    public void onBackPressed() {

        // process back for fragment
        if (processBackPressed()) {
            return;
        }

        // process back for fragment
        boolean enableBackPressed = true;
        if (currentFragment != null) {
            enableBackPressed = !currentFragment.processBackPressed();
        }
        if (enableBackPressed) {
            if(isBackOtherAsk)
            {
                //如果有其他先行动作
                if(doOtherBackThing())
                {
                    //执行完先行动作，根据需要，判断是否再返回
                    int cnt = getSupportFragmentManager().getBackStackEntryCount();
                    if (cnt <= 1 && isTaskRoot()) {
                        String closeWarningHint = getCloseWarning();
                        if (!mCloseWarned && !TextUtils.isEmpty(closeWarningHint)) {
                            Toast toast = Toast.makeText(this, closeWarningHint, Toast.LENGTH_SHORT);
                            toast.show();
                            mCloseWarned = true;
                        } else {
                            doReturnBack();
                        }
                    } else {
                        mCloseWarned = false;
                        doReturnBack();
                    } 
                 
                }
            }
            else
            {
                int cnt = getSupportFragmentManager().getBackStackEntryCount();
                if (cnt <= 1 && isTaskRoot()) {
                    String closeWarningHint = getCloseWarning();
                    if (!mCloseWarned && !TextUtils.isEmpty(closeWarningHint)) {
                        Toast toast = Toast.makeText(this, closeWarningHint, Toast.LENGTH_SHORT);
                        toast.show();
                        mCloseWarned = true;
                    } else {
                        doReturnBack();
                    }
                } else {
                    mCloseWarned = false;
                    doReturnBack();
                } 
            }
          
        }
    }

    /**
     * 返回前是否需要询问动作
     * @param isask
     */
    public void setisBackOtherAsk(boolean isask)
    {
        this.isBackOtherAsk=isask;
    }
    
    private boolean tryToUpdateCurrentAfterPop() {
        FragmentManager fm = getSupportFragmentManager();
        int cnt = fm.getBackStackEntryCount();
        if (cnt > 0) {
            String name = fm.getBackStackEntryAt(cnt - 1).getName();
            Fragment fragment = fm.findFragmentByTag(name);
            if (fragment != null && fragment instanceof CubeFragment) {
                currentFragment = (CubeFragment) fragment;
            }
            return true;
        }
        return false;
    }

    protected void doReturnBack() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStackImmediate();
            if (tryToUpdateCurrentAfterPop() && currentFragment != null) {
                currentFragment.onBack();
            }
        }
    }

    public void hideKeyboardForCurrentFocus() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showKeyboardAtView(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    protected void exitFullScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }
}
