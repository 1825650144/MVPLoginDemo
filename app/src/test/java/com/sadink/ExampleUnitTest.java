package com.sadink;

import android.util.Log;
import com.sadink.model.bean.User;
import com.sadink.presenter.UserLoginPresenter;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("哈哈我在做单元测试!" + true);

//        Log.d("sadink","哈哈我在做单元测试!");
        // 比较两个值是否相等
        assertEquals(4, 2 + 2);
    }

    @Test
    public void login(){
        User mockUserManager = Mockito.mock(User.class);
        UserLoginPresenter loginPresenter = new UserLoginPresenter();
        Log.d("sadink","哈哈我在做单元测试!");

        loginPresenter.login("userName","密码");
    }
}