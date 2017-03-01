package com.book.market.business.write.dao;

import com.book.market.model.po.User;

/**
 * Created by shj on 16-10-1.
 */
public interface UserWriteDao
{
    Integer insertUser(User user)throws Exception;
}
