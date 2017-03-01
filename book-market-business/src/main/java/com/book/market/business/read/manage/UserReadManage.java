package com.book.market.business.read.manage;

import com.book.market.model.po.User;

/**
 * Created by shj on 16-10-2.
 */
public interface UserReadManage {
    /**
     *
     * @param user 用户信息，包含username,password等信息
     * @return 查询是否被注册
     * @throws Exception
     */
    Integer usreCheck(User user) throws Exception;

    /**
     *
     * @param user 用户信息
     * @return 返回该用户所有信息
     * @throws Exception
     */
    User getUser(User user)throws Exception;
}
