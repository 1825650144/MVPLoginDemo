package com.sadink;

import android.os.Bundle;

import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;

/**
 * Created by dongdd on 2016/6/3 0003 17:03
 */
public class ActivityText extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
    }
}
