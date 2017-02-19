package com.sadink.dagger2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by dongdd on 2016/5/26 0026 09:00
 *
 *
 * 这种用来生产Dependency的、用 @Provides修饰过的方法叫Provider方法。这里要注意第二个Provider方法 provideRetrofit(OkHttpClient okhttpClient)，
 * 这个方法有一个参数，是OkHttpClient。这是因为创建一个Retrofit对象需要一个OkHttpClient的对象，这里通过参数传递进来。
 * 这样做的好处是，当Client向管理员（Component）索要一个Retrofit的时候，Component会自动找到Module里面找到生产Retrofit的这个 provideRetrofit(OkHttpClient okhttpClient)方法，
 * 找到以后试图调用这个方法创建一个Retrofit对象，返回给Client。但是调用这个方法需要一个OkHttpClient，于是Component又会去找其他的provider方法，看看有没有哪个会生产OkHttpClient。
 * 于是就找到了上面的第一个provider方法： provideOkHttpClient()。
   找到以后，调用这个方法，创建一个OkHttpClient对象，再调用 provideRetrofit(OkHttpClient okhttpClient)方法，
   把刚刚创建的OkHttpClient对象传进去，创建出一个Retrofit对象，返回给Client。当然，如果最后找到的 provideOkHttpClient()方法也需要其他参数，
   那么管理员还会继续递归的找下去，直到所有的Dependency都被满足了，再一个一个创建Dependency，然后把最终Client需要的Dependency呈递给Client。
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.baidu.com")
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public UserApiService provideUserApiService(Retrofit retrofit) {
        return retrofit.create(UserApiService.class);
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Provides
    public UserManager provideUserManager(SharedPreferences sharedPreferences, UserApiService userApiService) {
        return new UserManager(sharedPreferences, userApiService);
    }


    @Provides
    public PasswordValidator providePasswordValidator() {
        new PasswordValidator();
        return null;
    }


    @Provides
    public LoginPresenter provideLoginPresenter(UserManager mUserManager, PasswordValidator mPasswordValidator) {
        return new LoginPresenter(mUserManager, mPasswordValidator);
    }


    @Provides
    public Context provideContext() {
        return mContext;
    }

}
