package com.lyx.classroom;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lyx.classroom.entity.Node;
import com.lyx.classroom.home.PageAdapter;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.frame.annotation.Id;
import com.lyx.frame.widget.ViewPagerIndicator;


public class MainActivity extends BaseActivity {
    @Id(R.id.focus_indicator)
    private ViewPagerIndicator mIndicator;
    @Id(R.id.focus_view_page)
    private ViewPager mViewPager;

    private long mExitTime;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.home_title));
        getRightImage().setImageResource(R.mipmap.ic_home_more);
        getBackView().setImageResource(R.mipmap.ic_home_personal);
        getBackView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(getResources().getString(R.string.home_personal));
            }
        });

        mViewPager.setAdapter(new PageAdapter(this, Node.getAreaList()));
        mIndicator.setTabItemTitles(Node.getAreaList());
        mIndicator.setViewPager(mViewPager, 0);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            showToast(getString(R.string.main_exit));
            mExitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}