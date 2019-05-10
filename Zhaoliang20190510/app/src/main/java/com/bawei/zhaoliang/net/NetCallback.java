package com.bawei.zhaoliang.net;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 4:25 PM
 * Description: 网络响应回调
 */
public interface NetCallback {

    /**
     * 响应成功
     * @param result
     */
    void onSuccess(String result);

    /**
     * 响应失败
     * @param msg
     */
    void onFial(String msg);
}
