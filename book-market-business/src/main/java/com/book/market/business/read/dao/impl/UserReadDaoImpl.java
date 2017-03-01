package com.book.market.business.read.dao.impl;

import com.book.market.business.read.dao.UserReadDao;
import com.book.market.model.po.User;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by shj on 16-10-2.
 */
@Repository
public class UserReadDaoImpl implements UserReadDao{


    @Autowired
    @Qualifier("sqlMapClientRead")
    private SqlMapClient sqlMapClient;
    @Override
    public Integer userCheck(User user) throws Exception {
        return (Integer) sqlMapClient.queryForObject("userService.user_check",user);
    }

    @Override
    public User userGet(User user) throws Exception {
        return (User) sqlMapClient.queryForObject("userService.user_get",user);
    }

}
