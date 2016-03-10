package in.hok.good.adapterview;

import android.content.Context;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

public abstract class DataHolder
{
    private Object mData = null;
    private DisplayImageOptions[] mOptions = null;
    private int type=0;

    public DataHolder(Object data, DisplayImageOptions[] options)
    {
      this.mData = data;
      this.mOptions = options;
    }

    public abstract View onCreateView(Context paramContext, int paramInt, Object paramObject);

    public abstract void onUpdateView(Context paramContext, int paramInt, View paramView, Object paramObject);

    public int getType()
    {
      return type;
    }
    
    public void setType(int type)
    {
    	this.type=type;
    }
    
    public Object getData()
    {
      return this.mData;
    }

    public DisplayImageOptions[] getDisplayImageOptions()
    {
      return this.mOptions;
    }
}
