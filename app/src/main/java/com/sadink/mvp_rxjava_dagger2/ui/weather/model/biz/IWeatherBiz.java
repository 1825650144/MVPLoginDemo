package com.sadink.mvp_rxjava_dagger2.ui.weather.model.biz;

/**
 * Created by dongdd on 2016/5/26 0026 14:46
 */
public interface IWeatherBiz {
    /**
     * 加载数据
     * @param onLoadDataListener
     */
    void loadData(WeatherBiz.OnLoadDataListener onLoadDataListener);
}
