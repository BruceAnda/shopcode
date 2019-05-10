package com.bawei.zhaoliang.bean;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 4:12 PM
 * Description: 用户 bean
 */
public class User {

    private String phone;
    private String pwd;

    public User() {
    }

    public User(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
