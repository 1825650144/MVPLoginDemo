package com.sadink.dagger2;

import javax.inject.Inject;

/**
 * Created by dongdd on 2016/5/26 0026 09:13
 */
public class LoginPresenter {

    private final UserManager mUserManager;
    private final PasswordValidator mPasswordValidator;

    @Inject
    public LoginPresenter(UserManager mUserManager, PasswordValidator mPasswordValidator) {
        this.mUserManager = mUserManager;
        this.mPasswordValidator = mPasswordValidator;
    }

    public void login(String username, String password) {
        if (username == null || username.length() == 0) return;
        if (mPasswordValidator.verifyPassword(password)) return;

        mUserManager.performLogin(username, password);
    }

    public boolean isLoggedIn() {
        return false;
    }
}
