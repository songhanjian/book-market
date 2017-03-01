package com.book.market.business.write.dao;

import com.book.market.model.po.CartBookRel;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shj on 16-10-2.
 */
public class CartBookRelWriteDaoTestCase extends TestCase {
    private CartBookRelWriteDao cartBookRelWriteDao;
    public CartBookRelWriteDaoTestCase(){
        System.setProperty("global.config.path","/home/shj/dev/env/env-dev");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-market-business/spring-service.xml");
        cartBookRelWriteDao = (CartBookRelWriteDao) applicationContext.getBean("cartBookRelWriteDao");
    }

    public void testUpdateCartBookRel(){

        try {
            CartBookRel cartBookRel = new CartBookRel();
            cartBookRel.setId(1);
            cartBookRel.setCount(2);
            cartBookRel.setTotalPrice(30);
            cartBookRelWriteDao.updateCartBookRel(cartBookRel);
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void testDeleteCartBookRel(){
        try {
            cartBookRelWriteDao.deleteCartBookRel(new Integer[]{1});
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void testInsertCartBookRel(){
        try {
            CartBookRel cartBookRel = new CartBookRel();
            cartBookRel.setBookId(2);
            cartBookRel.setCount(2);
            cartBookRel.setBookName("101 Classic Short Stories");
            cartBookRel.setTotalPrice(58);
            cartBookRel.setCartId(1);
            cartBookRel.setBookPrice(29);
            cartBookRelWriteDao.insertCartBookRel(cartBookRel);
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }



}
