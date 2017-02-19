package com.sadink.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sadink.R;
import com.sadink.model.bean.Weather;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import com.sadink.presenter.IWeatherPresenter;
import com.sadink.presenter.WeatherPresenter;

public class WeatherActivity extends BaseActivity implements IWeatherView {

    private TextView textView;
    private ProgressBar progressBar;
    private IWeatherPresenter weatherPresenter;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.tv_weather);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        weatherPresenter = new WeatherPresenter(this);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                weatherPresenter.loadData();
            }
        }, 0);
    }

    @Override
    public void showData(Weather weather) {
        String data = "\n城市：" + weather.getCity() +
                "\n风向：" + weather.getWd() +
                "\n风力：" + weather.getWs() +
                "\n发布时间：" + weather.getTime();
        textView.setText(data);
    }

    @Override
    public void clearData() {
        textView = null;
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
    }
}
