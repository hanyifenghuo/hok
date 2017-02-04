package com.hok.views.statusView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hok.R;
import com.hok.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vlonjat Gashi (vlonjatg)
 */
public class StatusViewGroup extends RelativeLayout {

    private static final String TAG_LOADING = "ProgressActivity.TAG_LOADING";
    private static final String TAG_EMPTY = "ProgressActivity.TAG_EMPTY";
    private static final String TAG_ERROR = "ProgressActivity.TAG_ERROR";
    private static final String TAG_NOINTERNET = "ProgressActivity.TAG_NOINTERNET";

    final String CONTENT = "type_content";
    final String LOADING = "type_loading";
    final String EMPTY = "type_empty";
    final String ERROR = "type_error";
    final String NOINTERNET = "type_no_internet";
    TextView tv_title;
    TextView exception_message;
    Button exception_button;
    ImageView iv;

    LayoutInflater inflater;
    View view;
    LayoutParams layoutParams;
    Drawable currentBackground;

    List<View> contentViews = new ArrayList<>();

    RelativeLayout loadingStateRelativeLayout;
    RelativeLayout emptyStateRelativeLayout;
    RelativeLayout errorStateRelativeLayout;
    RelativeLayout noInternetStateRelativeLayout;

    private String state = CONTENT;

    /**
     * 全局通用的点击事件
     */
    private OnClickListener mOnclickListener;
    public void setGlobalOnclickListener(OnClickListener mOnclickListener) {
        this.mOnclickListener = mOnclickListener;
    }

    public StatusViewGroup(Context context) {
        super(context);
    }

