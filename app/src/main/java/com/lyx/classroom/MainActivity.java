package com.lyx.classroom;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.lyx.classroom.home.MainAdapter;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.classroom.home.ViewPagerIndicator;
import com.lyx.frame.annotation.Id;
import com.lyx.frame.annotation.IdParser;

import java.util.Arrays;

public class MainActivity extends BaseActivity {
    @Id(R.id.focus_indicator)
    private ViewPagerIndicator mIndicator;
    @Id(R.id.focus_view_page)
    private ViewPager mViewPager;

    private String[] mTitle = new String[]{"东丽区", "韩东校区", "西区", "东区", "分院校区"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IdParser.inject(this);

        setTitle("韩山师范学院");
        getBackView().setImageResource(R.mipmap.ic_launcher);
        getRightImage().setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        MainAdapter adapter = new MainAdapter(this, Arrays.asList(mTitle));
        mIndicator.setTabItemTitles(Arrays.asList(mTitle));
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager, 0);
    }
}