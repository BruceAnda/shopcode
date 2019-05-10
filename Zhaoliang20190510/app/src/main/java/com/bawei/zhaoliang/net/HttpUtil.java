package com.bawei.zhaoliang.net;

import android.os.Handler;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 4:24 PM
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
     * post 请求数据
     */
    public void doHttpPost(final String url, final Map<String, String> param, final NetCallback callback) {
        new Thread() {
            @Override
            public void run() {
                // 请求网络
                HttpURLConnection connection = null;
                try {
                    URL url1 = new URL(url);
                    connection = (HttpURLConnection) url1.openConnection();
                    // connection 的一些配置
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    // 向服务器写入数据
                    String body = paramToString(param);

                    connection.setDoOutput(true);
                    connection.getOutputStream().write(body.getBytes());

                    if (connection.getResponseCode() == 200) {
                        final String result = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onSuccess(result);

                            }
                        });
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFial(e.getMessage());
                        }
                    });
                }
            }
        }.start();
    }

    /**
     * 把param 转成成 字符串
     * @param param
     * @return
     */
    private String paramToString(Map<String, String> param) {
        StringBuilder stringBuilder = new StringBuilder();
        // 变量map
        for (Map.Entry<String, String> entry : param.entrySet()) {
            // 把Map Entry的键和值封装成参数
            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue()));
            stringBuilder.append("&");
        }
        return stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
    }
}
