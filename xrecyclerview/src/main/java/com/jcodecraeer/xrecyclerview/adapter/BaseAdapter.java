package com.jcodecraeer.xrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


abstract public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mObjects = new ArrayList<>();

    public int mCurrentPosition;

    public BaseAdapter(Context context) {
    }


    private final Object mLock = new Object();

    public void addAll(Collection<? extends T> collection) {
        if (collection != null && collection.size() != 0) {
            synchronized (mLock) {
                mObjects.addAll(collection);
            }
        }

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        final BaseViewHolder viewHolder = OnCreateViewHolder(parent, viewType);

        return viewHolder;
    }

    abstract public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType);


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        holder.itemView.setId(position);
        OnBindViewHolder(holder, position);
    }

    public void OnBindViewHolder(BaseViewHolder holder, final int position) {
        mCurrentPosition = position;
        holder.setData(getItem(position));
    }


    public T getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public int getItemCount() {
        if (mObjects != null && mObjects.size() > 0) {
            return mObjects.size();
        } else {
            return 0;
        }
    }

}