    public StatusViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StatusViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentBackground = this.getBackground();
    }

    @Override
    public void addView(@NonNull View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (child.getTag() == null || (!child.getTag().equals(TAG_LOADING)
                && !child.getTag().equals(TAG_EMPTY) && !child.getTag().equals(TAG_ERROR)
                && !child.getTag().equals(TAG_NOINTERNET))) {

            contentViews.add(child);
        }
    }

    /**
     * Hide all other states and show content
     */
    public void showContent() {
        switchState(CONTENT, null, null, null, null, null, Collections.<Integer>emptyList());
    }

    /**
     * Hide all other states and show content
     *
     * @param skipIds Ids of views not to show
     */
    public void showContent(List<Integer> skipIds) {
        switchState(CONTENT, null, null, null, null, null, skipIds);
    }


    public void showLoading() {
        switchState(LOADING, null, null, null, null, null, Collections.<Integer>emptyList());
    }


    public void showLoading(List<Integer> skipIds) {
        switchState(LOADING, null, null, null, null, null, skipIds);
    }

    public void showEmpty(){
        switchState(EMPTY,null,null,null,null,null, Collections.<Integer>emptyList());
    }
    public void showEmpty(Drawable emptyImageDrawable, String emptyTextTitle, String emptyTextContent) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, Collections.<Integer>emptyList());
    }

    public void showEmpty(Drawable emptyImageDrawable, String emptyTextTitle, String emptyTextContent, List<Integer> skipIds) {
        switchState(EMPTY, emptyImageDrawable, emptyTextTitle, emptyTextContent, null, null, skipIds);
    }

    public void showError(){
        switchState(ERROR,null,null,null,null,null, Collections.<Integer>emptyList());
    }
    public void showError(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, Collections.<Integer>emptyList());
    }


    public void showError(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener, List<Integer> skipIds) {
        switchState(ERROR, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds);
    }

    public void showNoInternet(){
        switchState(NOINTERNET,null,null,null,null,null, Collections.<Integer>emptyList());
    }
    public void showNoInternet(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener) {
        switchState(NOINTERNET, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, Collections.<Integer>emptyList());
    }


    public void showNoInternet(Drawable errorImageDrawable, String errorTextTitle, String errorTextContent, String errorButtonText, OnClickListener onClickListener, List<Integer> skipIds) {
        switchState(NOINTERNET, errorImageDrawable, errorTextTitle, errorTextContent, errorButtonText, onClickListener, skipIds);
    }

    public String getState() {
        return state;
    }


    public boolean isContent() {
        return state.equals(CONTENT);
    }


    public boolean isLoading() {
        return state.equals(LOADING);
    }


    public boolean isEmpty() {
        return state.equals(EMPTY);
    }


    public boolean isError() {
        return state.equals(ERROR);
    }

    private void switchState(String state, Drawable drawable, String errorText, String errorTextContent,
                             String errorButtonText, OnClickListener onClickListener, List<Integer> skipIds) {
        this.state = state;

        switch (state) {
            case CONTENT:
                //Hide all state views to display content
                hideLoadingView();
                hideEmptyView();
                hideErrorView();
                hidenoInternetView();
                setContentVisibility(true, skipIds);
                break;
            case LOADING:
                hideEmptyView();
                hideErrorView();
                hidenoInternetView();
                setLoadingView();
                setContentVisibility(true, skipIds);
                break;
            case EMPTY:
                hideLoadingView();
                hideErrorView();
                hidenoInternetView();
                setEmptyView(  drawable,  errorText,  errorTextContent,
                         errorButtonText,  onClickListener);

                setContentVisibility(false, skipIds);
                break;
            case ERROR:
                hideLoadingView();
                hideEmptyView();
                hidenoInternetView();
                setErrorView(drawable,  errorText,  errorTextContent,
                        errorButtonText,  onClickListener);
                setContentVisibility(false, skipIds);
                break;

            case NOINTERNET:
                hideErrorView();
                hideLoadingView();
                hideEmptyView();

                setNoInternetView(drawable,  errorText,  errorTextContent,
                        errorButtonText,  onClickListener);
                setContentVisibility(false, skipIds);
                break;

        }
    }

    private void setLoadingView() {
        if (loadingStateRelativeLayout == null) {
            loadingStateRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.exception_loading_content, null);
            loadingStateRelativeLayout.setTag(TAG_LOADING);

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);

            addView(loadingStateRelativeLayout, layoutParams);
        } else {
            loadingStateRelativeLayout.setVisibility(VISIBLE);
        }

    }

    private void setEmptyView(Drawable drawable, String title, String message, String errorButtonText, OnClickListener onClickListener) {
        if (emptyStateRelativeLayout == null) {
            emptyStateRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.exception_empty, null);
            emptyStateRelativeLayout.setTag(TAG_EMPTY);

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);

            addView(emptyStateRelativeLayout, layoutParams);

             tv_title= (TextView) emptyStateRelativeLayout.findViewById(R.id.exception_title);
             exception_message= (TextView) emptyStateRelativeLayout.findViewById(R.id.exception_message);
            exception_button= (Button) emptyStateRelativeLayout.findViewById(R.id.exception_button);
            iv= (ImageView) emptyStateRelativeLayout.findViewById(R.id.iv);
        } else {
            emptyStateRelativeLayout.setVisibility(VISIBLE);
        }
        setCusmUI(emptyStateRelativeLayout, drawable,  title,  message,  errorButtonText,  onClickListener);
    }

    private void setErrorView(Drawable drawable, String title, String message, String errorButtonText, OnClickListener onClickListener) {
        if (errorStateRelativeLayout == null) {
            errorStateRelativeLayout = (RelativeLayout)  inflater.inflate(R.layout.exception_failure, null);
            errorStateRelativeLayout.setTag(TAG_ERROR);

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);

            addView(errorStateRelativeLayout, layoutParams);

            tv_title= (TextView) errorStateRelativeLayout.findViewById(R.id.exception_title);
            exception_message= (TextView) errorStateRelativeLayout.findViewById(R.id.exception_message);
            exception_button= (Button) errorStateRelativeLayout.findViewById(R.id.exception_button);
            iv= (ImageView) errorStateRelativeLayout.findViewById(R.id.iv);
        } else {
            errorStateRelativeLayout.setVisibility(VISIBLE);
        }
        setCusmUI(errorStateRelativeLayout, drawable,  title,  message,  errorButtonText,  onClickListener);
    }
    private void setNoInternetView(Drawable drawable, String title, String message, String errorButtonText, OnClickListener onClickListener) {
        if (noInternetStateRelativeLayout == null) {
            noInternetStateRelativeLayout = (RelativeLayout)  inflater.inflate(R.layout.exception_no_internet, null);
            noInternetStateRelativeLayout.setTag(TAG_NOINTERNET);

            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(CENTER_IN_PARENT);

            addView(noInternetStateRelativeLayout, layoutParams);
            tv_title= (TextView) noInternetStateRelativeLayout.findViewById(R.id.exception_title);
            exception_message= (TextView) noInternetStateRelativeLayout.findViewById(R.id.exception_message);
            exception_button= (Button) noInternetStateRelativeLayout.findViewById(R.id.exception_button);
            iv= (ImageView) noInternetStateRelativeLayout.findViewById(R.id.iv);
        } else {
            noInternetStateRelativeLayout.setVisibility(VISIBLE);
        }

        setCusmUI(noInternetStateRelativeLayout, drawable,  title,  message,  errorButtonText,  onClickListener);
    }
    private void setContentVisibility(boolean visible, List<Integer> skipIds) {
        if (skipIds!=null)
        for (View v : contentViews) {
            if (!skipIds.contains(v.getId())) {
                v.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        }
    }

    private void hideLoadingView() {
        if (loadingStateRelativeLayout != null) {
            loadingStateRelativeLayout.setVisibility(GONE);
        }
    }

    private void hideEmptyView() {
        if (emptyStateRelativeLayout != null) {
            emptyStateRelativeLayout.setVisibility(GONE);
        }
    }

    private void hideErrorView() {
        if (errorStateRelativeLayout != null) {
            errorStateRelativeLayout.setVisibility(GONE);
        }
    }
    private void hidenoInternetView() {
        if (noInternetStateRelativeLayout != null) {
            noInternetStateRelativeLayout.setVisibility(GONE);
        }
    }
    public void setCusmUI(View m,Drawable drawable, String title, String message, String buttontext, OnClickListener onClickListener)
    {
        if (!TextUtils.isEmpty(title)&&tv_title!=null)
        {
            tv_title= (TextView) m.findViewById(R.id.exception_title);
            tv_title.setText(title);
        }
        if (!TextUtils.isEmpty(message)&&exception_message!=null)
        {
            exception_message= (TextView) m.findViewById(R.id.exception_message);
            exception_message.setText(message);
        }
        //全局点击事件
        if (mOnclickListener!=null&&exception_button!=null)
            exception_button.setOnClickListener(mOnclickListener);

        if (!(TextUtils.isEmpty(title)&&onClickListener==null)&&exception_button!=null)
        {
            exception_button= (Button) m.findViewById(R.id.exception_button);
            exception_button.setText(StringUtil.getNotNUll(buttontext));
            if (onClickListener!=null)
                exception_button.setOnClickListener(onClickListener);
        }


        if (drawable!=null&&iv!=null)
        {
            iv= (ImageView) m.findViewById(R.id.iv);
            iv.setImageDrawable(drawable);
        }
    }
}