package com.philipli.smartzone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.philipli.smartzone.R;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.util.CommonUtil;
import com.philipli.smartzone.util.ConstantUtil;
import com.philipli.smartzone.util.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by philip on 2017/10/12.
 */

public class LoginActivity extends RxBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mtoolbar;
    @BindView(R.id.enter_username)
    EditText mEnterUsername;
    @BindView(R.id.delete_username)
    ImageButton mDeleteUsername;
    @BindView(R.id.enter_password)
    EditText mEnterPassword;
    @BindView(R.id.login_btn)
    Button loginBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initToolBar() {

        mtoolbar.setTitle(R.string.login_title);
        mtoolbar.setNavigationIcon(R.drawable.ic_cancle);
        mtoolbar.setNavigationOnClickListener(along -> finish());

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mEnterUsername.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && mEnterUsername.getText().length() > 0) {
                mDeleteUsername.setVisibility(View.VISIBLE);
            } else {
                mDeleteUsername.setVisibility(View.INVISIBLE);
            }
        });
        mEnterPassword.setOnFocusChangeListener((v, hasFocus) -> {
        });
        mEnterUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEnterPassword.setText("");
                if (s.length() > 0) {
                    mDeleteUsername.setVisibility(View.VISIBLE);
                } else {
                    mDeleteUsername.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.login_btn, R.id.delete_username})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                boolean isNetConnected = CommonUtil.hasNetWork(this);
                if (!isNetConnected) {
                    Toast.makeText(this, "当前网络不可用，请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                login();
                break;
            case R.id.delete_username:
                mEnterUsername.setText("");
                mEnterPassword.setText("");
                mDeleteUsername.setVisibility(View.GONE);
                mEnterUsername.setFocusable(true);
                mEnterUsername.setFocusableInTouchMode(true);

        }


    }

    private void login() {
        String name = mEnterUsername.getText().toString();
        String password = mEnterPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        PreferenceUtil.putBoolean(ConstantUtil.KEY, true);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

}
