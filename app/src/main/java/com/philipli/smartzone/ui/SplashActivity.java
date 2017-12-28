package com.philipli.smartzone.ui;

import android.content.Intent;
import android.os.Bundle;

import com.philipli.smartzone.R;
import com.philipli.smartzone.util.ConstantUtil;
import com.philipli.smartzone.util.PreferenceUtil;
import com.philipli.smartzone.util.SystemUiVisibilityUtil;
import com.trello.rxlifecycle2.components.RxActivity;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 * Created by philip on 2017/10/11.
 */

public class SplashActivity extends RxActivity {
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bind = ButterKnife.bind(this);
        SystemUiVisibilityUtil.hideStatusBar(getWindow(), true);
        setSplash();
    }

    private void setSplash() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .compose(this.<Long>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> finishTask());
    }

    private void finishTask() {

        boolean isLogin = PreferenceUtil.getBoolean(ConstantUtil.KEY, false);
        if (isLogin) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }

        SplashActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
