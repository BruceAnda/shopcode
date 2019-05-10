package cn.zhaoliang5156.day03mvc.model;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import cn.zhaoliang5156.day03mvc.bean.User;
import cn.zhaoliang5156.day03mvc.net.HttpUtil;
import cn.zhaoliang5156.day03mvc.net.NetCallBack;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 2:11 PM
 * Description: Model层
 */
public class UserModel {

    private HttpUtil httpUtil;

    public UserModel() {
        httpUtil = HttpUtil.getInstance();
    }

    public void login(final User user, final NetCallBack netCallBack) {
        new Thread() {
            @Override
            public void run() {
                httpUtil.doHttpPostLogin("http://172.17.8.100/small/user/v1/login", user, netCallBack);
            }
        }.start();
    }
}
