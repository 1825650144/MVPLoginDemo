package com.sadink.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sadink.R;
import com.sadink.model.bean.EBEntitry;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by dongdd on 2016/6/3 0003 17:03
 */
public class MainActivityEBShow extends BaseActivity {
    @Bind(R.id.textView) TextView textView;
    @Bind(R.id.btn_start) Button btnStart;
    @Bind(R.id.tv_str) TextView tvStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eventbus_show);
        ButterKnife.bind(this);
        // 注册订阅
        EventBus.getDefault().register(this);
    }


    /**
     * eventBus响应操作
     */
    @Subscribe
    public void onEventMainThread(EBEntitry ebEntitry) {
        System.out.println("onEventMainThread：" + ebEntitry.getMsg());
        textView.setText(ebEntitry.getMsg());
        //Toast.makeText(getApplicationContext(), "onEventMainThread：" + ebEntitry.getMsg(),Toast.LENGTH_SHORT).show();
    }


    @Subscribe
    public void onEventMainThread(String str) {
        System.out.println("onEventMainThread：" + str);
        tvStr.setText(str);
        //Toast.makeText(getApplicationContext(), "onEventMainThread：" + ebEntitry.getMsg(),Toast.LENGTH_SHORT).show();
    }


    @Subscribe
    public void onEvent(EBEntitry ebEntitry) {
        System.out.println("onEvent：" + ebEntitry.getMsg());
        textView.setText(ebEntitry.getMsg());
        //Toast.makeText(getApplicationContext(), "onEvent：" + ebEntitry.getMsg(), Toast.LENGTH_SHORT).show();
    }


    @Subscribe
    public void onEventBackground(EBEntitry ebEntitry) {
        System.out.println("onEventBackground：" + ebEntitry.getMsg());
        textView.setText(ebEntitry.getMsg());
        //Toast.makeText(getApplicationContext(), "onEventBackground：" + ebEntitry.getMsg(),Toast.LENGTH_SHORT).show();
    }


    @Subscribe
    public void onEventAsync(EBEntitry ebEntitry) {
        System.out.println("onEventAsync：" + ebEntitry.getMsg());
        textView.setText(ebEntitry.getMsg());
        //Toast.makeText(getApplicationContext(), "onEventAsync：" + ebEntitry.getMsg(),Toast.LENGTH_SHORT).show();
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        // 取消订阅
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.btn_start) public void onClick() {
        startActivity(new Intent(getApplicationContext(), MainActivityEBTrigger.class));
    }
}
