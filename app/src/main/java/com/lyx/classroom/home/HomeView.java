package com.lyx.classroom.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lyx.classroom.R;
import com.lyx.classroom.dao.Area;
import com.lyx.classroom.room.RoomActivity;
import com.lyx.frame.adapter.abs.CommonAdapter;
import com.lyx.frame.utils.DpiUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * HomeView
 * <p/>
 * Created by luoyingxing on 17/11/16.
 */
public class HomeView implements Serializable {
    private View mRootView;
    private Context mContext;
    private Area mArea;

    private ViewHolder viewHolder;

    public HomeView() {
    }

    public HomeView(Context context, Area area) {
        this.mContext = context;
        this.mArea = area;
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

    private CommonAdapter<Area> mAdapter;

    private void setAdapter() {
        final int width = (DpiUtils.getWidth(mContext) - DpiUtils.dipTopx(32)) / 2;
        final int height = width / 5 * 3;

        mAdapter = new CommonAdapter<Area>(mContext, new ArrayList<Area>(), R.layout.item_home_list) {
            @Override
            protected void convert(com.lyx.frame.adapter.abs.ViewHolder holder, Area area, int i) {
                holder.setText(R.id.tv_item_list, area.getTitle());
                ImageView imageView = holder.getView(R.id.iv_item_image);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
                imageView.setImageResource(area.getImage());
            }
        };

        viewHolder.gridView.setAdapter(mAdapter);
        viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, RoomActivity.class);
                intent.putExtra(RoomActivity.PARAM, mAdapter.getItem(position));
                mContext.startActivity(intent);
            }
        });
    }

    private void loadDataList() {
        mAdapter.addAll(mArea.getChildArea());
    }

    private class ViewHolder {
        GridView gridView;
    }
}
