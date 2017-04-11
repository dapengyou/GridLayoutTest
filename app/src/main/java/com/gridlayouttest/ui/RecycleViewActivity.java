package com.gridlayouttest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.gridlayouttest.R;
import com.gridlayouttest.model.Vistitor;
import com.gridlayouttest.ui.adapter.VisitorAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by lady_zhou on 2017/4/11.
 */

public class RecycleViewActivity extends Activity {
    private RecyclerView recyclerView;
    private List<Vistitor> mList;
    private VisitorAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.id_recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VisitorAdapter(mList);
        recyclerView.setAdapter(mAdapter);
    }

}
