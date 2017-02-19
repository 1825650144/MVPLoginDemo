package com.sadink;

import android.app.Application;

/**
 * Created by dongdd on 2016/5/26 0026 09:21
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        ComponentHolder.setAppComponent(appComponent);
    }
}
