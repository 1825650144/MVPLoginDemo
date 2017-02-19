package com.sadink.mvp_rxjava_dagger2.ui.weather.model.biz;

import android.util.Log;

import com.sadink.mvp_rxjava_dagger2.ui.weather.model.bean.WeatherBean;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.http.HttpMethods;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by dongdd on 2016/5/26 0026 14:47
 */
public class WeatherBiz implements IWeatherBiz {

    private HttpMethods httpMethods;
    private WeatherBean weatherBean;
    private Subscriber<WeatherBean> weatherSubscriber;

    @Inject
    public WeatherBiz(HttpMethods httpMethods) {
        this.httpMethods = httpMethods;
    }

    @Override
    public void loadData(final OnLoadDataListener onLoadDataListener) {

        weatherSubscriber = new Subscriber<WeatherBean>(){
            @Override
            public void onCompleted() {
                Log.d("sadink","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                onLoadDataListener.loadFailued();
            }

            @Override
            public void onNext(WeatherBean weatherBean) {
                onLoadDataListener.loadSuccess(weatherBean);
            }
        };


        httpMethods.getWeatherInfo(weatherSubscriber,"101110101");
    }




    /**
     * 此接口作用：链接model层和presenter层
     */
    public  interface OnLoadDataListener{
        void loadSuccess(WeatherBean weatherBean);
        void loadFailued();
    }
}
