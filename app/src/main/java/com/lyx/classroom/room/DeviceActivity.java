package com.lyx.classroom.room;

import android.os.Bundle;

import com.lyx.classroom.R;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.classroom.dao.Room;
import com.lyx.frame.annotation.IdParser;

public class DeviceActivity extends BaseActivity {
    public static final String PARAM = "AREA";

    private Room mRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        IdParser.inject(this);

        mRoom = (Room) getIntent().getSerializableExtra(PARAM);
        if (null != mRoom) {
            setSubtitle(mRoom.getName());
        }
    }
}