package com.bawei.zhaoliang.mvp;

import android.content.Context;
import android.content.Intent;

import com.bawei.zhaoliang.bean.User;
import com.bawei.zhaoliang.net.NetCallback;
import com.bawei.zhaoliang.ui.activity.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 4:04 PM
 * Description: 用户业务逻辑实现类
 */
public class UserPresenterImpl implements IUserContract.IUserPresenter {

    private IUserContract.IUserView view;
    private IUserContract.IUserModel model;

    @Override
    public void attach(IUserContract.IUserView view) {
        this.view = view;
        model = new UserModelImpl();
    }

    @Override
    public void detach() {
        if (view != null) {
            view = null;
        }
        if (model != null) {
            model = null;
        }
    }

    @Override
    public void regist(User user) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", user.getPhone());
        param.put("pwd", user.getPwd());
        String url = "http://172.17.8.100/small/user/v1/register";
        model.doHttpPost(url, param, new NetCallback() {
            @Override
            public void onSuccess(String result) {
                view.registSuccess(result);
            }

            @Override
            public void onFial(String msg) {
                view.registFail();
            }
        });
    }

    @Override
    public void login(User user) {
        Map<String, String> param = new HashMap<>();
        param.put("phone", user.getPhone());
        param.put("pwd", user.getPwd());
        String url = "http://172.17.8.100/small/user/v1/login";
        model.doHttpPost(url, param, new NetCallback() {
            @Override
            public void onSuccess(String result) {
                view.loginSuccess(result);
            }

            @Override
            public void onFial(String msg) {
                view.loginFail();
            }
        });
    }

    @Override
    public User inputToUser(String phone, String pwd) {
        User user = new User(phone, pwd);
        return user;
    }

    @Override
    public void toMain(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
