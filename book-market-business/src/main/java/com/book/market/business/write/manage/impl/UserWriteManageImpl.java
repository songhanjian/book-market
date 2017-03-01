package com.book.market.business.write.manage.impl;

import com.book.market.business.read.dao.UserReadDao;
import com.book.market.business.write.dao.UserWriteDao;
import com.book.market.business.write.manage.UserWriteManage;
import com.book.market.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shj on 16-10-2.
 */
@Service
public class UserWriteManageImpl implements UserWriteManage {
    @Autowired
    private UserReadDao userReadDao;
    @Autowired
    private UserWriteDao userWriteDao;


    @Override
    public synchronized Integer register(User user) throws Exception {
        Integer result = userReadDao.userCheck(user);
        if(result!=null&&result.intValue()==1){
            return -1;
        }else {
            return  userWriteDao.insertUser(user);
        }
    }
}
