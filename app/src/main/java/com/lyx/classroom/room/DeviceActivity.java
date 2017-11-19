package com.lyx.classroom.room;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyx.classroom.R;
import com.lyx.classroom.base.BaseActivity;
import com.lyx.classroom.entity.Node;
import com.lyx.frame.annotation.Id;
import com.lyx.frame.annotation.IdParser;
import com.lyx.frame.annotation.OnClick;
import com.lyx.frame.annotation.OnTouch;

public class DeviceActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    public static final String PARAM = "AREA";
    @Id(R.id.tv_device_light_status)
    private TextView mLightStatusTV;
    @Id(R.id.tv_device_curtains_status)
    private TextView mCurtainsStatusTV;
    @Id(R.id.iv_device_light)
    private ImageView mLightIV;
    @OnClick
    @OnTouch
    @Id(R.id.tv_device_book)
    private TextView mBookTV;
    @OnClick
    @OnTouch
    @Id(R.id.tv_device_projection)
    private TextView mProjectionTV;
    @OnClick
    @OnTouch
    @Id(R.id.tv_device_teach)
    private TextView mTeachTV;
    @OnClick
    @OnTouch
    @Id(R.id.cb_device_power)
    private CheckBox mPowerCb;
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
    @OnTouch
    @Id(R.id.cb_device_air_power)
    private CheckBox mAirPowerCb;
    @OnClick
    @OnTouch
    @Id(R.id.iv_device_air_reduce)
    private ImageView mAirReduceIV;
    @OnClick
    @OnTouch
    @Id(R.id.iv_device_air_increase)
    private ImageView mAirIncreaseIV;

    private Node mNode;

    /**
     * 空调是否已开
     */
    private boolean mAirOpen = false;
    /**
     * 空调温度
     */
    private int mAirTemperature = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        IdParser.inject(this);

        mNode = (Node) getIntent().getSerializableExtra(PARAM);
        if (null != mNode) {
            setSubtitle(mNode.getName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBreezeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showToast(isChecked ? "清风打开" : "清风关闭");
            }
        });

        mCurtainsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCurtainsStatusTV.setText(isChecked ? "窗帘：开" : "窗帘：关");
            }
        });

        mPowerCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mLightIV.setImageResource(R.mipmap.ic_device_light_high);
                    mLightStatusTV.setText("灯光：开");
                } else {
                    mLightIV.setImageResource(R.mipmap.ic_device_light_close);
                    mLightStatusTV.setText("灯光：关");
                }
            }
        });

        mAirPowerCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAirOpen = isChecked;
                updateAir();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_device_teach:
                if (mPowerCb.isChecked()) {
                    mLightIV.setImageResource(R.mipmap.ic_device_light_high);
                    mLightStatusTV.setText("灯光：开");
                } else {
                    showToast("请先开灯！");
                }
                break;
            case R.id.tv_device_book:
                if (mPowerCb.isChecked()) {
                    mLightIV.setImageResource(R.mipmap.ic_device_light_auto);
                    mLightStatusTV.setText("灯光：开");
                } else {
                    showToast("请先开灯！");
                }
                break;
            case R.id.tv_device_projection:
                if (mPowerCb.isChecked()) {
                    mLightIV.setImageResource(R.mipmap.ic_device_light_half);
                    mLightStatusTV.setText("灯光：开");
                    mCurtainsStatusTV.setText("窗帘：开");
                    mCurtainsSwitch.setChecked(true);
                } else {
                    showToast("请先开灯！");
                }
                break;
            case R.id.iv_device_air_reduce:
                if (mAirOpen) {
                    mAirTemperature--;
                    updateAir();
                } else {
                    showToast("请先打开空调！");
                }
                break;
            case R.id.iv_device_air_increase:
                if (mAirOpen) {
                    mAirTemperature++;
                    updateAir();
                } else {
                    showToast("请先打开空调！");
                }
                break;
        }
    }

    private void updateAir() {
        mAirStatusTV.setText(mAirOpen ? "空调已开，温度" + mAirTemperature + "°" : "空调已关");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.94f, 1.0f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.94f, 1.0f);
                ObjectAnimator.ofPropertyValuesHolder(v, pvhX, pvhY).setDuration(100).start();
                break;
        }
        return false;
    }
}