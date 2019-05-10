package cn.zhaoliang5156.day03mvcsimple.model;

import android.os.AsyncTask;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import cn.zhaoliang5156.day03mvcsimple.bean.User;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 2:56 PM
 * Description:
 */
public class UserModel {

    public void login(final User user, final ModelCallback callback) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                // 访问网络
                HttpURLConnection connection = null;
                try {
                    URL url1 = new URL(strings[0]);
                    connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    connection.setDoOutput(true);
                    String body = "phone=" + URLEncoder.encode(user.getUsername()) + "&pwd=" + URLEncoder.encode(user.getPassword());
                    connection.getOutputStream().write(body.getBytes());

                    // 判断是否请求成功
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // guava
                        return CharStreams.toString(new InputStreamReader(connection.getInputStream()));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                // 回来的字符串
                // 4. 读取服务器响应更新UI
                callback.onSuccess(s);
            }
        }.execute("http://172.17.8.100/small/user/v1/login");
    }

    public interface ModelCallback {
        void onSuccess(String result);
    }
}
