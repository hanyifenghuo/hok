package com.hok.core.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hok.R;
import com.hok.core.HokFragment;
import com.hok.views.statusView.StatusViewGroup;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by 顾雪华 on 2016/10/28.
 * Emial：guxuehua@51zhaoyou.com
 */
public abstract class TitlePtrFragment  extends HokFragment{
    protected TitleHeaderBar mTitleHeaderBar;
    protected LinearLayout mContentContainer;
    public PtrFrameLayout mPtrFrameLayout;
    private LinearLayout llpreLazy;
    public StatusViewGroup mStatusLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(getFrameLayoutId(), null);
        LinearLayout contentContainer = (LinearLayout) view.findViewById(R.id.cube_mints_content_frame_content);
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_frame);
        mTitleHeaderBar = (TitleHeaderBar) view.findViewById(R.id.cube_mints_content_frame_title_header);
        llpreLazy = (LinearLayout) view.findViewById(R.id.llpreLazy);
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
        initPtr();
        mStatusLayout= (StatusViewGroup) view.findViewById(R.id.status);
        mStatusLayout.setGlobalOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });
        View contentView = createView(inflater, view, savedInstanceState);
//        View contentView = inflater.inflate(getChildrenLayoutId(),null);
        contentView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        contentContainer.addView(contentView);
        return view;
    }
    //titleBaseFragment用到的
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected int getFrameLayoutId() {
        return R.layout.base_content_frame_with_title_header_pullfresh;
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
    public void refresh()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this);
        ft.attach(this);
        ft.commitAllowingStateLoss();
    }
    public void initPtr()
    {
        mPtrFrameLayout.setPullToRefresh(false);
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);
        mPtrFrameLayout.disableWhenHorizontalMove(false);

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                onPtrRefresh();
            }

        });
    }
    public void onPtrRefresh()
    {

    }
}
