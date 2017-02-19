package com.sadink.presenter;

import android.util.Log;

import com.sadink.model.bean.Weather;
import com.sadink.model.biz.IWeatherBiz;
import com.sadink.model.biz.WeatherBiz;
import com.sadink.view.IWeatherView;

/**
 * Created by dongdd on 2016/5/20 0020 14:26
 */
public class WeatherPresenter implements IWeatherPresenter<IWeatherView>,WeatherBiz.OnLoadWeatherListener{

    private IWeatherView weatherView;
    private IWeatherBiz weatherBiz;

    public WeatherPresenter(IWeatherView weatherView) {
        this.weatherView = weatherView;
        weatherBiz = new WeatherBiz();
    }

    @Override
    public void loadData() {
        weatherBiz.loadData(this);
    }

    @Override
    public void attachView(IWeatherView iWeatherView) {
        this.weatherView = iWeatherView;
    }

    @Override
    public void detachView() {
        weatherView = null;
    }

    @Override
    public void loadSuccess(Weather weather) {
        Log.d("sadink","数据presenter： " + weather.getCity());
        weatherView.hideProgress();
        weatherView.showData(weather);

    }

    @Override
    public void loadFailer() {
        Log.d("sadink","数据presenter： failer");
        weatherView.hideProgress();
        weatherView.showFailedError();
    }
}
