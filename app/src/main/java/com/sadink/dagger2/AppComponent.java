package com.sadink.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dongdd on 2016/5/26 0026 09:18
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    LoginPresenter loginPresenter();

    void inject(LoginActivity loginActivity);
}
