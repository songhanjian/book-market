package com.book.market.business.write.dao;

import com.book.market.model.po.Cart;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shj on 16-10-2.
 */
public class CartWriteDaoTestCase extends TestCase{

    private CartWriteDao cartWriteDao;

    public CartWriteDaoTestCase(){
        System.setProperty("global.config.path","/home/shj/dev/env/env-dev");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-market-business/spring-service.xml");
        cartWriteDao = (CartWriteDao) applicationContext.getBean("cartWriteDaoImpl");
    }


    public void testInsertCart(){
        try {
            Cart cart =new Cart();
            cart.setUserId(1);
            cart.setIsDelete(0);
            cartWriteDao.insertCart(cart);
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }

    }
}
