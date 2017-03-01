package com.book.market.business.read.manage.impl;

import com.book.market.business.read.dao.UserReadDao;
import com.book.market.business.read.manage.UserReadManage;
import com.book.market.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shj on 16-10-2.
 */
@Service
public class UserReadManageImpl implements UserReadManage{

    @Autowired
    private UserReadDao userReadDao;

    @Override
    public Integer usreCheck(User user) throws Exception {
        return userReadDao.userCheck(user);
    }

    @Override
    public User getUser(User user) throws Exception {
        return userReadDao.userGet(user);
    }
}
