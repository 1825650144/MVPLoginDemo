package com.sadink.mvp_rxjava_dagger2.ui.weather.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sadink.R;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.WeatherModel;
import com.sadink.mvp_rxjava_dagger2.ui.weather.model.bean.WeatherBean;
import com.sadink.mvp_rxjava_dagger2.ui.weather.presenter.DaggerWeatherComponent;
import com.sadink.mvp_rxjava_dagger2.ui.weather.presenter.WeatherComponent;
import com.sadink.mvp_rxjava_dagger2.ui.weather.presenter.WeatherPresenter;
import com.sadink.utils.CacheUtil;
import javax.inject.Inject;

/**
 * 获取天气视图
 */
public class WeatherActivity extends BaseActivity implements IWeatherView {

    @Bind(R.id.tv_weather)
    TextView tvWeather;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    WeatherPresenter weatherPresenter;
    @Bind(R.id.tv_cache) TextView tvCache;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        // Dagger2注册
        WeatherComponent weatherComponent = DaggerWeatherComponent.builder()
            .weatherModel(new WeatherModel(getApplicationContext()))
            .build();
        weatherComponent.inject(this);

        // 注解注册
        ButterKnife.bind(this);

        weatherPresenter.attachView(this);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                weatherPresenter.loadData();
            }
        }, 0);


        initView();
    }


    private void initView() {
        showCacheSize();
    }

    private void showCacheSize(){
        tvCache.setText("缓存大小: " + CacheUtil.getCacheSize(getCacheDir()));
    }


    @Override
    public void showData(WeatherBean weather) {
        tvWeather.setText(weather.getWeatherinfo().toString());
    }


    @Override
    public void clearData() {
        tvWeather = null;
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void showFailedError() {
        showToast("加载天气信息有误!");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        weatherPresenter.detachView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.tv_cache) public void onClick() {
        CacheUtil.deleteDir(getCacheDir());
        showCacheSize();
    }


    @Override protected void onResume() {
        super.onResume();
        showCacheSize();
    }
}
