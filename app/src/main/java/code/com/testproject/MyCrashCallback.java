package code.com.testproject;

import android.content.Context;
import android.util.Log;

import com.hok.expection.callback.RecoveryCallback;

import java.util.Hashtable;

/**
 * 崩溃回调
 * Created by thinkpad on 2016/11/29.
 */

public class MyCrashCallback implements RecoveryCallback {
    private Context mContext;
    private StringBuilder sb=new StringBuilder();
    public MyCrashCallback(Context mContext)
    {
        this.mContext=mContext;
    }
    @Override
    public void stackTrace(String stackTrace) {
//        sb.append("stackTrace："+stackTrace);
    }

    @Override
    public void cause(String cause) {
        sb.append("cause："+cause);
    }

    @Override
    public void exception(String throwExceptionType, String throwClassName, String throwMethodName, int throwLineNumber) {
        sb.append("\nthrowExceptionType："+throwExceptionType);
        sb.append("\nthrowClassName："+throwClassName);
        sb.append("\nthrowMethodName："+throwMethodName);
        sb.append("\nthrowLineNumber："+throwLineNumber);
    }

    @Override
    public void throwable(Throwable throwable) {
        Log.e("1上传错误信息","1111");
//        try {
//            String bean = dumpPhoneInfo(throwable);
//            //导出异常信息到SD卡中
//            uploadExceptionToServer(bean);
//            //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
//        } catch (Exception e) {
////            LogManager.logE(CrashHandler.class, "崩溃中的崩溃" +e.toString() );
////            e.printStackTrace();
//
//        }
    }

    private String  dumpPhoneInfo(Throwable ex) throws Exception {
//        PhoneBean bean = new PhoneBean();
//        bean.initData(mContext);
//        bean.setmStrException(sb.toString());
        return sb.toString();
    }
    private void uploadExceptionToServer(String aa) throws Exception {
        Log.e("上传错误信息","2222");
//        Hashtable<String, String> map = new Hashtable<>();
//        map.put("Account", StringUtil.getNotNUll(bean.getAccount()));
//        map.put("AppVerisonCode", bean.getAppVerisonCode());
//        map.put("AppVersionName", bean.getAppVersionName());
//        map.put("CpuModel", bean.getCpuModel());
//        map.put("IMEI", bean.getIMEI());
//        map.put("IMSI", StringUtil.getNotNUll(bean.getIMSI()));
//        map.put("PhoneBrand", bean.getPhoneBrand());
//        map.put("PhoneModel", bean.getPhoneModel());
//        map.put("PhoneNub", StringUtil.getNotNUll(bean.getPhoneNub()));
//        map.put("PhoneType", bean.getPhoneType());
//        map.put("VersionRelease", bean.getVersionRelease());
//        map.put("VersionSdk", bean.getVersionSdk());
//        map.put("WidthAndHeight", StringUtil.getNotNUll(bean.getWidthAndHeight()));
//        map.put("mStrException", StringUtil.getNotNUll(bean.getmStrException()));
//        LogManager.logE(CrashHandler.class, "bug测试2");
//
//        new HttpUtil.Builder("index/catchError")
//                .Params(map)
//                .post();

    }
}
