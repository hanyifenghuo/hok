package com.hok.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hok.R;
import com.hok.core.HokFragmentActivity;

/**
 * Created by thinkpad on 2016/8/29.
 */
public class TitleBaseActivity extends HokFragmentActivity {
    protected TitleHeaderBar mTitleHeaderBar;
    protected LinearLayout mContentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    protected int getFrameLayoutId() {
        return R.layout.base_content_frame_with_title_header;
    }

    protected TitleHeaderBar getTitleHeaderBar() {
        return (TitleHeaderBar) findViewById(R.id.cube_mints_content_frame_title_header);
    }

    protected LinearLayout getContentContainer() {
        return (LinearLayout) findViewById(R.id.cube_mints_content_frame_content);
    }

    protected void initViews() {
        super.setContentView(getFrameLayoutId());

        mTitleHeaderBar = getTitleHeaderBar();
        mContentContainer = getContentContainer();

        if (enableDefaultBack()) {
            mTitleHeaderBar.setLeftOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!processBackPressed()) {
                        doReturnBack();
                    }
                }
            });
        } else {
            mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
        }
    }

    protected boolean enableDefaultBack() {
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContentContainer.addView(view);
    }

    public void setContentViewSupper(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected void setHeaderTitle(int id) {
        mTitleHeaderBar.getTitleTextView().setText(id);
    }

    protected void setHeaderTitle(String title) {
        mTitleHeaderBar.setTitle(title);
    }

    @Override
    protected String getCloseWarning() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }
}
