package com.bawei.zhaoliang.mvp;

import android.content.Context;

import com.bawei.zhaoliang.bean.User;
import com.bawei.zhaoliang.net.NetCallback;

import java.util.Map;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 3:58 PM
 * Description:  用户契约 M V P 统一管理 我们的MVP接口
 */
public interface IUserContract {

    /**
     * 用户V 接口
     */
    public interface IUserView {
        /**
         * 注册成功
         */
        void registSuccess(String result);

        /**
         * 注册失败
         */
        void registFail();

        /**
         * 登录成功
         */
        void loginSuccess(String result);

        /**
         * 登录失败
         */
        void loginFail();
    }

    /**
     * 用户M 接口
     */
    public interface IUserModel {
        /**
         * 和网络交互 让参数通用一些 怎么办呢？ 我需要有个键有个值 MAP
         */
        void doHttpPost(String url, Map<String, String> param, NetCallback callback);
    }

    /**
     * 用户P 接口
     * 业务：
     * 1. 注册
     * 2. 登录
     */
    public interface IUserPresenter {

        /**
         * 绑定view
         */
        void attach(IUserView view);

        /**
         * 解绑 释放内存
         */
        void detach();

        /**
         * 注册业务逻辑
         */
        void regist(User user);

        /**
         * 登录业务逻辑
         */
        void login(User user);

        /**
         * 把电话和密码 封装成User
         *
         * @param phone
         * @param pwd
         * @return
         */
        User inputToUser(String phone, String pwd);

        /**
         * 跳转到主界面
         */
        void toMain(Context context);
    }
}
