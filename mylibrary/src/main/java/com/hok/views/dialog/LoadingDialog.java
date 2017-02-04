package com.hok.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hok.R;
import com.wang.avi.AVLoadingIndicatorView;

public class LoadingDialog
{
    /**
     *"BallPulseIndicator",
     "BallGridPulseIndicator",
     "BallClipRotateIndicator",
     "BallClipRotatePulseIndicator",
     "SquareSpinIndicator",
     "BallClipRotateMultipleIndicator",
     "BallPulseRiseIndicator",
     "BallRotateIndicator",
     "CubeTransitionIndicator",
     "BallZigZagIndicator",
     "BallZigZagDeflectIndicator",
     "BallTrianglePathIndicator",
     "BallScaleIndicator",
     "LineScaleIndicator",
     "LineScalePartyIndicator",
     "BallScaleMultipleIndicator",
     "BallPulseSyncIndicator",
     "BallBeatIndicator",
     "LineScalePulseOutIndicator",
     "LineScalePulseOutRapidIndicator",
     "BallScaleRippleIndicator",
     "BallScaleRippleMultipleIndicator",
     "BallSpinFadeLoaderIndicator",
     "LineSpinFadeLoaderIndicator",
     "TriangleSkewSpinIndicator",
     "PacmanIndicator",
     "BallGridBeatIndicator",
     "SemiCircleSpinIndicator",
     "com.wang.avi.sample.MyCustomIndicator"
     */
    private static String type="BallClipRotateIndicator";
    /**
     * 得到自定义的progressDialog
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {

              LayoutInflater inflater = LayoutInflater.from(context);
              View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
              LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
              // main.xml中的ImageView
              TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
              AVLoadingIndicatorView avi= (AVLoadingIndicatorView) v.findViewById(R.id.avi);
              avi.setIndicator(type);

              if (msg!=null)
              tipTextView.setText(msg);// 设置加载信息

              Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

              loadingDialog.setCancelable(true);// 不可以用“返回键”取消
              loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                                  LinearLayout.LayoutParams.FILL_PARENT,
                                  LinearLayout.LayoutParams.FILL_PARENT));// 设置布局

              return loadingDialog;

    }

    public static Dialog createLoadingDialog(Context context, String msg,String type) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        AVLoadingIndicatorView avi= (AVLoadingIndicatorView) v.findViewById(R.id.avi);
        avi.setIndicator(type);

        if (msg!=null)
            tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局

        return loadingDialog;

    }
}
