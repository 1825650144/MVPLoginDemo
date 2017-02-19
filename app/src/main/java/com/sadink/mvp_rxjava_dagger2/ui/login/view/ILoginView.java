package com.sadink.mvp_rxjava_dagger2.ui.login.view;

import com.sadink.mvp_rxjava_dagger2.ui.login.model.bean.UserBean;

/**
 * Created by dongdd on 2016/5/26 0026 10:45
 */
public interface ILoginView {

    UserBean getUserBean();

    void clearUserBean();

    void showProgress();
    void hideProgress();

    void toMainActivity();

    void showFailedError();

}
