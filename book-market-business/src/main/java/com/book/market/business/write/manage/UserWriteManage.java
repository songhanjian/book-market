package com.book.market.business.write.manage;

import com.book.market.business.read.dao.UserReadDao;
import com.book.market.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shj on 16-10-2.
 */
public interface UserWriteManage {

    /**
     *
     * @param user 用户注册信息，完成用户注册
     * @return
     * @throws Exception
     */
    Integer register(User user)throws Exception;


}
