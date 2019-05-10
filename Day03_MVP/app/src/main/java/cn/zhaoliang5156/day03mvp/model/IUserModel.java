package cn.zhaoliang5156.day03mvp.model;

import cn.zhaoliang5156.day03mvp.bean.User;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/10 3:30 PM
 * Description: UserModelImpl 的接口
 */
public interface IUserModel {

    void login(final User user, final UserModelImpl.ModelCallback callback);
}
