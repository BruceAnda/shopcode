package cn.zhaoliang5156.day03normal;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import cn.zhaoliang5156.day03normal.bean.User;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 11:26 AM
 * Description: 登录界面
 *
 * phone:15169707777
 * pwd:123456
 */
public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
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
            //hello
            // hello 2
        }
    }

    /**
     * 请求网络接口登录
     *
     * @param user
     */
    private void login(final User user) {
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
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }.execute("http://172.17.8.100/small/user/v1/login");
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
