package com.lyx.classroom.room;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lyx.classroom.R;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.classroom.dao.Area;
import com.lyx.classroom.dao.Room;
import com.lyx.frame.adapter.abs.CommonAdapter;
import com.lyx.frame.adapter.abs.ViewHolder;
import com.lyx.frame.annotation.Id;
import com.lyx.frame.annotation.IdParser;
import com.lyx.frame.utils.TimeUtils;

import java.util.ArrayList;

public class RoomActivity extends BaseActivity {
    public static final String PARAM = "AREA";
    @Id(R.id.lv_room)
    private ListView mListView;

    private Area mArea;
    private CommonAdapter<Room> mAdapter;

    private int[] mBackground = new int[]{R.color.bg_room_one, R.color.bg_room_two, R.color.bg_room_three, R.color.bg_room_four, R.color.bg_room_five};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        IdParser.inject(this);

        mArea = (Area) getIntent().getSerializableExtra(PARAM);
        if (null != mArea) {
            setSubtitle(mArea.getTitle());
        }

        mAdapter = new CommonAdapter<Room>(this, new ArrayList<Room>(), R.layout.item_room_list) {
            @Override
            protected void convert(ViewHolder holder, Room room, int position) {
                holder.setText(R.id.tv_room_title, room.getName());
                holder.setText(R.id.tv_room_time, TimeUtils.showTime(room.getTime()));

                holder.getView(R.id.iv_room_light).setVisibility(room.isHasLight() ? View.VISIBLE : View.GONE);
                holder.getView(R.id.iv_room_air).setVisibility(room.isHasAir() ? View.VISIBLE : View.GONE);
                holder.getView(R.id.iv_room_curtains).setVisibility(room.isHasCurtain() ? View.VISIBLE : View.GONE);
                holder.getView(R.id.iv_room_breeze).setVisibility(room.isHasBreeze() ? View.VISIBLE : View.GONE);

                holder.getView(R.id.tv_room_line).setBackgroundColor(getResources().getColor(mBackground[position % 5]));
            }
        };

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        mAdapter.addAll(mArea.getRoomList());
    }
}