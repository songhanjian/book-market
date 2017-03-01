package com.book.market.business.write.dao;

import com.book.market.model.po.User;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shj on 16-10-2.
 */
public class UserWriteDaoTestCase extends TestCase {
    private UserWriteDao userWriteDao;

    public UserWriteDaoTestCase(){
        System.setProperty("global.config.path","/home/shj/dev/env/env-dev");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-market-business/spring-service.xml");
        userWriteDao = (UserWriteDao) applicationContext.getBean("userWriteDaoImpl");
    }

    public void testInsertUser(){
        try {
            User user = new User();
            user.setUsername("fangdandan");
            user.setPassword("123456");
            user.setPhone("16539264093");
            user.setCompany("ody");
            user.setEmail("fangdandan1991@163.com");
            user.setAddress("西青院");
            System.out.println(userWriteDao.insertUser(user));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }


}
