package com.lyx.classroom.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lyx.classroom.R;
import com.lyx.frame.annotation.IdParser;

/**
 * author:  luoyingxing
 * date: 2017/11/16.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected ViewGroup mActionbarLayout;
    protected ImageView mBackView;
    protected TextView mSubtitleView;
    protected TextView mTitleView;
    protected TextView mRightText;
    protected ImageView mRightImage;
    protected CharSequence mTitle = "";
    protected View.OnClickListener mRightImageOnClickListener;
    protected View.OnClickListener mRightTextOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        IdParser.inject(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.actionbar_custom_view);
            actionBar.setElevation(0);
            Toolbar parent = (Toolbar) actionBar.getCustomView().getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }

        mActionbarLayout = (RelativeLayout) findViewById(R.id.actionbar_custom_view_container);

        mBackView = (ImageView) findViewById(R.id.back);
        assert mBackView != null;
        mBackView.setVisibility(View.VISIBLE);
        mBackView.setOnClickListener(this);

        mSubtitleView = (TextView) findViewById(R.id.subtitle);
        assert mSubtitleView != null;

        mTitleView = (TextView) findViewById(R.id.title);
        assert mTitleView != null;
        mTitleView.setText(mTitle);

        mRightText = (TextView) findViewById(R.id.right_text);
        assert mRightText != null;
        mRightText.setVisibility(View.VISIBLE);
        mRightText.setOnClickListener(this);

        mRightImage = (ImageView) findViewById(R.id.right_image);
        assert mRightImage != null;
        mRightImage.setVisibility(View.VISIBLE);
        mRightImage.setOnClickListener(this);
    }

    /**
     * Activity 布局
     *
     * @return Activity的布局文件
     */
    protected int getContentView() {
        return R.layout.activity_base;
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mTitle = title;
        mTitleView.setText(mTitle);
    }

    public void showBackView(boolean visible) {
        mBackView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public ImageView getBackView() {
        return mBackView;
    }

    public ImageView getRightImage() {
        return mRightImage;
    }

    public void setSubtitle(CharSequence subtitle) {
        mSubtitleView.setText(subtitle);
    }

    public TextView getRightText() {
        return mRightText;
    }

    public void setOnRightImageClick(View.OnClickListener listener) {
        mRightImageOnClickListener = listener;
    }

    public void setOnRightTextClick(View.OnClickListener listener) {
        mRightTextOnClickListener = listener;
    }

    public void onRightImageClick() {
        if (mRightImageOnClickListener != null) {
            mRightImageOnClickListener.onClick(mRightImage);
        }
    }

    public void onRightTextClick() {
        if (mRightTextOnClickListener != null) {
            mRightTextOnClickListener.onClick(mRightText);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                goBack();
                break;
            case R.id.right_image:
                onRightImageClick();
                break;
            case R.id.right_text:
                onRightTextClick();
                break;
        }
    }

    private void goBack() {
        finish();
    }

    protected Toast mToast;

    protected void showToast(Object msg) {
        if (mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    protected void showLongToast(Object msg) {
        if (mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, "" + msg, Toast.LENGTH_LONG);
        mToast.show();
    }

}