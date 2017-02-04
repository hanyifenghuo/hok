package com.hok.core.base;

import android.view.View;

/**
 * 在下拉的时候，需要的操作
 * Created by 顾雪华 on 2016/10/28.
 * Emial：guxuehua@51zhaoyou.com
 */
public interface IPtrInFrag {
    void pullData();//下拉
    void pageData();//分页获取数据
    void prePageView();//分页前的布局
    void donePageView(Exception paramException, View.OnClickListener onClickListener);//分页后的布局
}
