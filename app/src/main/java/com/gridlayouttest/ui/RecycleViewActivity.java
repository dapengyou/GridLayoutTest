package com.gridlayouttest.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gridlayouttest.MainActivity;
import com.gridlayouttest.R;
import com.gridlayouttest.model.Vistitor;
import com.gridlayouttest.ui.adapter.VisitorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lady_zhou on 2017/4/11.
 */

public class RecycleViewActivity extends Activity implements VisitorAdapter.OnRecyclerViewListener {
    private RecyclerView recyclerView;
    //private List<Vistitor> mList;
    private List<String> mList =new ArrayList<String>();
    private VisitorAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i <= 20; i++) {
            mList.add(""+i);
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.id_recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VisitorAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this,"被点击"+position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
