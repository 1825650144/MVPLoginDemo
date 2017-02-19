package com.sadink.mvp_rxjava_dagger2.ui.login.model.biz;

import com.sadink.mvp_rxjava_dagger2.ui.login.model.bean.UserBean;

import javax.inject.Inject;

/**
 * Created by dongdd on 2016/5/26 0026 11:04
 */
public class UserBiz implements IUserBiz {

    @Inject
    public UserBiz() {}


    @Override
    public void login(UserBean userBean, OnLoginListener onLoginListener) {
        if (userBean.getUsername().equals("admin") && userBean.getPassword().equals("123")) {
            onLoginListener.loginSuccess();
        } else {
            onLoginListener.loginFailed();
        }
    }


    /**
     * 此接口用来链接Presenter层
     */
    public interface OnLoginListener {

        void loginSuccess();

        void loginFailed();
    }
}
