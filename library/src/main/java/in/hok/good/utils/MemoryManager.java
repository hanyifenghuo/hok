package in.hok.good.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public final class MemoryManager {
	 public static BitmapFactory.Options createJustDecodeBoundsOptions()
	  {
	    BitmapFactory.Options returnOptions = new BitmapFactory.Options();
	    returnOptions.inJustDecodeBounds = true;
	    return returnOptions;
	  }

	  public static BitmapFactory.Options createSampleSizeOptions(BitmapFactory.Options justBoundsOptions, int maxWidth, int maxHeight)
	  {
	    int srcWidth = justBoundsOptions.outWidth;
	    int srcHeight = justBoundsOptions.outHeight;
	    int widthScale = (int)Math.ceil(srcWidth / maxWidth);
	    int heightScale = (int)Math.ceil(srcHeight / maxHeight);
	    int inSampleSize = Math.max(widthScale, heightScale);
	    return createSampleSizeOptions(inSampleSize);
	  }

	  public static BitmapFactory.Options createSampleSizeOptions(int inSampleSize)
	  {
	    BitmapFactory.Options returnOptions = new BitmapFactory.Options();
	    returnOptions.inDither = false;
	    returnOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
	    returnOptions.inSampleSize = inSampleSize;
	    return returnOptions;
	  }

	  public static boolean isLowMemory(Context context)
	  {
	    ActivityManager actMgr = (ActivityManager)context.getSystemService("activity");
	    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
	    actMgr.getMemoryInfo(memoryInfo);
	    LogManager.logI(MemoryManager.class, "Avail Mem=" + (memoryInfo.availMem >> 20) + "M");
	    LogManager.logI(MemoryManager.class, "Threshold=" + (memoryInfo.threshold >> 20) + "M");
	    LogManager.logI(MemoryManager.class, "Is Low Mem=" + memoryInfo.lowMemory);
	    return memoryInfo.lowMemory;
	  }
}
