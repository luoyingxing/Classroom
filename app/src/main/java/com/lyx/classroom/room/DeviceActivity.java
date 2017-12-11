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
import com.lyx.frame.annotation.OnClick;
import com.lyx.frame.annotation.OnTouch;

public class DeviceActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {
    public static final String PARAM = "PARAM";
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
    @Id(R.id.tv_device_light_reduce)
    private TextView mReduceTV;
    @OnClick
    @OnTouch
    @Id(R.id.tv_device_light_increase)
    private TextView mIncreaseTV;
    @OnClick
    @OnTouch
    @Id(R.id.tv_device_teach)
    private TextView mTeachTV;
    @OnClick
    @OnTouch
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
     * 灯光亮度级别
     */
    private int mLightLevel;
    /**
     * 空调温度
     */
    private int mAirTemperature = 25;

    private boolean mIsSwitch = true;

    @Override
    protected int getContentView() {
        return R.layout.activity_device;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                showToast(isChecked ? getString(R.string.device_breeze_on) : getString(R.string.device_breeze_off));
            }
        });

        mCurtainsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCurtainsStatusTV.setText(isChecked ? getString(R.string.device_curtains_status_on) : getString(R.string.device_curtains_status_off));
            }
        });

        mAirPowerCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateAir();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_device_teach:
                clearLevel();
                mLightIV.setImageResource(R.mipmap.ic_device_light_high);
                mLightStatusTV.setText(getString(R.string.device_light_status_on));
                break;
            case R.id.tv_device_book:
                clearLevel();
                mLightIV.setImageResource(R.mipmap.ic_device_light_auto);
                mLightStatusTV.setText(getString(R.string.device_light_status_on));
                break;
            case R.id.tv_device_light_increase:
                mLightLevel++;
                if (mLightLevel > 10) {
                    mLightLevel = 10;
                }

                if (mIsSwitch) {
                    mLightLevel = 5;
                    mIsSwitch = false;
                }

                updateLight();
                break;
            case R.id.tv_device_light_reduce:
                mLightLevel--;
                if (mLightLevel < 0) {
                    mLightLevel = 0;
                }
                updateLight();
                break;
            case R.id.tv_device_power:
                clearLevel();
                mLightIV.setImageResource(R.mipmap.ic_device_light_close);
                mLightStatusTV.setText(getString(R.string.device_light_status_off));
                mCurtainsSwitch.setChecked(false);
                break;
            case R.id.iv_device_air_reduce:
                if (mAirPowerCb.isChecked()) {
                    mAirTemperature--;
                    updateAir();
                } else {
                    showToast(getString(R.string.device_air_on_tip));
                }
                break;
            case R.id.iv_device_air_increase:
                if (mAirPowerCb.isChecked()) {
                    mAirTemperature++;
                    updateAir();
                } else {
                    showToast(getString(R.string.device_air_on_tip));
                }
                break;
        }
    }

    private void clearLevel() {
        mIsSwitch = true;
        mLightLevel = 0;
    }

    private void updateLight() {
        if (mLightLevel > 5) {
            mLightIV.setImageResource(R.mipmap.ic_device_light_high);
            mCurtainsStatusTV.setText(getString(R.string.device_curtains_status_on));
            mCurtainsSwitch.setChecked(true);
            mLightStatusTV.setText(getString(R.string.device_light_status_on) + "，亮度" + mLightLevel * 100 / 10 + "%");
        } else if (mLightLevel < 5) {
            mLightIV.setImageResource(R.mipmap.ic_device_light_close);
            mCurtainsStatusTV.setText(getString(R.string.device_curtains_status_off));
            mCurtainsSwitch.setChecked(false);
            if (mLightLevel == 0) {
                mLightStatusTV.setText(getString(R.string.device_light_status_off));
            } else {
                mLightStatusTV.setText(getString(R.string.device_light_status_on) + "，亮度" + mLightLevel * 100 / 10 + "%");
            }
        } else {
            mLightIV.setImageResource(R.mipmap.ic_device_light_half);
            mLightStatusTV.setText(String.format("%s，亮度50%%", getString(R.string.device_light_status_on)));
            mCurtainsSwitch.setChecked(true);
        }
    }

    private void updateAir() {
        mAirStatusTV.setText(mAirPowerCb.isChecked() ?
                getString(R.string.device_air_open_tip) + mAirTemperature + getString(R.string.device_air_open_tip_temperature) :
                getString(R.string.device_air_close_tip));
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