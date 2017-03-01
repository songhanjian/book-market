package com.book.market.business.read.dao;

import com.book.market.model.po.User;

/**
 * Created by shj on 16-10-1.
 */
public interface UserReadDao {

    /**
     *
     * @param user 用户对象，保存有username,password信息
     * @return 验证该用户是否已创建，若创建，返回1
     * @throws Exception
     */
    Integer userCheck(User user)throws Exception;

    /**
     *
     * @param user 用户对象，包含username,password信息
     * @return 返回该用户的所有信息
     * @throws Exception
     */
    User userGet(User user)throws Exception;


}
