package com.sadink.swipeToLoadLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sadink.R;
import com.sadink.mvp_rxjava_dagger2.base.BaseActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongdd on 2016/6/30 0030 09:34
 */
public class HistoryFragment extends BaseActivity {

    @Bind(R.id.swipe_target) RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout) SwipeToLoadLayout swipeToLoadLayout;
    private SVProgressHUD mSVProgressHUD;
    private List<String> strList;
    private RecycleHistoryAdapter recyclePicAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_time);
        ButterKnife.bind(this);
        initDialog();

        initData();
    }


    private void initData() {

        // 初始化控件
        initRefresh();

        strList = new ArrayList<>();

        recyclePicAdapter = new RecycleHistoryAdapter(getApplicationContext(),strList);
        swipeTarget.setAdapter(recyclePicAdapter);
        //点击事件
        recyclePicAdapter.setOnItemClickLitener(new RecycleHistoryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //IntentUtils.startDayShowActivity(this, historyList.get(position));
            }
        });



        // 设置上拉加载监听
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override public void onLoadMore() {
                for (int i = 0; i < 2; i++) {
                    strList.add("加载更多获得的数据" + i);
                }
                recyclePicAdapter.notifyDataSetChanged();
                //设置上拉加载更多结束
                swipeToLoadLayout.setLoadingMore(false);
            }
        });

        // 设置下拉刷新监听
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override public void onRefresh() {
                strList.clear();
                for (int i = 0; i < 1; i++) {
                    strList.add(0,"刷新获得的数据"+i);
                }

                recyclePicAdapter.notifyDataSetChanged();
                //设置下拉刷新结束
                swipeToLoadLayout.setRefreshing(false);
            }
        });
    }

    //
    @Override protected void onStart() {
        super.onStart();
        showProgressDialog();
        //自动刷新
        swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                strList.add("哈哈");
                strList.add("嘻嘻");
                strList.add("呵呵");
                strList.add("哦哦");
                recyclePicAdapter.notifyDataSetChanged();
                dissmissProgressDialog();
            }
        },5000);
    }

    private void initRefresh() {
        // 是否运行下拉刷新
        swipeToLoadLayout.setRefreshEnabled(true);
        // 是否运行上拉刷新
        swipeToLoadLayout.setLoadMoreEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        swipeTarget.setLayoutManager(linearLayoutManager);
        swipeTarget.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        swipeTarget.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.LTGRAY).build());
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    private void initDialog() {
        mSVProgressHUD = new SVProgressHUD(this);
    }


    public void showProgressDialog() {
        dissmissProgressDialog();
        mSVProgressHUD.showWithStatus("加载中...", SVProgressHUD.SVProgressHUDMaskType.Black);
    }

    public void dissmissProgressDialog() {
        if (mSVProgressHUD.isShowing()) {
            mSVProgressHUD.dismiss();
        }
    }
}
