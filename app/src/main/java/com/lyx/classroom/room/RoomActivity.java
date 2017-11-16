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

import java.util.ArrayList;

public class RoomActivity extends BaseActivity {
    public static final String PARAM = "AREA";
    private Area mArea;

    @Id(R.id.lv_room)
    private ListView mListView;

    private CommonAdapter<Room> mAdapter;

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
            protected void convert(ViewHolder holder, Room room, int i) {
                holder.setText(R.id.tv_room_title, room.getName());
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