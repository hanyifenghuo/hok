package in.hok.good.imageloaderoption;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public class TransitionBitmapDisplayer
implements BitmapDisplayer
{
private final int defaultDrawableId;
private final int durationMillis;
private final boolean animateFromNetwork;
private final boolean animateFromDisc;
private final boolean animateFromMemory;

public TransitionBitmapDisplayer(int defaultDrawableId, int durationMillis)
{
  this(defaultDrawableId, durationMillis, true, true, true);
}

public TransitionBitmapDisplayer(int defaultDrawableId, int durationMillis, boolean animateFromNetwork, boolean animateFromDisc, boolean animateFromMemory) {
  this.defaultDrawableId = defaultDrawableId;
  this.durationMillis = durationMillis;
  this.animateFromNetwork = animateFromNetwork;
  this.animateFromDisc = animateFromDisc;
  this.animateFromMemory = animateFromMemory;
}

public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom)
{
  if (((this.animateFromNetwork) && (loadedFrom == LoadedFrom.NETWORK)) || 
    ((this.animateFromDisc) && (loadedFrom == LoadedFrom.DISC_CACHE)) || (
    (this.animateFromMemory) && (loadedFrom == LoadedFrom.MEMORY_CACHE))) {
    MyTransitionDrawable transDrawable = new MyTransitionDrawable(imageAware.getWrappedView().getResources().getDrawable(this.defaultDrawableId), new BitmapDrawable(bitmap));
    transDrawable.setCrossFadeEnabled(true);
    imageAware.setImageDrawable(transDrawable);
    transDrawable.startTransition(this.durationMillis);
  } else {
    imageAware.setImageBitmap(bitmap);
  }
}

private static class MyTransitionDrawable extends TransitionDrawable
{
  private Drawable mainDrawable = null;

  public MyTransitionDrawable(Drawable defDrawable, Drawable mainDrawable)
  {
      super(new Drawable[]{defDrawable,mainDrawable});
    this.mainDrawable = mainDrawable;
  }

  public int getIntrinsicWidth()
  {
    return this.mainDrawable.getIntrinsicWidth();
  }

  public int getIntrinsicHeight()
  {
    return this.mainDrawable.getIntrinsicHeight();
  }
}
}