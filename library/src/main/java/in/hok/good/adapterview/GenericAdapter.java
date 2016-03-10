package in.hok.good.adapterview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class GenericAdapter extends BaseAdapter
{
    Context mContext = null;
    private List<DataHolder> mHolders = null;

    private boolean mIsLoopView = false;

    private int mViewTypeCount = 1;

    public GenericAdapter(Context context)
    {
      this(context, 1);
    }

    public GenericAdapter(Context context, int viewTypeCount)
    {
      if (context == null)
        throw new NullPointerException();
      if (viewTypeCount <= 0)
        throw new IllegalArgumentException("viewTypeCount should great than zero.");
      this.mContext = context;
      this.mHolders = new ArrayList();
      this.mViewTypeCount = viewTypeCount;
    }

    public GenericAdapter(Context context, List<DataHolder> holders)
    {
      this(context, holders, 1);
    }

    public GenericAdapter(Context context, List<DataHolder> holders, int viewTypeCount)
    {
      if ((context == null) || (holders == null))
        throw new NullPointerException();
      if (viewTypeCount <= 0)
        throw new IllegalArgumentException("viewTypeCount should great than zero.");
      this.mContext = context;
      this.mHolders = new ArrayList(holders);
      this.mViewTypeCount = viewTypeCount;
    }

    public void addDataHolder(DataHolder holder)
    {
      this.mHolders.add(holder);
      notifyDataSetChanged();
    }

    public void addDataHolder(int location, DataHolder holder)
    {
      if (this.mIsLoopView)
        location = getRealPosition(location);
      this.mHolders.add(location, holder);
      notifyDataSetChanged();
    }

    public void addDataHolders(List<DataHolder> holders)
    {
      this.mHolders.addAll(holders);
      notifyDataSetChanged();
    }

    public void addDataHolders(int location, List<DataHolder> holders)
    {
      if (this.mIsLoopView)
        location = getRealPosition(location);
      this.mHolders.addAll(location, holders);
      notifyDataSetChanged();
    }

    public void removeDataHolder(int location)
    {
      if (this.mIsLoopView)
        location = getRealPosition(location);
      this.mHolders.remove(location);
      notifyDataSetChanged();
    }

    public void removeDataHolder(DataHolder holder)
    {
      this.mHolders.remove(holder);
      notifyDataSetChanged();
    }

    public DataHolder queryDataHolder(int location)
    {
      if (this.mIsLoopView)
        location = getRealPosition(location);
      return (DataHolder)this.mHolders.get(location);
    }

    public int queryDataHolder(DataHolder holder)
    {
      return this.mHolders.indexOf(holder);
    }

    public void clearDataHolders()
    {
      this.mHolders.clear();
      notifyDataSetChanged();
    }

    public void setLoopView(boolean isLoopView)
    {
      this.mIsLoopView = isLoopView;
      notifyDataSetChanged();
    }

    public boolean isLoopView()
    {
      return this.mIsLoopView;
    }

    public final int getCount()
    {
      int size = this.mHolders.size();
      if (size == 0)
        return size;
      if (this.mIsLoopView) {
        return 2147483647;
      }
      return size;
    }

    public int getRealCount()
    {
      return this.mHolders.size();
    }

    public int getRealPosition(int position)
    {
      return position % getRealCount();
    }

    public int getMiddleFirstPosition()
    {
      int realCount = getRealCount();
      if (realCount == 0)
        throw new UnsupportedOperationException("the count for adapter should not be zero");
      int middlePosition = 1073741823;
      while (middlePosition % realCount != 0)
      {
        middlePosition--;
      }
      return middlePosition;
    }

    public final Object getItem(int position)
    {
      return queryDataHolder(position);
    }

    public final long getItemId(int position)
    {
      return position;
    }

    public final int getItemViewType(int position)
    {
      return queryDataHolder(position).getType();
    }

    public final int getViewTypeCount()
    {
      return this.mViewTypeCount;
    }

    public final View getView(int position, View convertView, ViewGroup parent)
    {
      DataHolder holder = queryDataHolder(position);
      View returnVal;
      if (convertView == null)
      {
        returnVal = holder.onCreateView(this.mContext, position, holder.getData());
      }
      else {
        returnVal = convertView;
        holder.onUpdateView(this.mContext, position, convertView, holder.getData());
      }
      return returnVal;
    }
  }
