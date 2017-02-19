package com.sadink.mvp_rxjava_dagger2.ui.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.sadink.R;
import com.sadink.glide.GlideMainActivity;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import com.sadink.mvp_rxjava_dagger2.logcat.LogInfo;
import com.sadink.mvp_rxjava_dagger2.ui.login.model.UserModel;
import com.sadink.mvp_rxjava_dagger2.ui.login.model.bean.UserBean;
import com.sadink.mvp_rxjava_dagger2.ui.login.presenter.DaggerUserComponent;
import com.sadink.mvp_rxjava_dagger2.ui.login.presenter.UserComponent;
import com.sadink.mvp_rxjava_dagger2.ui.login.presenter.UserLoginPresenter;
import com.sadink.mvp_rxjava_dagger2.ui.weather.view.WeatherActivity;
import com.socks.library.KLog;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 登录视图
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    UserBean userBean;

    @Inject
    UserLoginPresenter loginPresenter;
    @Bind(R.id.btn_glide)
    Button btnGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);

        UserComponent userComponent = DaggerUserComponent.builder().userModel(new UserModel(getApplicationContext())).build();
        userComponent.inject(this);

        KLog.d(LogInfo.LOGCAT, "s" + userBean);
        loginPresenter.attachView(this);
    }


    @Override
    public UserBean getUserBean() {
        userBean.setUsername(etUsername.getText() != null ? etUsername.getText().toString() : "");
        userBean.setPassword(etPassword.getText() != null ? etPassword.getText().toString() : "");
        return userBean;
    }

    @Override
    public void clearUserBean() {
        etUsername.setText("");
        etPassword.setText("");
        userBean = null;
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


    @OnClick({R.id.btn_login, R.id.btn_clear})
    public void onClick(View view) {
        switch (view.getId()) {
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
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.btn_glide)
    public void onClick() {
        startActivity(GlideMainActivity.class);
    }

    /**
     * activity跳转类
     * @param class_
     */
    private void startActivity(Class<?> class_){
        startActivity(new Intent(getApplicationContext(),class_));
    }
}
