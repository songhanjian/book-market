package com.book.market.business.read.dao;

import com.book.market.model.po.User;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shj on 16-10-2.
 */
public class UserReadDaoTestCase extends TestCase {

    private UserReadDao userReadDao;

    public UserReadDaoTestCase() {
        System.setProperty("global.config.path","/home/shj/dev/env/env-dev");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-market-business/spring-service.xml");
        userReadDao = (UserReadDao) applicationContext.getBean("userReadDaoImpl");
    }

    public void testUserCheck(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        try {
            Assert.assertEquals(1,userReadDao.userCheck(user).intValue());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }

    }

    public void testUserGet(){

        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        try {
            System.out.println(userReadDao.userGet(user));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
