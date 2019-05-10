package cn.zhaoliang5156.day03mvc.net;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 2:23 PM
 * Description: 网络接口回调
 */
public interface NetCallBack {

    /**
     * 成功的响应
     *
     * @param result
     */
    void onSuccess(String result);

    /**
     * 错误的响应
     *
     * @param msg
     */
    void onError(String msg);
}
