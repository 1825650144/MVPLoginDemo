package com.sadink.view;

/**
 * Created by dongdd on 2016/5/20 0020 09:09
 * 总结下，对于View的接口，去观察功能上的操作，然后考虑：

 该操作需要什么？（getUserName, getPassword）
 该操作的结果，对应的反馈？(toMainActivity, showFailedError)
 该操作过程中对应的友好的交互？(showLoading, hideLoading)
 */
public interface ILoginView {


    String getUserName();
    String getPassword();

    void clearUserName();
    void clearPassword();


    void showProgress();
    void hideProgress();

    void toMainActivity();

    void showFailedError();



}
