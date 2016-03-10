package in.hok.good.adapterview;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.List;

public abstract class GroupDataHolder extends in.hok.good.adapterview.DataHolder
{
  private List<in.hok.good.adapterview.DataHolder> mChildren = null;
  private boolean mIsExpanded;

  public GroupDataHolder(Object data, DisplayImageOptions[] options)
  {
    super(data, options);
    this.mChildren = new ArrayList();
  }

  public GroupDataHolder(Object data, List<in.hok.good.adapterview.DataHolder> children, DisplayImageOptions[] options)
  {
    super(data, options);
    if (children == null)
      throw new NullPointerException();
    this.mChildren = new ArrayList(children);
  }

  public boolean isExpanded()
  {
    return this.mIsExpanded;
  }

  void setExpanded(boolean isExpanded)
  {
    this.mIsExpanded = isExpanded;
  }

  public void addChild(in.hok.good.adapterview.DataHolder holder)
  {
    this.mChildren.add(holder);
  }

  public void addChild(int location, in.hok.good.adapterview.DataHolder holder)
  {
    this.mChildren.add(location, holder);
  }

  public void addChildren(List<in.hok.good.adapterview.DataHolder> holders)
  {
    this.mChildren.addAll(holders);
  }

  public void addChildren(int location, List<in.hok.good.adapterview.DataHolder> holders)
  {
    this.mChildren.addAll(location, holders);
  }

  public void removeChild(int location)
  {
    this.mChildren.remove(location);
  }

  public void removeChild(in.hok.good.adapterview.DataHolder holder)
  {
    this.mChildren.remove(holder);
  }

  public in.hok.good.adapterview.DataHolder queryChild(int location)
  {
    return (in.hok.good.adapterview.DataHolder)this.mChildren.get(location);
  }

  public int queryChild(in.hok.good.adapterview.DataHolder holder)
  {
    return this.mChildren.indexOf(holder);
  }

  public void clearChildren()
  {
    this.mChildren.clear();
  }

  public int getChildrenCount()
  {
    return this.mChildren.size();
  }
}
