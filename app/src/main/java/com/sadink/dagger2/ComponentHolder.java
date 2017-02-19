package com.sadink.dagger2;

/**
 * Created by dongdd on 2016/5/26 0026 09:00
 */
public class ComponentHolder {
    private static AppComponent sAppComponent;

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static void setAppComponent(AppComponent sAppComponent) {
        ComponentHolder.sAppComponent = sAppComponent;
    }
}
