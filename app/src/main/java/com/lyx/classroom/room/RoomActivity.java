package com.lyx.classroom.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lyx.classroom.R;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.classroom.entity.Node;
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

    private Node mNode;
    private CommonAdapter<Node> mAdapter;

    private int[] mBackground = new int[]{R.color.bg_room_one, R.color.bg_room_two, R.color.bg_room_three, R.color.bg_room_four, R.color.bg_room_five};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        IdParser.inject(this);

        mNode = (Node) getIntent().getSerializableExtra(PARAM);
        if (null != mNode) {
            setSubtitle(mNode.getTitle());
        }

        mAdapter = new CommonAdapter<Node>(this, new ArrayList<Node>(), R.layout.item_room_list) {
            @Override
            protected void convert(ViewHolder holder, Node Node, int position) {
                holder.setText(R.id.tv_room_title, Node.getName());
                holder.setText(R.id.tv_room_time, TimeUtils.showTime(Node.getTime()));

                holder.getView(R.id.iv_room_light).setVisibility(Node.isHasLight() ? View.VISIBLE : View.GONE);
                holder.getView(R.id.iv_room_air).setVisibility(Node.isHasAir() ? View.VISIBLE : View.GONE);
                holder.getView(R.id.iv_room_curtains).setVisibility(Node.isHasCurtain() ? View.VISIBLE : View.GONE);
                holder.getView(R.id.iv_room_breeze).setVisibility(Node.isHasBreeze() ? View.VISIBLE : View.GONE);

                holder.getView(R.id.tv_room_line).setBackgroundColor(getResources().getColor(mBackground[position % 5]));
            }
        };

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(RoomActivity.this, DeviceActivity.class);
                intent.putExtra(DeviceActivity.PARAM, mAdapter.getItem(position));
                startActivity(intent);
            }
        });

        mAdapter.addAll(mNode.getNodeList());
    }
}