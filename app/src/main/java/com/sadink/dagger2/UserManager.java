package com.sadink.dagger2;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by dongdd on 2016/5/26 0026 08:58
 */
public class UserManager {
    private final SharedPreferences mPref;
    private final UserApiService mRestAdapter;

    @Inject
    public UserManager(SharedPreferences mPref, UserApiService mRestAdapter) {
        this.mPref = mPref;
        this.mRestAdapter = mRestAdapter;
    }


    public void performLogin(String username,String password){

    }
}
