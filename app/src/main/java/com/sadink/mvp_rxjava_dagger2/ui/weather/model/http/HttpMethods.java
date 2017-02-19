package com.sadink.mvp_rxjava_dagger2.ui.weather.model.http;

import com.sadink.mvp_rxjava_dagger2.ui.weather.model.bean.HttpResult;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.bean.WeatherBean;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dongdd on 2016/5/26 0026 15:33
 */
public class HttpMethods {

    private WeatherApiService weatherApiService;

    @Inject
    public HttpMethods(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }




    /**
     * 获取和风天气信息
     * @param subscriber
     * @param cityid
     */
    public void getWeatherInfo(Subscriber<WeatherBean> subscriber, String cityid){
        Observable observable =  weatherApiService.getWeatherInfo(cityid);
        toSubscriber(observable,subscriber);
    }


    /**
     * 获取图片信息
     * @param subscriber
     */
    public void getImageInfo(Subscriber<List<String>> subscriber){
        Observable observable = weatherApiService.getImageList();
        toSubscriber(observable,subscriber);
    }

    /**
     * 观察者订阅被观察者
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscriber(Observable<T> o,Subscriber<T> s){
        o.subscribeOn(Schedulers.io()) //指定到io线程
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // 指定到主线程
                .subscribe(s); //开始订阅观察监听
    }


    private class  HttpResultFun<T> implements Func1<HttpResult<T>,T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
//            if(tHttpResult.getCount() == 0){
//                throw new ApiException(100);
//            }
            return tHttpResult.getSubjects();
        }
    }

}
