package com.gridlayouttest.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gridlayouttest.R;
import com.gridlayouttest.model.Vistitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 来访者适配器
 * Created by lady_zhou on 2017/4/11.
 */

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.ViewHolder> {
    //private List<Vistitor> mList;
    private Context context;
    private List<String> mList;

    public VisitorAdapter(Context context, List<String> list) {
        this.context = context;
        this.mList = list;
    }

    public interface OnRecyclerViewListener {
        void onItemClick(View view, int position);
    }

    private OnRecyclerViewListener recyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.recyclerViewListener = onRecyclerViewListener;
    }

    /**
     * 防止数据源为空   空指针
     *
     * @param list
     */
    public void setList(List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mList = list;
    }

    //RecyclerView显示的子View
    //该方法返回是ViewHolder，当有可复用View时，就不再调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_layout, parent, false);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("onClick", "点击");
//            }
//        });
        return new ViewHolder(view);
    }

    //将数据绑定到子View，会自动复用View
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout linearLayout;
        int position;

        public ViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(recyclerViewListener!=null){
                recyclerViewListener.onItemClick(v,position);
            }
        }
    }
}
