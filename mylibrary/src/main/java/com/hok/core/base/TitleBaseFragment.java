package com.hok.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hok.R;
import com.hok.core.HokFragment;

/**
 * Created by thinkpad on 2016/8/29.
 */
public abstract class TitleBaseFragment extends HokFragment {
    protected TitleHeaderBar mTitleHeaderBar;
    protected LinearLayout mContentContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(getFrameLayoutId(), null);
        LinearLayout contentContainer = (LinearLayout) view.findViewById(R.id.cube_mints_content_frame_content);

        mTitleHeaderBar = (TitleHeaderBar) view.findViewById(R.id.cube_mints_content_frame_title_header);
        if (enableDefaultBack()) {
            mTitleHeaderBar.setLeftOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            mTitleHeaderBar.getLeftViewContainer().setVisibility(View.INVISIBLE);
        }

        mContentContainer = contentContainer;
        View contentView = createView(inflater, view, savedInstanceState);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        contentContainer.addView(contentView);
        return view;
    }
    //titleBaseFragment用到的
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected int getFrameLayoutId() {
        return R.layout.base_content_frame_with_title_header;
    }

    /**
     * make it looks like Activity
     */
    private void onBackPressed() {
        getContext().onBackPressed();
    }

    protected boolean enableDefaultBack() {

        return true;
    }

    protected void setHeaderTitle(int id) {
        mTitleHeaderBar.getTitleTextView().setText(id);
    }

    protected void setHeaderTitle(String title) {
        mTitleHeaderBar.getTitleTextView().setText(title);
    }

    public TitleHeaderBar getTitleHeaderBar() {
        return mTitleHeaderBar;
    }

}
