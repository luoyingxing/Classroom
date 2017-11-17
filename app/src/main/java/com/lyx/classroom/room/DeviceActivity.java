package com.lyx.classroom.room;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyx.classroom.R;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.classroom.dao.Room;
import com.lyx.frame.annotation.Id;
import com.lyx.frame.annotation.IdParser;
import com.lyx.frame.annotation.OnClick;

public class DeviceActivity extends BaseActivity implements View.OnClickListener {
    public static final String PARAM = "AREA";
    @Id(R.id.tv_device_global_status)
    private TextView mGlobalStatusTV;
    @Id(R.id.iv_device_light)
    private ImageView mLightIV;
    @OnClick
    @Id(R.id.tv_device_book)
    private TextView mBookTV;
    @OnClick
    @Id(R.id.tv_device_projection)
    private TextView mProjectionTV;
    @OnClick
    @Id(R.id.tv_device_teach)
    private TextView mTeachTV;
    @OnClick
    @Id(R.id.tv_device_power)
    private TextView mPowerTV;
    @Id(R.id.iv_device_breeze)
    private ImageView mBreezeIV;
    @Id(R.id.sc_device_breeze)
    private SwitchCompat mBreezeSwitch;
    @Id(R.id.iv_device_curtains)
    private ImageView mCurtainsIV;
    @Id(R.id.sc_device_curtains)
    private SwitchCompat mCurtainsSwitch;
    @Id(R.id.tv_device_air)
    private TextView mAirTV;
    @Id(R.id.tv_device_air_status)
    private TextView mAirStatusTV;
    @OnClick
    @Id(R.id.iv_device_air_power)
    private ImageView mAirPowerIV;
    @OnClick
    @Id(R.id.iv_device_air_reduce)
    private ImageView mAirReduceIV;
    @OnClick
    @Id(R.id.iv_device_air_increase)
    private ImageView mAirIncreaseIV;

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

    @Override
    protected void onStart() {
        super.onStart();
        mBreezeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        mCurtainsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_device_book:
                showToast("tv_device_book");
                break;
            case R.id.tv_device_projection:
                showToast("tv_device_projection");
                break;
            case R.id.tv_device_teach:
                showToast("tv_device_teach");
                break;
            case R.id.tv_device_power:
                showToast("tv_device_power");
                break;
            case R.id.iv_device_air_power:
                showToast("iv_device_air_power");
                break;
            case R.id.iv_device_air_reduce:
                showToast("iv_device_air_reduce");
                break;
            case R.id.iv_device_air_increase:
                showToast("iv_device_air_increase");
                break;
        }
    }
}