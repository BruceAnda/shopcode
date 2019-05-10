package com.bawei.zhaoliang.mvp;

import com.bawei.zhaoliang.net.HttpUtil;
import com.bawei.zhaoliang.net.NetCallback;

import java.util.Map;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 4:07 PM
 * Description:
 */
public class UserModelImpl implements IUserContract.IUserModel {

    private HttpUtil httpUtil;

    public UserModelImpl() {
        httpUtil = HttpUtil.getInstance();
    }

    @Override
    public void doHttpPost(String url, Map<String, String> param, NetCallback callback) {
        // 请求网络
        httpUtil.doHttpPost(url, param, callback);
    }
}
