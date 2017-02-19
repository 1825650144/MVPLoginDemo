package com.sadink.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.sadink.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dongdd on 2016/5/26 0026 08:50
 * 参考：http://android.jobbole.com/83203/
 */
public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;

    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main_dagger2);

//      方式一：
//        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();  //
//        mLoginPresenter = appComponent.loginPresenter();   //

//        方式二:
//        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build(); //
//        appComponent.inject(this); //


//        ComponentHolder.getAppComponent().inject(this);


    }


    @OnClick(R.id.login)
    public void onClick() {
        mLoginPresenter.login(username.getText().toString(), password.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
