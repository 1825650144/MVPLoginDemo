package com.sadink.presenter;

import android.view.View;

/**
 * Created by dongdd on 2016/5/20 0020 11:19
 */
public interface IUserLoginPresenter<V> {

    void login();

    void clear();

    void attachView(V v);

    void detachView();
}
