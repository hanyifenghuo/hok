package in.hok.good.utils.clog;

import android.util.Log;

public class CLog
{
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    private static int sLevel = 0;

    public static void setLogLevel(int level)
    {
      sLevel = level;
    }

    public static void v(String tag, String msg)
    {
      if (sLevel > 0) {
        return;
      }
      Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable throwable)
    {
      if (sLevel > 0) {
        return;
      }
      Log.v(tag, msg, throwable);
    }

    public static void v(String tag, String msg, Object... args)
    {
      if (sLevel > 0) {
        return;
      }
      if (args.length > 0) {
        msg = String.format(msg, args);
      }
      Log.v(tag, msg);
    }

    public static void d(String tag, String msg)
    {
      if (sLevel > 1) {
        return;
      }
      Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Object... args)
    {
      if (sLevel > 1) {
        return;
      }
      if (args.length > 0) {
        msg = String.format(msg, args);
      }
      Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable throwable)
    {
      if (sLevel > 1) {
        return;
      }
      Log.d(tag, msg, throwable);
    }

    public static void i(String tag, String msg)
    {
      if (sLevel > 2) {
        return;
      }
      Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Object... args)
    {
      if (sLevel > 2) {
        return;
      }
      if (args.length > 0) {
        msg = String.format(msg, args);
      }
      Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable throwable)
    {
      if (sLevel > 2) {
        return;
      }
      Log.i(tag, msg, throwable);
    }

    public static void w(String tag, String msg)
    {
      if (sLevel > 3) {
        return;
      }
      Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Object[] args)
    {
      if (sLevel > 3) {
        return;
      }
      if (args.length > 0) {
        msg = String.format(msg, args);
      }
      Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable throwable)
    {
      if (sLevel > 3) {
        return;
      }
      Log.w(tag, msg, throwable);
    }

    public static void e(String tag, String msg)
    {
      if (sLevel > 4) {
        return;
      }
      Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Object[] args)
    {
      if (sLevel > 4) {
        return;
      }
      if (args.length > 0) {
        msg = String.format(msg, args);
      }
      Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable throwable)
    {
      if (sLevel > 4) {
        return;
      }
      Log.e(tag, msg, throwable);
    }

    public static void f(String tag, String msg)
    {
      if (sLevel > 5) {
        return;
      }
      Log.wtf(tag, msg);
    }

    public static void f(String tag, String msg, Object[] args)
    {
      if (sLevel > 5) {
        return;
      }
      if (args.length > 0) {
        msg = String.format(msg, args);
      }
      Log.wtf(tag, msg);
    }

    public static void f(String tag, String msg, Throwable throwable)
    {
      if (sLevel > 5) {
        return;
      }
      Log.wtf(tag, msg, throwable);
    }
}
