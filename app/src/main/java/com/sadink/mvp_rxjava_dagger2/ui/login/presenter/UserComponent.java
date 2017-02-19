package com.sadink.mvp_rxjava_dagger2.ui.login.presenter;

import com.sadink.mvp_rxjava_dagger2.ui.login.model.UserModel;
import com.sadink.mvp_rxjava_dagger2.ui.login.model.bean.UserBean;
import com.sadink.mvp_rxjava_dagger2.ui.login.view.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dongdd on 2016/5/26 0026 11:31
 */

@Component(modules = {UserModel.class})
@Singleton
public interface UserComponent {

    UserBean userbean();

    UserLoginPresenter userLoginPresenter();

    void inject(LoginActivity loginActivity);
}
