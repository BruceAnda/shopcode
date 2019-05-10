package cn.zhaoliang5156.day03mvc.net;

import android.os.Handler;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cn.zhaoliang5156.day03mvc.bean.User;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 2:20 PM
 * Description: 使用单例模式封装网络工具类
 */
public class HttpUtil {

    private static final HttpUtil ourInstance = new HttpUtil();

    private Handler mHandler;

    public static HttpUtil getInstance() {
        return ourInstance;
    }

    private HttpUtil() {
        mHandler = new Handler();
    }

    /**
     * Http post 登录
     */
    public void doHttpPostLogin(String url, User user, final NetCallBack callBack) {
        // 访问网络
        HttpURLConnection connection = null;
        try {
            URL url1 = new URL(url);
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            connection.setDoOutput(true);
            String body = "phone=" + URLEncoder.encode(user.getUsername()) + "&pwd=" + URLEncoder.encode(user.getPassword());
            connection.getOutputStream().write(body.getBytes());

            // 判断是否请求成功
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                final InputStream inputStream = connection.getInputStream();
                // guava
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callBack.onSuccess(CharStreams.toString(new InputStreamReader(inputStream)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }


        } catch (final Exception e) {
            e.printStackTrace();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onError(e.getMessage());
                }
            });
        }
    }


}
