package in.hok.good.utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.AsyncTask;

public abstract class AsyncWeakTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>
{
    private List<WeakReference<Object>> mObjReferences = null;
    private Exception                   mExcep         = null;

    public AsyncWeakTask(Object...objects )
    {
        this.mObjReferences = new ArrayList(objects.length);
        for (Object obj : objects)
        {
            addToWeakReference(obj);
        }
    }

    public final void addToWeakReference(Object obj)
    {
        if (obj == null)
            throw new NullPointerException();
        this.mObjReferences.add(new WeakReference(obj));
    }

    protected void onPreExecute(Object[] objs)
    {
    }

    protected abstract Result doInBackgroundImpl(Params[] paramArrayOfParams) throws Exception;

    protected void onProgressUpdate(Object[] objs, Progress[] values)
    {
    }

    protected void onCancelled(Object[] objs)
    {
    }

    protected void onPostExecute(Object[] objs, Result result)
    {
    }

    protected void onException(Object[] objs, Exception e)
    {
    }

    protected final void onPreExecute()
    {
        Object[] objs = getObjects();
        if (objs == null)
            cancel(true);
        else
            onPreExecute(objs);
    }

    protected final Result doInBackground(Params[] params)
    {
        try
        {
            return doInBackgroundImpl(params);
        } catch (Exception e)
        {
            this.mExcep = e;
        }
        return null;
    }

    protected final void onProgressUpdate(Progress[] values)
    {
        if (isCancelled())
            return;
        Object[] objs = getObjects();
        if (objs == null)
            cancel(true);
        else
            onProgressUpdate(objs, values);
    }

    protected final void onCancelled()
    {
        Object[] objs = getObjects();
        if (objs != null)
            onCancelled(objs);
    }

    protected final void onPostExecute(Result result)
    {
        Object[] objs = getObjects();
        if (objs != null)
        {
            if (this.mExcep == null)
                onPostExecute(objs, result);
            else
                onException(objs, this.mExcep);
        }
    }

    private Object[] getObjects()
    {
        Object[] objs = new Object[this.mObjReferences.size()];
        Iterator objIterator = this.mObjReferences.iterator();
        for (int i = 0; i < objs.length; i++)
        {
            objs[i] = ((WeakReference) objIterator.next()).get();
            if (objs[i] == null)
                return null;
        }
        return objs;
    }
}