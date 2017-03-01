package com.book.market.business.read.dao;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shj on 16-10-2.
 */
public class BookReadDaoTestCase extends TestCase{
    private BookReadDao bookReadDao;
    public BookReadDaoTestCase(){
        System.setProperty("global.config.path","/home/shj/dev/env/env-dev");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-market-business/spring-service.xml");
        bookReadDao = (BookReadDao)applicationContext.getBean("bookReadDaoImpl");
    }

    public void testGetBooks(){
        try {
            Assert.assertEquals(4,bookReadDao.getBooks(2).size());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }

    }

    public void testGetBook(){
        try {
            System.out.println(bookReadDao.getBook(1));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void testGetBookCount(){
        try {
            Assert.assertEquals(1,bookReadDao.getBookCount("19").intValue());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }

    public void testGetBookIso(){
        try {
            System.out.println(bookReadDao.getBookIso(2));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }
    }


}
