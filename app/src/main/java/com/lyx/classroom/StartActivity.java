package com.lyx.classroom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lyx.classroom.base.Constant;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class StartActivity extends Activity {
    private Subscriber mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubscriber = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Long aLong) {
                if (aLong > Constant.TIME_START) {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        Observable.timer(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mSubscriber && !mSubscriber.isUnsubscribed()) {
            mSubscriber.unsubscribe();
            mSubscriber = null;
        }
    }
}