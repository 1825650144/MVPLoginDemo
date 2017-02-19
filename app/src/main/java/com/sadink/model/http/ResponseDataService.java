package com.sadink.model.http;

import com.sadink.model.bean.HttpResult;
import com.sadink.model.bean.Movie;
import com.sadink.model.bean.Weather;
import com.sadink.model.bean.Weather2;
import com.sadink.model.bean.Weather3;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dongdd on 2016/5/22 0022 19:32
 */
public interface ResponseDataService {

    public static  final String WEATHER_URL = "http://www.weather.com.cn/data/sk/101110101.html";
    public static  final String  WEATHER_URL_b  = "http://www.weather.com.cn/adat/sk/101010100.html";

    String WEATHER_HF_KEY = "29de2224e0d741da93d0f775e2301add";


    /**
     * 获取电影信息
     * @param start
     * @param count
     * @return
     */
    @GET("top250")
    Observable<HttpResult<List<Movie>>> getTopMovie(@Query("start") int start, @Query("start") int count);


    /**
     * 获取天气信息
     * @return
     * 在Retrofit 2.0中我们还可以在@Url里面定义完整的URL：这种情况下Base URL会被忽略。
     */
//           adat/sk/{cityId}.html
    @GET("data/sk/{cityId}.html")
    Observable<Weather2> getWeatherInfo(@Path("cityId") String cityId);


    @GET("data/sk/{cityId}.html")
    Call<Weather2> getWeather(@Path("cityId") String cityId);



    @GET("weather")
    Observable<Weather3> getWeatherHFInfo(@Query("cityid") String cityid, @Query("key") String key);

}
