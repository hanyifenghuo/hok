package com.hok.utils.logutil;

import android.util.Log;


public final class LogManager
{
  public static final int VERBOSE = 2;
  public static final int DEBUG = 3;
  public static final int INFO = 4;
  public static final int WARN = 5;
  public static final int ERROR = 6;
  public static final int ASSERT = 7;
  public static int LOGGING_LEVEL = 2;

  public static boolean ENABLED_JLOG = false;

  public static boolean debug = false;

  public static void setDebug(boolean debug) {
    LogManager.debug = debug;
  }

  public static void logV(Class<? extends Object> tag, String msg)
  {
    if (2 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      Log.v(tagStr, msg);
      if (ENABLED_JLOG)
        JLog.fine(tagStr.concat(":").concat(msg));
    }
  }

  public static void logV(Class<? extends Object> tag, String msg, Throwable tr)
  {
    if (2 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      if (tr == null)
      {
        Log.v(tagStr, msg);
        if (ENABLED_JLOG)
          JLog.fine(tagStr.concat(":").concat(msg));
      }
      else {
        Log.v(tagStr, msg, tr);
        if (ENABLED_JLOG)
          JLog.fine(tagStr.concat(":").concat(msg), tr);
      }
    }
  }

  public static void logD(Class<? extends Object> tag, String msg)
  {
    if (3 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      Log.d(tagStr, msg);
      if (ENABLED_JLOG)
        JLog.config(tagStr.concat(":").concat(msg));
    }
  }

  public static void logD(Class<? extends Object> tag, String msg, Throwable tr)
  {
    if (3 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      if (tr == null)
      {
        Log.d(tagStr, msg);
        if (ENABLED_JLOG)
          JLog.config(tagStr.concat(":").concat(msg));
      }
      else {
        Log.d(tagStr, msg, tr);
        if (ENABLED_JLOG)
          JLog.config(tagStr.concat(":").concat(msg), tr);
      }
    }
  }

  public static void logI(Class<? extends Object> tag, String msg)
  {
    if (4 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      Log.i(tagStr, msg);
      if (ENABLED_JLOG)
        JLog.info(tagStr.concat(":").concat(msg));
    }
  }

  public static void logI(Class<? extends Object> tag, String msg, Throwable tr)
  {
    if (4 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      if (tr == null)
      {
        Log.i(tagStr, msg);
        if (ENABLED_JLOG)
          JLog.info(tagStr.concat(":").concat(msg));
      }
      else {
        Log.i(tagStr, msg, tr);
        if (ENABLED_JLOG)
          JLog.info(tagStr.concat(":").concat(msg), tr);
      }
    }
  }

  public static void logW(Class<? extends Object> tag, String msg)
  {
    if (5 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      Log.w(tagStr, msg);
      if (ENABLED_JLOG)
        JLog.warning(tagStr.concat(":").concat(msg));
    }
  }

  public static void logW(Class<? extends Object> tag, Throwable tr)
  {
    if (5 >= LOGGING_LEVEL&&!debug)
    {
      String tagStr = tag.getSimpleName();
      if (tr == null)
      {
        Log.w(tagStr, "");
        if (ENABLED_JLOG)
          JLog.warning(tagStr.concat(":").concat(""));
      }
      else {
        Log.w(tagStr, tr);
        if (ENABLED_JLOG)
          JLog.warning(tagStr.concat(":").concat(""), tr);
      }
    }
  }

  public static void logW(Class<? extends Object> tag, String msg, Throwable tr)
  {
    if (5 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      if (tr == null)
      {
        Log.w(tagStr, msg);
        if (ENABLED_JLOG)
          JLog.warning(tagStr.concat(":").concat(msg));
      }
      else {
        Log.w(tagStr, msg, tr);
        if (ENABLED_JLOG)
          JLog.warning(tagStr.concat(":").concat(msg), tr);
      }
    }
  }

  public static void logE(Class<? extends Object> tag, String msg)
  {
    if (6 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      Log.e(tagStr, msg);
      if (ENABLED_JLOG)
        JLog.severe(tagStr.concat(":").concat(msg));
    }
  }

  public static void logE(Class<? extends Object> tag, String msg, Throwable tr)
  {
    if (6 >= LOGGING_LEVEL&&!debug)
    {
      if (msg == null)
        msg = "";
      String tagStr = tag.getSimpleName();
      if (tr == null)
      {
        Log.e(tagStr, msg);
        if (ENABLED_JLOG)
          JLog.severe(tagStr.concat(":").concat(msg));
      }
      else {
        Log.e(tagStr, msg, tr);
        if (ENABLED_JLOG)
          JLog.severe(tagStr.concat(":").concat(msg), tr);
      }
    }
  }
}
