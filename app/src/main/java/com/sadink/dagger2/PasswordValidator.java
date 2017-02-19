package com.sadink.dagger2;

import javax.inject.Inject;

/**
 * Created by dongdd on 2016/5/26 0026 09:14
 */
public class PasswordValidator {

    @Inject
    public PasswordValidator() { }

    public boolean verifyPassword(String password) {
        //假设这个方法需要联网
        return "123456".equals(password);
    }
}
