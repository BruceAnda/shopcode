package cn.zhaoliang5156.day03mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.zhaoliang5156.day03mvp.bean.User;
import cn.zhaoliang5156.day03mvp.persenter.UserPresenter;
import cn.zhaoliang5156.day03mvp.view.UserView;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 11:26 AM
 * Description: 登录界面
 * <p>
 * phone:15169707777
 * pwd:123456
 */
public class MainActivity extends AppCompatActivity implements UserView {

    private UserPresenter presenter;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        presenter = new UserPresenter(this);
    }

    /**
     * 点击登录按钮调用
     *
     * @param view
     */
    public void login(View view) {
        // 1. 从界面读取用户名和密码 封装成User对象
        User user = presenter.inputToUser(etUsername.getText().toString(), etPassword.getText().toString());
        // 2. 检测用户用户名和密码
        boolean isActiveUser = presenter.checkUser(user);
        // 3. 请求服务器login登录
        if (isActiveUser) {
            presenter.login(user);
        }
    }

    public void onSuccess(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // framgment 有 attach，detach
        presenter.detach();
    }
}
