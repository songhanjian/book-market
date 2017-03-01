package com.book.market.business.read.dao;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shj on 16-10-2.
 */
public class CartReadDaoTestCase extends TestCase {
    private CartReadDao cartReadDao;

    public CartReadDaoTestCase() {
        System.setProperty("global.config.path","/home/shj/dev/env/env-dev");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-market-business/spring-service.xml");
        cartReadDao  = (CartReadDao)applicationContext.getBean("cartReadDaoImpl");
    }

    public void testGetCart(){
        try {

            System.out.println(cartReadDao.getCart(1));

        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }


}
