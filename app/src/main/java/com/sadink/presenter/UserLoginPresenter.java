package com.sadink.presenter;

import android.os.UserManager;
import android.util.Log;

import com.sadink.model.bean.User;
import com.sadink.model.biz.IUserBiz;
import com.sadink.model.biz.UserBiz;
import com.sadink.view.ILoginView;


/**
 * Created by dongdd on 2016/5/20 0020 09:52
 * Presenter是用作Model和View之间交互的桥梁，其实也是主要看该功能有什么操作，比如本例，两个操作:login和clear。
 */
public class UserLoginPresenter implements UserBiz.OnLoginListener,IUserLoginPresenter<ILoginView> {
    private IUserBiz userBiz;
    private ILoginView loginView;

    private User mUserManager = new User();

    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        if (password == null || password.length() < 6) return;

        Log.i("sadink", "username: " +username +  "password: " +password);

//        mUserManager.performLogin(username, password);
    }

    public void setUserManager(User userManager) {  //<==
        this.mUserManager = userManager;
    }

    public UserLoginPresenter() {
        userBiz = new UserBiz();
    }


    @Override
    public void login() {
        loginView.showProgress();
        userBiz.login(loginView.getUserName(), loginView.getPassword(), this);

    }

    @Override
    public void attachView(ILoginView v) {
        this.loginView =  v;
    }

    @Override
    public void loginSuccess() {
                loginView.toMainActivity();
                loginView.hideProgress();
    }

    @Override
    public void loginFailed() {
                 loginView.hideProgress();
                loginView.showFailedError();
    }


    @Override
    public void detachView() {
        loginView = null;
    }

    @Override
    public void clear() {
        loginView.clearUserName();
        loginView.clearPassword();
    }

}
