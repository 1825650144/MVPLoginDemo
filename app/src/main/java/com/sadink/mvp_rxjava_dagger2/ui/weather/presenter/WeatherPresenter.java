package com.sadink.mvp_rxjava_dagger2.ui.weather.presenter;

import com.sadink.mvp_rxjava_dagger2.ui.weather.model.bean.WeatherBean;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.biz.IWeatherBiz;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.biz.WeatherBiz;
import com.sadink.mvp_rxjava_dagger2.ui.weather.view.IWeatherView;

import javax.inject.Inject;

/**
 * Created by dongdd on 2016/5/26 0026 14:53
 */
public class WeatherPresenter implements IWeatherPresenter<IWeatherView>, WeatherBiz.OnLoadDataListener {

    private IWeatherView weatherView;
    private IWeatherBiz weatherBiz;


    @Inject
    public WeatherPresenter(WeatherBiz weatherBiz) {
        this.weatherBiz = weatherBiz;
    }

    @Override
    public void loadData() {
        weatherView.showProgress();
        weatherBiz.loadData(this);
    }

    @Override
    public void attachView(IWeatherView iWeatherView) {
        weatherView = iWeatherView;
    }

    @Override
    public void detachView() {
        weatherView = null;
    }

    @Override
    public void loadSuccess(WeatherBean weatherBean) {
        weatherView.hideProgress();
        weatherView.showData(weatherBean);
    }

    @Override
    public void loadFailued() {
        weatherView.hideProgress();
        weatherView.clearData();
        weatherView.showFailedError();
    }
}
