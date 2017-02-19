package com.sadink.mvp_rxjava_dagger2.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sadink.BuildConfig;
import com.sadink.utils.ToastUtils;
import com.socks.library.KLog;

/**
 * Created by dongdd on 2016/5/20 0020 10:41
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.init(BuildConfig.DEBUG);
    }

    public void showToast(String Msg) {
        ToastUtils.showToast(getApplicationContext(),Msg);
    }

}
