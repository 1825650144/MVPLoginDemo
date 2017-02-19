package com.sadink.sendSMS;

import android.os.Bundle;
import android.telephony.SmsManager;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import java.util.List;

/**
 * Created by dongdd on 2016/6/23 0023 09:29
 */
public class SMSActivity extends BaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    // 发送短信
    public void sendMsg(){
        String content = "123";
        SmsManager smsManager = SmsManager.getDefault();
        List<String> divideContents = smsManager.divideMessage(content);
        for (String text : divideContents) {
            smsManager.sendTextMessage("10086", null, text, null, null);
        }
    }


}
