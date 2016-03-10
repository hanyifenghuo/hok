package in.hok.good.imageloaderoption;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class ExtraSourceImageDownloader extends BaseImageDownloader
{
  public static final String SCHEME_PACKAGE = "package";

  public ExtraSourceImageDownloader(Context context)
  {
    super(context);
  }

  public ExtraSourceImageDownloader(Context context, int connectTimeout, int readTimeout) {
    super(context, connectTimeout, readTimeout);
  }

  protected InputStream getStreamFromOtherSource(String imageUri, Object extra) throws IOException
  {
    if (imageUri != null) {
      String packagePrefix = "package://";
      if (imageUri.toLowerCase().startsWith(packagePrefix)) {
        String cropUri = imageUri.substring(packagePrefix.length());
        ApplicationInfo appInfo = getInstalledApplication(this.context, cropUri);
        if (appInfo == null)
          throw new IOException("Package named '" + cropUri + "' is not installed.");
        BitmapDrawable iconDrawable = (BitmapDrawable)appInfo.loadIcon(this.context.getPackageManager());
        Bitmap bitmap = iconDrawable.getBitmap();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, os);
        return new ByteArrayInputStream(os.toByteArray());
      }
    }
    return super.getStreamFromOtherSource(imageUri, extra);
  }
  
  public static ApplicationInfo getInstalledApplication(Context context, String packageName)
  {
    try
    {
      return context.getPackageManager().getApplicationInfo(packageName, 0);
    } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
    }
    return null;
  }
}
