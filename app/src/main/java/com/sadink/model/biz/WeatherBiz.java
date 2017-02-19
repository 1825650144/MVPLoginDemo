package com.sadink.model.biz;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.sadink.model.bean.Movie;
import com.sadink.model.bean.Weather;
import com.sadink.model.bean.Weather2;
import com.sadink.model.bean.Weather3;
import com.sadink.model.http.ResponseDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by dongdd on 2016/5/20 0020 14:10
 */
public class WeatherBiz implements IWeatherBiz {

    //    private String url = "http://www.weather.com.cn/adat/sk/101010100.html";
    private String url = "http://www.weather.com.cn/data/sk/101110101.html";
    private Weather weather;
    private Subscriber<List<Movie>> getTopMovieOnNext;
    private Subscriber<Weather3> getWeatherHF;
    private Subscriber<Weather2> getWeather;
    private WeatherBiz.OnLoadWeatherListener onListener;
    private Retrofit mRetrofit;

    public static final String WEATHER_URL = "http://www.weather.com.cn/";


    @Override
    public void loadData(final WeatherBiz.OnLoadWeatherListener onListener) {


        /**
         * 定义观察者
         */
      /*  getTopMovieOnNext = new Subscriber<List<Movie>>() {
            @Override
            public void onCompleted() {
            }

            *//**
         * 失败处理
         * @param e
         *//*
            @Override
            public void onError(Throwable e) {
                onListener.loadFailer();
            }

            *//**
         * 成功处理
         * @param movies
         *//*
            @Override
            public void onNext(List<Movie> movies) {
                Log.d("sadink", "标题：" + movies.get(0).getTitle());
                weather = new Weather();
                weather.setCity("city");
                weather.setWd("WD");
                weather.setWs("WS");
                weather.setTime(movies.get(0).getTitle());
                onListener.loadSuccess(weather);
            }
        };*/


        /**
         * 获取天气信息---观察者
         */
      /*  getWeatherHF = new Subscriber<Weather3>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                onListener.loadFailer();
            }

            @Override
            public void onNext(Weather3 weather3s) {
                Log.d("sadink", "城市：" + weather3s.basic.city);
                weather = new Weather();
                weather.setCity(weather3s.basic.city);
                weather.setWd(weather3s.now.wind.sc);
                weather.setWs(weather3s.now.wind.dir);
                weather.setTime(weather3s.basic.update.loc);
                onListener.loadSuccess(weather);
            }
        };*/


//        getWeather = new Subscriber<Weather2>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                onListener.loadFailer();
//            }
//
//            @Override
//            public void onNext(Weather2 weather2) {
//                Log.d("sadink", "城市：" + weather2.getWeatherinfo().getCity());
//                Log.d("sadink", "天气：" + weather2.getWeatherinfo().toString());
//
//                weather = new Weather();
//                weather.setCity(weather2.getWeatherinfo().getCity());
//                weather.setWd(weather2.getWeatherinfo().getWD());
//                weather.setWs(weather2.getWeatherinfo().getWS());
//                weather.setTime(weather2.getWeatherinfo().getTime());
//                onListener.loadSuccess(weather);
//            }
//        };



        /*getWeather = new Subscriber<Weather2>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                onListener.loadFailer();
            }

            @Override
            public void onNext(Weather2 weather2) {
                Log.d("sadink", "城市：" + weather2.weatherinfo.city);
                Log.d("sadink", "天气：" + weather2.weatherinfo.toString());

                weather = new Weather();
                weather.setCity(weather2.weatherinfo.city);
                weather.setWd(weather2.weatherinfo.WD);
                weather.setWs(weather2.weatherinfo.WS);
                weather.setTime(weather2.weatherinfo.time);
                onListener.loadSuccess(weather);
            }
        };
*/

        /**
         * 进行网络请求
         */
        // 获取电影信息
//        HttpMethods.getInstance().getTopMovie(getTopMovieOnNext, 0, 10);

        // 获取和风天气信息
//        HttpMethods.getInstance().getWeatherInfo(getWeather,"CN101110101");


//        HttpMethods.getInstance().getWeatherInfo(getWeather, "101110101");


      /*  AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject weatherinfo = response.getJSONObject("weatherinfo");
                    weather = new Weather();
                    weather.setCity(weatherinfo.getString("city"));
                    weather.setWd(weatherinfo.getString("WD"));
                    weather.setWs(weatherinfo.getString("WS"));
                    weather.setTime(weatherinfo.getString("time"));
                    onListener.loadSuccess(weather);
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    onListener.loadSuccess(weather);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("sadink","responseString: " + responseString);
                Log.d("sadink","statusCode: " + statusCode + throwable.getMessage());
                onListener.loadFailer();
            }
        });*/


        /**
         * 同步请求
         */
        this.onListener = onListener;
//       // 开启一个子线程，进行网络操作，等待有返回结果，使用handler通知UI
//        new Thread(networkTask).start();

        /**
         * 异步请求
         */
//        Call<ResponseBody> call = HttpMethods.getInstance().getWeather("101110101");
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
////                    weather = new Gson().fromJson(response.body().toString(),Weather.class);
//                    Log.d("sadink", "response=" + response.body().string());
//                    onListener.loadSuccess(new Weather(response.body().string()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.d("sadink", "Exception = " + e.getMessage());
//                    onListener.loadFailer();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("sadink", "onFailure=" + t.getMessage());
//            }
//
//        });
//        // 移除对象
//        call.cancel();

        getCarType();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            // TODO
            // UI界面的更新等相关操作
            switch (msg.what) {
                case 1:
                    onListener.loadSuccess(new Weather(val));
                    break;
                case 2:
                    onListener.loadFailer();
                    break;
            }
        }
    };

    /**
     * 网络操作相关的子线程
     */
  /*  Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();

            Call<ResponseBody> call = HttpMethods.getInstance().getWeather("101110101");
            try {
                Response<ResponseBody> bodyResponse = call.execute();
                String body = bodyResponse.body().string();//获取返回体的字符串
                Log.d("sadink", "body=" + body);
                msg.what = 1;
                data.putString("value", body);
            } catch (Exception e) {
                Log.d("sadink", "Exception = " + e.getMessage());
                msg.what = 2;
                e.printStackTrace();
            } finally {
                call.cancel();
            }
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };*/


    private void getCarType() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ResponseDataService dataService = mRetrofit.create(ResponseDataService.class);

        Call<Weather2> call = dataService.getWeather("101110101");

        call.enqueue(new Callback<Weather2>() {
            @Override
            public void onResponse(Call<Weather2> call, Response<Weather2> response) {
                try {
                    Log.d("sadink", "json:" + response.body().weatherinfo.toString());
                    Weather2 weather = new Gson().fromJson(response.body().getWeatherinfo().toString(),Weather2.class);
                    Log.d("sadink", "response=" + response.toString());
                    Log.d("sadink", "weather=" + response.toString());



                    onListener.loadSuccess(new Weather(response.body().weatherinfo.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("sadink", "Exception = " + e.getMessage());
                    onListener.loadFailer();
                }
            }

            @Override
            public void onFailure(Call<Weather2> call, Throwable t) {
                Log.d("sadink", "onFailure=" + t.getMessage());
            }
        });
    }


    /**
     * 此接口用来链接mode层和presenter层
     */
    public interface OnLoadWeatherListener {

        void loadSuccess(Weather weather);

        void loadFailer();
    }
}
