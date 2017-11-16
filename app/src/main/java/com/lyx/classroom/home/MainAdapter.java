package com.lyx.classroom.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyx.classroom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * MainAdapter
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class MainAdapter extends PagerAdapter {
    private static final String TAG = MainAdapter.class.getSimpleName();
    private Context mContext;

    private int mViesCount;
    private List<String> mTitleList = new ArrayList<>();

    public MainAdapter(Context context, List<String> title) {
        this.mContext = context;
        this.mTitleList = title;
        init();
    }

    private void init() {
        mViesCount = mTitleList.size();
    }

    @Override
    public int getCount() {
        return mViesCount;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View views = null;
        View mPagerView = LayoutInflater.from(mContext).inflate(R.layout.item_views_adapter, null, false);
        ViewGroup viewGroup = (ViewGroup) mPagerView.findViewById(R.id.rl_fragment_news_rootView);

        HomeView focusView = new HomeView(mContext, mTitleList.get(position));
        views = focusView.getRootView();

        if (views != null) {
            if (views.getParent() != null) {
                ((ViewGroup) views.getParent()).removeView(views);
            }
            viewGroup.addView(views);
        }
        container.addView(mPagerView);
        return mPagerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
