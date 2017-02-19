package com.sadink.mvp_rxjava_dagger2.base;

/**
 * Created by dongdd on 2016/5/26 0026 14:56
 */
public interface BasePresenter<V> {

    /**
     * 初始化视图层
     * @param v
     */
    void attachView(V v);


    /**
     * 分离视图层
     */
    void detachView();
}
