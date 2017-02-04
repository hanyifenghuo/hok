package com.hok.core;

/**
 * Created by thinkpad on 2016/8/29.
 */
public interface IHokFragment {
    void onEnter(Object data);

    void onLeave();

    void onBack();

    void onBackWithData(Object data);

    /**
     * process the return back logic
     * return true if back pressed event has been processed and should stay in current view
     *
     * @return
     */
    boolean processBackPressed();
}
