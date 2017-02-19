package com.sadink.mvp_rxjava_dagger2.ui.login.model;

import android.content.Context;

import com.sadink.mvp_rxjava_dagger2.ui.login.model.bean.UserBean;
import com.sadink.mvp_rxjava_dagger2.ui.login.model.biz.UserBiz;
import com.sadink.mvp_rxjava_dagger2.ui.login.presenter.UserLoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dongdd on 2016/5/26 0026 11:01
 */
@Module
public class UserModel {

    private Context mContext;

    public UserModel(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public UserBean provideUserBean(){
        return new UserBean();
    }

    @Provides
    @Singleton
    public UserBiz provideUserBiz(){
        return new UserBiz();
    }

    @Provides
    @Singleton
    public UserLoginPresenter provideUserLoginPresenter(UserBiz userBiz){
        return new UserLoginPresenter(userBiz);
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }


}
