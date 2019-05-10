package cn.zhaoliang5156.day03mvp.persenter;

import android.text.TextUtils;

import cn.zhaoliang5156.day03mvp.bean.User;
import cn.zhaoliang5156.day03mvp.model.IUserModel;
import cn.zhaoliang5156.day03mvp.model.UserModelImpl;
import cn.zhaoliang5156.day03mvp.view.UserView;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 3:10 PM
 * Description:
 */
public class UserPresenter {

    private IUserModel userModel;
  //  private MainActivity userView;  // v层 可以是Activity 也可以是Fragment  使用基类 可以抽象类 或接口 一般使用接口解决通用性的问题
    private UserView userView;

    public UserPresenter(UserView userView) {
        this.userView = userView;
        userModel = new UserModelImpl();
    }

    /**
     * 把读取的信息封装成user
     *
     * @param username
     * @param password
     * @return
     */
    public User inputToUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    /**
     * 检测用户是否合格
     *
     * @param user
     * @return
     */
    public boolean checkUser(User user) {
        if (!TextUtils.isEmpty(user.getUsername()) && !TextUtils.isEmpty(user.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 请求网络接口登录
     *
     * @param user
     */
    public void login(final User user) {
        userModel.login(user, new UserModelImpl.ModelCallback() {
            @Override
            public void onSuccess(String result) {
                // Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                userView.onSuccess(result);
            }
        });
    }

    /**
     * 释放资源 解决内存泄漏
     */
    public void detach() {
        if (userView != null) {
            userView = null;
        }
        if (userModel != null) {
            userModel = null;
        }
    }
}
