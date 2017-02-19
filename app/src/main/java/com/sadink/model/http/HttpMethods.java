package com.sadink.model.http;

import com.sadink.model.bean.HttpResult;
import com.sadink.model.bean.Movie;
import com.sadink.model.bean.Weather;
import com.sadink.model.bean.Weather2;
import com.sadink.model.bean.Weather3;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dongdd on 2016/5/22 0022 19:40
 */
public class HttpMethods {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    public static final String WEATHER_URL_HF = "https://api.heweather.com/x3/";

    public static final String WEATHER_URL = "http://www.weather.com.cn/";


    // 链接超时为5分钟
    private static final int DEFAULT_TIMEOUT = 5;


    private Retrofit retrofit;
    private ResponseDataService dataService;

    /**
     * 构造方法私有
     */
    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                //添加 json 转换器
                .addConverterFactory(GsonConverterFactory.create())
                //添加 RxJava 适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(WEATHER_URL)
                .build();
//
        dataService = retrofit.create(ResponseDataService.class);
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getTopMovie(Subscriber<List<Movie>> subscriber, int start, int count) {
        Observable observable = dataService.getTopMovie(start, count)
                .map(new HttpResultFun<List<Movie>>());

        toSubscribe(observable, subscriber);
    }


    /**
     * 获取天气信息
     */
    public Call<Weather2> getWeather(String cityId) {

        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(WEATHER_URL)
                .build();
        dataService = retrofit.create(ResponseDataService.class);
        Call<Weather2> call = dataService.getWeather(cityId);
        return call;
    }


    /**
     * 获取天气信息
     *
     * @param subscriber
     */
    public void getWeatherInfo(Subscriber<Weather2> subscriber,String cityId) {
        dataService = retrofit.create(ResponseDataService.class);
        Observable observable = dataService.getWeatherInfo(cityId);
        toSubscribe(observable, subscriber);
    }


    /**
     * 获取和风天气信息
     *
     * @param subscriber
     */
    public void getWeatherHFInfo(Subscriber<Weather3> subscriber, String cityId) {

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(WEATHER_URL_HF)
                .build();

        dataService = retrofit.create(ResponseDataService.class);

        Observable observable = dataService.getWeatherHFInfo(cityId, dataService.WEATHER_HF_KEY);
        toSubscribe(observable, subscriber);
    }


    /**
     * Observale被Observer订阅
     *
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {

        // 指定到io线程
        o.subscribeOn(Schedulers.io())//在非UI线程进行耗时操作
                .unsubscribeOn(Schedulers.io())
                // 指定到主线程
                .observeOn(AndroidSchedulers.mainThread())//在UI线程进行观察监听
                .subscribe(s);//开始订阅观察监听
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFun<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
//            if (httpResult.getCount() == 0) {
//                throw new ApiException(100);
//            }
            return httpResult.getSubjects();
        }
    }

}
