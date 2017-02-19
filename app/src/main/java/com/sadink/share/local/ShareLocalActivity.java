package com.sadink.share.local;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sadink.R;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import com.sadink.mvp_rxjava_dagger2.logcat.LogInfo;
import com.socks.library.KLog;
import java.util.List;

/**
 * Created by dongdd on 2016/6/20 0020 08:57
 */
public class ShareLocalActivity extends BaseActivity {

    private String urlTaobao = "https://shop150354170.taobao.com/shop/view_shop.htm?tracelog=twddp&user_number_id=1043754513";

    @Bind(R.id.btn_share) Button btnShare;
    @Bind(R.id.btn_share_qq) Button btnShareQq;
    @Bind(R.id.btn_share_weixin) Button btnShareWeixin;
    @Bind(R.id.btn_share_sinaweibo) Button btnShareSinaweibo;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_local);
        ButterKnife.bind(this);

    }


    private void initData() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,urlTaobao);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "我是弹出框的标题"));
    }


    @OnClick({ R.id.btn_share, R.id.btn_share_qq, R.id.btn_share_weixin, R.id.btn_share_sinaweibo })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_share:
                initData();
                break;
            case R.id.btn_share_qq:
                shartToApp(1);
                break;
            case R.id.btn_share_weixin:
                shartToApp(2);
                break;
            case R.id.btn_share_sinaweibo:
                shartToApp(3);
                break;
        }
    }


    private void initShareIntent(String type) {
        boolean found = false;
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        //share.setType("image/jpeg");
        share.setType("text/plain");
        // gets the list of intents that can be loaded.
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(share, 0);

        if (!resInfo.isEmpty()) {
            for (ResolveInfo info : resInfo) {

                KLog.d(LogInfo.LOGCAT, info.activityInfo.applicationInfo.packageName.toString());

                if (info.activityInfo.packageName.toLowerCase().contains(type) || info.activityInfo.name.toLowerCase().contains(type)) {
                    share.putExtra(Intent.EXTRA_SUBJECT, "残墨e族");
                    share.putExtra(Intent.EXTRA_TEXT, urlTaobao);
                    //share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(myPath))); // Optional, just if you wanna share an image.
                    share.setPackage(info.activityInfo.packageName);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
            startActivity(Intent.createChooser(share, "我是弹出框的标题"));
        }
    }


    private final int ID_QQ = 1;
    private final int ID_WEIXIN = 2;
    private final int ID_SINAWEIBO = 3;
    private final int ID_QQWEIBO = 4;
    private final int ID_RENREN = 5;
    private final int ID_EVERNOTE = 6;

    private void shartToApp(Integer packageName) {

        switch (packageName) {
            //一些常用应用包名：
            case ID_QQWEIBO:
                initShareIntent("com.tencent.wblog");
                break;
            case ID_WEIXIN:
                initShareIntent("com.tencent.mm");
                break;
            case ID_EVERNOTE:
                initShareIntent("evernote");
                break;
            case ID_SINAWEIBO:
                initShareIntent("com.sina.weibo");
                break;
            case ID_RENREN:
                initShareIntent("renren");
                break;
            case ID_QQ:
                initShareIntent("com.tencent.mobileqq");
                break;
        }
    }

}
