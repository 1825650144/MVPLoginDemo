package com.sadink.mvp_rxjava_dagger2.ui.weather.model.http;

import com.sadink.mvp_rxjava_dagger2.ui.weather.model.bean.WeatherBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by dongdd on 2016/5/26 0026 15:27
 */
public interface WeatherApiService {

    String url = "http://192.168.1.124:8080/ServiceHallService/imagePad/gradeOneMainPage";


    @GET("data/sk/{cityId}.html")
    Observable<WeatherBean> getWeatherInfo(@Path("cityId") String cityid);

    @GET(url)
    Observable<List<String>> getImageList();
}
