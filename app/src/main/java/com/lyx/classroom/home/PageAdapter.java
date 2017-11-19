package com.lyx.classroom.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyx.classroom.R;
import com.lyx.classroom.entity.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * PageAdapter
 * <p/>
 * Created by luoyingxing on 17/11/16..
 */
public class PageAdapter extends PagerAdapter {
    private Context mContext;
    private List<Node> mNodeList = new ArrayList<>();

    public PageAdapter(Context context, List<Node> list) {
        this.mContext = context;
        this.mNodeList = list;
    }

    @Override
    public int getCount() {
        if (mNodeList != null) {
            return mNodeList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View mPagerView = LayoutInflater.from(mContext).inflate(R.layout.item_views_adapter, null, false);
        ViewGroup viewGroup = (ViewGroup) mPagerView.findViewById(R.id.rl_home_root);

        PageView view = new PageView(mContext, mNodeList.get(position));
        View root = view.getRootView();

        if (root != null) {
            if (root.getParent() != null) {
                ((ViewGroup) root.getParent()).removeView(root);
            }
            viewGroup.addView(root);
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
