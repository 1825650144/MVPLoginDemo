package com.sadink.presenter;

/**
 * Created by dongdd on 2016/5/20 0020 14:25
 */
public interface IWeatherPresenter<V> {

    void loadData();

    void attachView(V v);

    void detachView();
}
