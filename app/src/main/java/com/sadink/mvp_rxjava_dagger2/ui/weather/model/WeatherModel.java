package com.sadink.mvp_rxjava_dagger2.ui.weather.model;

import android.content.Context;

import com.sadink.mvp_rxjava_dagger2.ui.weather.model.biz.WeatherBiz;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.http.HttpMethods;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.http.WeatherApiService;
import com.sadink.mvp_rxjava_dagger2.ui.weather.presenter.WeatherPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dongdd on 2016/5/26 0026 14:44
 */
@Module
public class WeatherModel {
    public Context mContext;
    public static final String WEATHER_URL = "http://www.weather.com.cn/";


    public WeatherModel(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public WeatherApiService provideWeatherApiService(Retrofit retrofit){
        return retrofit.create(WeatherApiService.class);
    }


    @Provides
    @Singleton
    public HttpMethods provideHttpMethods(WeatherApiService weatherApiService){
        return new HttpMethods(weatherApiService);
    }

    @Provides
    @Singleton
    public WeatherBiz provideWeatherBiz(HttpMethods httpMethods){
        return new WeatherBiz(httpMethods);
    }

    @Provides
    @Singleton
    public WeatherPresenter provideWeatherPresenter(WeatherBiz weatherBiz){
        return new WeatherPresenter(weatherBiz);
    }












    @Provides
    public Context provideContext(){
        return mContext;
    }
}
