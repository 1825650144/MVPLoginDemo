package com.sadink.mvp_rxjava_dagger2.ui.login.presenter;

import com.sadink.mvp_rxjava_dagger2.ui.login.model.biz.UserBiz;
import com.sadink.mvp_rxjava_dagger2.ui.login.view.ILoginView;

import javax.inject.Inject;

/**
 * Created by dongdd on 2016/5/20 0020 09:52
 * Presenter是用作Model和View之间交互的桥梁，其实也是主要看该功能有什么操作，比如本例，两个操作:login和clear。
 */
public class UserLoginPresenter implements UserBiz.OnLoginListener,IUserLoginPresenter<ILoginView> {

    private ILoginView loginView;
    private UserBiz userBiz;


    @Inject
    public UserLoginPresenter(UserBiz userBiz) {
        this.userBiz = userBiz;
    }


    @Override
    public void login() {
        loginView.showProgress();
        userBiz.login(loginView.getUserBean(), this);

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
        loginView.clearUserBean();
    }

}
