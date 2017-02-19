package com.sadink.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sadink.R;
import com.sadink.model.bean.EBEntitry;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by dongdd on 2016/6/3 0003 17:03
 */
public class MainActivityEBTrigger extends BaseActivity {

    @Bind(R.id.editText) EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eventbus_trgger);
        ButterKnife.bind(this);
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @OnClick({ R.id.post, R.id.button }) public void onClick(View view) {
        switch (view.getId()) {
            case R.id.post:
                System.out.println("发送参数：" + editText.getText().toString());
                EventBus.getDefault().post(new EBEntitry(editText.getText().toString()));
                EventBus.getDefault().post(editText.getText().toString()+"哈哈哈");
                break;
            case R.id.button:
                Intent it = new Intent(getApplicationContext(), MainActivityEBShow.class);
                startActivity(it);
                break;
        }
    }
}
