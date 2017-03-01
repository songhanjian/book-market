package com.book.market.business.write.dao.impl;

import com.book.market.business.write.dao.UserWriteDao;
import com.book.market.model.po.User;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by shj on 16-10-2.
 */
@Repository
public class UserWriteDaoImpl implements UserWriteDao{

    @Autowired
    @Qualifier("sqlMapClientWrite")
    private SqlMapClient sqlMapClient;

    @Override
    public Integer insertUser(User user) throws Exception {
        return (Integer) sqlMapClient.insert("userService.insert_user",user);
    }
}
