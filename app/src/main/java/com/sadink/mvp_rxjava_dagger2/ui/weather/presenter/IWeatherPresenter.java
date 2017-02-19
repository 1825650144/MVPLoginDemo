package com.sadink.mvp_rxjava_dagger2.ui.weather.presenter;

import com.sadink.mvp_rxjava_dagger2.base.BasePresenter;

/**
 * Created by dongdd on 2016/5/26 0026 14:52
 */
public interface IWeatherPresenter<V> extends BasePresenter<V> {

    /**
     * 加载数据
     */
    void loadData();
}
