package com.gridlayouttest.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gridlayouttest.R;
import com.gridlayouttest.model.Vistitor;

import java.util.List;

/**
 * 来访者适配器
 * Created by lady_zhou on 2017/4/11.
 */

public class VisitorAdapter extends RecyclerView.Adapter {
    private List<Vistitor> mList;

    public VisitorAdapter(List<Vistitor> list) {
        this.mList = list;
    }

    public static interface OnRecyclerViewListener {
        void onItemClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_layout, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTvNickName;

        public MyViewHolder(View view) {
            super(view);
            mTvNickName = (TextView) view.findViewById(R.id.tv_nickname);
        }
    }
}
