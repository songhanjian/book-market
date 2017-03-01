package com.book.market.business.read.dao;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shj on 16-10-2.
 */
public class CartBookRelReadDaoTestCase extends TestCase{
    private CartBookRelReadDao cartBookRelReadDao;

    public CartBookRelReadDaoTestCase(){
        System.setProperty("global.config.path","/home/shj/dev/env/env-dev");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-market-business/spring-service.xml");
        cartBookRelReadDao = (CartBookRelReadDao) applicationContext.getBean("cartBookRelReadDaoImpl");
    }

    public void testGetCartBookRels(){
        try {
            System.out.println(cartBookRelReadDao.getCarBookRels(1));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void testGetCartBookByCartIdBookId(){
        try {
            System.out.println(cartBookRelReadDao.getCartBookByCartIdBookId(1,1));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }
}
