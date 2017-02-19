package com.sadink.mvp_rxjava_dagger2.ui.login.model.biz;

import com.sadink.mvp_rxjava_dagger2.ui.login.model.bean.UserBean;

/**
 * Created by dongdd on 2016/5/20 0020 08:57
 */
public interface IUserBiz {
    /**
     * 登录操作
     * @param userBean
     * @param onLoginListener
     */
    void login(UserBean userBean, UserBiz.OnLoginListener onLoginListener);
}
