package com.lyx.classroom;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lyx.classroom.dao.Area;
import com.lyx.classroom.home.MainAdapter;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.frame.annotation.Id;
import com.lyx.frame.annotation.IdParser;
import com.lyx.frame.widget.ViewPagerIndicator;


public class MainActivity extends BaseActivity {
    @Id(R.id.focus_indicator)
    private ViewPagerIndicator mIndicator;
    @Id(R.id.focus_view_page)
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IdParser.inject(this);

        setTitle(getString(R.string.home_title));
        getRightImage().setImageResource(R.mipmap.ic_home_more);
        getBackView().setImageResource(R.mipmap.ic_home_personal);
        getBackView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(getResources().getString(R.string.home_personal));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewPager.setAdapter(new MainAdapter(this, Area.getAreaList()));
        mIndicator.setTabItemTitles(Area.getAreaList());
        mIndicator.setViewPager(mViewPager, 0);
    }
}