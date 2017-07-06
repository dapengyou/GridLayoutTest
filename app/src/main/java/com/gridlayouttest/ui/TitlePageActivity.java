package com.gridlayouttest.ui;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gridlayouttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lady_zhou on 2017/7/3.
 */

public class TitlePageActivity extends AppCompatActivity {
    private List<Fragment> mFragment = new ArrayList<>();
    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_titlepage);

        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }
}
