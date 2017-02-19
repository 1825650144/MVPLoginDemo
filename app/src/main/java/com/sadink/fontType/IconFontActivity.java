package com.sadink.fontType;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sadink.R;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;

/**
 * Created by dongdd on 2016/6/16 0016 15:31
 */
public class IconFontActivity extends BaseActivity {

    @Bind(R.id.tv_str) TextView tvStr;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iconfont);
        ButterKnife.bind(this);

        //        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf");
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont/iconfont3.ttf");
        tvStr.setTypeface(iconfont);
        tvStr.setTextSize(40);

    }
}
