package com.sadink.model.biz;

import com.sadink.model.bean.Weather;

/**
 * Created by dongdd on 2016/5/20 0020 14:07
 */
public interface IWeatherBiz {

    /**
     * 加载天气信息
     * @param onListener
     */
    void loadData(WeatherBiz.OnLoadWeatherListener onListener);

}
