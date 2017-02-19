package com.sadink.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.lidroid.xutils.HttpUtils;
import com.sadink.R;
import com.sadink.glide.posts.MyDataModel;
import com.sadink.glide.posts.MyUrlLoader;
import com.sadink.glide.utils.Constants;
import com.sadink.mvp_rxjava_dagger2.logcat.LogInfo;
import com.socks.library.KLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dongdd on 2016/5/30 0030 09:13
 */
public class GlideMainActivity extends AppCompatActivity {

    private String url1 = "http://img0.ph.126.net/SSM85Ibp3g8hOTa2tkjnBw==/6631268976328190694.jpg";
    private String url2 = "http://img1.ph.126.net/tAn2tp-lzI1waAzc1NiN-g==/6631224995863078981.jpg";

    @Bind(R.id.btn_intent)
    Button btnIntent;
    @Bind(R.id.btn_uri)
    Button btnUri;
    @Bind(R.id.btn_file)
    Button btnFile;
    @Bind(R.id.btn_res)
    Button btnRes;
    @Bind(R.id.btn_custom)
    Button btnCustom;
    @Bind(R.id.btn_gallery)
    Button btnGallery;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.current_source)
    TextView currentSource;

    private RequestManager requestManager;
    private String imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        requestManager = Glide.with(this);
    }

    HttpUtils http = new HttpUtils();

    private void requestHttp() {
        String url = "http://192.168.1.124:8080/ServiceHallService/imagePad/gradeOneMainPage";

//        http.send(HttpRequest.HttpMethod.GET,
//                url,
//                new RequestCallBack<String>() {
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo) {
//                        Type listType = new TypeToken<LinkedList<String>>() {
//                        }.getType();
//                        Gson gson = new Gson();
//                        LinkedList<String> users = gson.fromJson(responseInfo.toString(), listType);
//
//
//                        imageUrl = users.get(users.size() - 1);
//                        KLog.d(LogInfo.LOGCAT, "usrl: " + imageUrl);
////                        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
////                            String user = (String) iterator.next();
////
////                        }
//                    }
//
//                    @Override
//                    public void onFailure(HttpException e, String s) {
//                        imageUrl = "";
//                    }
//                });
    }


    @OnClick({R.id.btn_intent, R.id.btn_uri, R.id.btn_file, R.id.btn_res, R.id.btn_custom, R.id.btn_gallery})
    public void onClick(View view) {
        switch (view.getId()) {
            //  从网络加载图片
            case R.id.btn_intent:
//                requestHttp();
//                String url = "http://192.168.1.121:8080/sh/resource/imagePad/app001.png";
                String url = "http://192.168.1.124:8080/ServiceHallService/" + imageUrl;
//                String url = "http://b.hiphotos.baidu.com/zhidao/pic/item/0ff41bd5ad6eddc4f086b35a3ddbb6fd52663329.jpg";
                requestManager
                        .load(url)
                        .bitmapTransform(new CropCircleTransformation(this))
                        .into(imageView);
                currentSource.setText("From Intent");
                break;
            // 从Drawable/mipmap加载图片
            case R.id.btn_uri:
//                requestManager.load(Tools.resourceIdToUri(this, R.drawable.glide_logo)).into(imageView);
                requestManager.load(R.drawable.glide_logo).into(imageView);
                currentSource.setText("From Uri");
                break;

            // 从assets中加载图片
            case R.id.btn_file:
                copyFile();
                requestManager.load(new File(getFilesDir(), Constants.IMG_GUN)).into(imageView);
                currentSource.setText("From File");
                break;

            case R.id.btn_res:
                requestManager.load(R.mipmap.fire_man).into(imageView);
                currentSource.setText("From Res");
                break;
            // 根据ImageView容器的的尺寸加载不同地址的图片
            case R.id.btn_custom:
                requestManager.using(new MyUrlLoader(this)).load(new MyDataModel() {//用两张不同的图片模拟不同尺寸大小
                    @Override
                    public String buidUrl(int width, int height) {
                        KLog.d(LogInfo.LOGCAT, "width: " + width);
                        KLog.d(LogInfo.LOGCAT, "width: " + height);
                        if (width >= 600) {//在1080p的手机上看到url1
                            return url1;
                        } else {//在720p的手机上看到url2
                            return url2;
                        }
                    }
                }).into(imageView);
                currentSource.setText("From Custom Source");
                break;
            case R.id.btn_gallery:
                break;
        }
    }

    private void copyFile() {
        InputStream is;
        try {
            is = getAssets().open(Constants.IMG_GUN);
            FileOutputStream fos = new FileOutputStream(new File(getFilesDir(), Constants.IMG_GUN));
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
