package com.sadink.mvp_rxjava_dagger2.ui.weather.presenter;

import com.sadink.mvp_rxjava_dagger2.ui.weather.model.WeatherModel;
import com.sadink.mvp_rxjava_dagger2.ui.weather.view.WeatherActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dongdd on 2016/5/26 0026 14:53
 */
@Component(modules = {WeatherModel.class})
@Singleton
public interface WeatherComponent {

    WeatherPresenter weatherPresenter();

    void inject(WeatherActivity weatherActivity);
}
