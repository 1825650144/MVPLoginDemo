package com.sadink.glide.posts;

import android.content.Context;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

/**
 * Created by Zech on 2015/11/1 Email: dfshizhiqiang@gmail.com
 * 继承BaseGlideUrlLoader实现根据不同屏幕尺寸加载不同网络地址的图片
 */
public class MyUrlLoader extends BaseGlideUrlLoader<MyDataModel> {

    public MyUrlLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(MyDataModel model, int width, int height) {
        return model.buidUrl(width, height);
    }
}
