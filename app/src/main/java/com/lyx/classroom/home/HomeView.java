package com.lyx.classroom.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lyx.classroom.R;
import com.lyx.frame.adapter.abs.CommonAdapter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * HomeView
 * <p/>
 * Created by luoyingxing on 16/10/19.
 */
public class HomeView implements Serializable {
    private static final String TAG = HomeView.class.getSimpleName();
    private View mRootView;
    private Context mContext;
    private String mTitle;

    private ViewHolder viewHolder;

    public HomeView() {
    }

    public HomeView(Context context, String title) {
        this.mContext = context;
        this.mTitle = title;
        init();
    }

    public View getRootView() {
        return mRootView == null ? null : mRootView;
    }

    private void init() {
        initView();
        setAdapter();
        loadDataList();
    }

    private void initView() {
        if (mRootView == null) {
            mRootView = LayoutInflater.from(mContext).inflate(R.layout.view_home_page, null);
            viewHolder = new ViewHolder();
            viewHolder.gridView = (GridView) mRootView.findViewById(R.id.gv_home);
            mRootView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) mRootView.getTag();
        }
    }

    private CommonAdapter<String> mAdapter;

    private void setAdapter() {
        mAdapter = new CommonAdapter<String>(mContext, new ArrayList<String>(), R.layout.item_home_list) {
            @Override
            protected void convert(com.lyx.frame.adapter.abs.ViewHolder holder, String s, int i) {
                holder.setText(R.id.tv_item_list, s);
            }
        };

        viewHolder.gridView.setAdapter(mAdapter);
        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void loadDataList() {
        mAdapter.addAll("top(头条，默认)", "shehui(社会)", "guonei(国内)");
    }

    private class ViewHolder {
        GridView gridView;
    }
}
