package com.book.market.service;

import com.book.market.model.soap.Book;
import junit.framework.TestCase;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by shj on 17-1-7.
 */
public class BookServiceTestCase extends TestCase {


    public void testGetBook(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(BookService.class);
        factoryBean.setAddress("http://localhost:8080/book-market-service/service/BookService");
        BookService bookService = (BookService)factoryBean.create();
        Book book = bookService.getBook(2);
        System.out.println(book);
    }
}
