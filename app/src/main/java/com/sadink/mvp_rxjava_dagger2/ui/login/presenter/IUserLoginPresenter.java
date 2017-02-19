package com.sadink.mvp_rxjava_dagger2.ui.login.presenter;

import com.sadink.mvp_rxjava_dagger2.base.BasePresenter;

/**
 * Created by dongdd on 2016/5/20 0020 11:19
 */
public interface IUserLoginPresenter<V> extends BasePresenter<V>{

    void login();

    void clear();
}
