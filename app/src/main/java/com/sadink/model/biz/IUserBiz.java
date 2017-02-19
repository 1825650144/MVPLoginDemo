package com.sadink.model.biz;

/**
 * Created by dongdd on 2016/5/20 0020 08:57
 */
public interface IUserBiz {
    /**
     * 登录操作
     * @param username
     * @param password
     * @param onLoginListener
     */
    void login(String username, String password, UserBiz.OnLoginListener onLoginListener);
}
