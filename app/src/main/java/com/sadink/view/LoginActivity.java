package com.sadink.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.sadink.R;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import com.sadink.presenter.IUserLoginPresenter;
import com.sadink.presenter.UserLoginPresenter;


public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    private EditText username, password;
    private Button login, clear;
    private ProgressBar progressBar;
    private IUserLoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initView();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        login = (Button) findViewById(R.id.btn_login);
        clear = (Button) findViewById(R.id.btn_clear);
        login.setOnClickListener(this);
        clear.setOnClickListener(this);
        loginPresenter = new UserLoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    public String getUserName() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void clearUserName() {
        username.setText(null);
    }

    @Override
    public void clearPassword() {
        password.setText(null);
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity() {
        startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
    }

    @Override
    public void showFailedError() {
        showToast("登录信息有误!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginPresenter.login();
                break;
            case R.id.btn_clear:
                loginPresenter.clear();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }
}
