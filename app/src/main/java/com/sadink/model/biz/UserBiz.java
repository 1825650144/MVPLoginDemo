package com.sadink.model.biz;

import com.sadink.model.bean.User;

/**
 * Created by dongdd on 2016/5/20 0020 08:58
 */
public class UserBiz implements IUserBiz {

    @Override
    public void login(final String username, final String password, final UserBiz.OnLoginListener onLoginListener) {
        if (username.equals("admin") && password.equals("123")) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
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
