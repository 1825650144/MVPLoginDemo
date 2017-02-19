package com.sadink.mvp_rxjava_dagger2.ui.login.model.bean;

import javax.inject.Inject;

/**
 * Created by dongdd on 2016/5/26 0026 10:48
 */
public class UserBean {

    private String username;
    private String password;

    @Inject
    public UserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
