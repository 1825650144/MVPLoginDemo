package com.sadink.sendSMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.sadink.mvp_rxjava_dagger2.logcat.LogInfo;
import com.socks.library.KLog;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dongdd on 2016/6/23 0023 09:40
 */
public class InterceptReceiver extends BroadcastReceiver {

    public static final String TAG = "InterceptReceiver";
    public static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";


    public InterceptReceiver() {}


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "---------------InterceptReceiver onReceive()----------------");

        if (ACTION_SMS_RECEIVED.equals(intent.getAction())) {
            Bundle carryContent = intent.getExtras();
            if (carryContent != null) {
                StringBuilder sb = new StringBuilder();

                // 通过pdus获取接收到的所有短信息，获取短信内容
                Object[] pdus = (Object[]) carryContent.get("pdus");
                // 构建短信对象数组
                SmsMessage[] mges = new SmsMessage[pdus.length];
                for (int i = 0, len = pdus.length; i < len; i++) {
                    // 获取单条短信内容，以pdu格式存，并生成短信对象
                    mges[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                for (SmsMessage mge : mges) {
                    sb.append("短信来自：").append(mge.getDisplayOriginatingAddress()).append("\n").append("短信内容：").append(mge.getMessageBody()).append("\n");

                    Date sendDate = new Date(mge.getTimestampMillis());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    sb.append("短信发送时间：").append(format.format(sendDate));
                }
                KLog.d(LogInfo.LOGCAT,sb.toString());// 打印日志记录
                Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
                this.abortBroadcast(); // 不再往下传播
            }
        }
    }
}
