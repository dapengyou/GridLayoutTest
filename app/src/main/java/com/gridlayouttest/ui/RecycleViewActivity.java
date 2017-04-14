package com.gridlayouttest.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gridlayouttest.MainActivity;
import com.gridlayouttest.R;
import com.gridlayouttest.ui.adapter.VisitorAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lady_zhou on 2017/4/11.
 */

public class RecycleViewActivity extends Activity implements VisitorAdapter.OnRecyclerViewListener {
    //private RecyclerView recyclerView;
    //private List<Vistitor> mList;
    private XRecyclerView recyclerView;
    private List<String> mList = new ArrayList<String>();
    private VisitorAdapter mAdapter;
    private int refreshTime = 0;
    private int time = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            mList.add("" + i);
        }
    }

    private void initView() {
        recyclerView = (XRecyclerView) findViewById(R.id.id_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VisitorAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewListener(this);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                refreshTime++;
                time = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mList.clear();
                        initData();
                        mAdapter.notifyDataSetChanged();
                        recyclerView.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                // load more data here
                if (time < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            initData();
                            recyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            initData();
                            recyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                time++;

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
