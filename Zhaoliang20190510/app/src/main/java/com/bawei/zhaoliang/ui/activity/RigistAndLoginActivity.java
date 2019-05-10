package com.bawei.zhaoliang.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.zhaoliang.R;
import com.bawei.zhaoliang.bean.User;
import com.bawei.zhaoliang.mvp.IUserContract;
import com.bawei.zhaoliang.mvp.UserPresenterImpl;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 3:35 PM
 * Description:  MVP 的登录注册
 * <p>
 * 步骤：
 * 1. 首先搞一个界面--》输入框 按钮
 * 2. 使用契约接口搭建MVP框架-- 使用接口
 * 3. 实现MVP接口
 * 5. 网络工具类
 * 6. 测试--可用就完成了...
 * <p>
 * phone: 15116978888
 * pwd: 123456
 */
public class RigistAndLoginActivity extends AppCompatActivity implements IUserContract.IUserView {

    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemeberMe;

    // P 层的引用
    private IUserContract.IUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbRemeberMe = findViewById(R.id.cb_remember_me);

        presenter = new UserPresenterImpl();
        presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    /**
     * 注册
     *
     * @param view
     */
    public void regist(View view) {
        User user = presenter.inputToUser(etUsername.getText().toString(), etPassword.getText().toString());
        presenter.regist(user);
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        User user = presenter.inputToUser(etUsername.getText().toString(), etPassword.getText().toString());
        presenter.login(user);
    }

    @Override
    public void registSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registFail() {

    }

    @Override
    public void loginSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        presenter.toMain(this);
    }

    @Override
    public void loginFail() {

    }
}
