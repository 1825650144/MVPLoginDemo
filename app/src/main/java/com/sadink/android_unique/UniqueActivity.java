package com.sadink.android_unique;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sadink.R;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import com.sadink.mvp_rxjava_dagger2.logcat.LogInfo;
import com.socks.library.KLog;
import java.util.UUID;

/**
 * Created by dongdd on 2016/6/21 0021 10:29
 */
public class UniqueActivity extends BaseActivity {

    @Bind(R.id.tv_DEVICE_ID) TextView tvDEVICEID;
    @Bind(R.id.tv_MAC_ADDRESS) TextView tvMACADDRESS;
    @Bind(R.id.tv_Sim_Serial_Number) TextView tvSimSerialNumber;
    @Bind(R.id.tv_ANDROID_ID) TextView tvANDROIDID;
    @Bind(R.id.tv_Serial_Number) TextView tvSerialNumber;
    @Bind(R.id.tv_Installtion_ID) TextView tvInstalltionID;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_unique);
        ButterKnife.bind(this);
        initData();
        logPrintf();
    }


    private void initData() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        tvDEVICEID.setText(tvDEVICEID.getText() + DEVICE_ID);


        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAdress = info.getMacAddress(); // 获取mac地址
        int ipAddress = info.getIpAddress();  // 获取ip地址
        String ip = intToIp(ipAddress);

        //getLocalMacAddressFromWifiInfo(this);
        tvMACADDRESS.setText(tvMACADDRESS.getText() + macAdress);



        TelephonyManager tmssn = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String SimSerialNumber = tmssn.getSimSerialNumber();
        tvSimSerialNumber.setText(tvSimSerialNumber.getText() + SimSerialNumber);




        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        tvANDROIDID.setText(tvANDROIDID.getText() + ANDROID_ID);


        String SerialNumber = android.os.Build.SERIAL;
        tvSerialNumber.setText(tvSerialNumber.getText() +  SerialNumber);


        final TelephonyManager tmuuid = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tmuuid.getDeviceId();
        tmSerial = "" + tmuuid.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(),((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        tvInstalltionID.setText(tvInstalltionID.getText() +uniqueId);
    }



    public void logPrintf(){
        KLog.d(LogInfo.LOGCAT,tvDEVICEID.getText().toString());
        KLog.d(LogInfo.LOGCAT,tvMACADDRESS.getText().toString());
        KLog.d(LogInfo.LOGCAT,tvSimSerialNumber.getText().toString());
        KLog.d(LogInfo.LOGCAT,tvANDROIDID.getText().toString());
        KLog.d(LogInfo.LOGCAT,tvSerialNumber.getText().toString());
        KLog.d(LogInfo.LOGCAT,tvInstalltionID.getText().toString());
    }




    //根据Wifi信息获取本地Mac
    public void getLocalMacAddressFromWifiInfo(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String macAdress = info.getMacAddress(); // 获取mac地址
        int ipAddress = info.getIpAddress();  // 获取ip地址
        String ip = intToIp(ipAddress);
    }


    public static String intToIp(int i) {
        return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "."
            + ((i >> 8) & 0xFF) + "." + (i & 0xFF);
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
