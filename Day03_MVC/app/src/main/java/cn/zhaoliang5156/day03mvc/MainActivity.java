package cn.zhaoliang5156.day03mvc;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import cn.zhaoliang5156.day03mvc.bean.User;
import cn.zhaoliang5156.day03mvc.model.UserModel;
import cn.zhaoliang5156.day03mvc.net.NetCallBack;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 11:26 AM
 * Description: 登录界面
 * <p>
 * phone:15169707777
 * pwd:123456
 */
public class MainActivity extends AppCompatActivity {

    /**
     * model层的引用
     */
    private UserModel model;

    /**
     * 界面控件
     */
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        model = new UserModel();
    }

    /**
     * 点击登录按钮调用
     *
     * @param view
     */
    public void login(View view) {
        // 1. 从界面读取用户名和密码 封装成User对象
        User user = inputToUser();
        // 2. 检测用户用户名和密码
        boolean isActiveUser = checkUser(user);
        // 3. 请求服务器login登录
        if (isActiveUser) {
            login(user);
        }
    }

    /**
     * 请求网络接口登录
     *
     * @param user
     */
    private void login(final User user) {
        model.login(user, new NetCallBack() {
            @Override
            public void onSuccess(String result) {
                MainActivity.this.onSuccess(result);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    /**
     * 登录成功
     */
    public void onSuccess(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 检测用户是否合格
     *
     * @param user
     * @return
     */
    private boolean checkUser(User user) {
        if (!TextUtils.isEmpty(user.getUsername()) && !TextUtils.isEmpty(user.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 把读取的信息封装成user
     *
     * @return
     */
    private User inputToUser() {
        User user = new User();
        user.setUsername(etUsername.getText().toString());
        user.setPassword(etPassword.getText().toString());
        return user;
    }
}
