package com.book.market.service.impl;

import com.book.market.business.read.manage.BookReadManage;
import com.book.market.model.soap.Book;
import com.book.market.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

/**
 * Created by shj on 17-1-7.
 */
public class BookServiceImpl implements BookService {
    @Autowired
    private BookReadManage bookReadManage;

    private Logger logger = Logger.getLogger(BookServiceImpl.class);
    @Override
    public Book getBook(int id) {
        try {
            com.book.market.model.po.Book book = this.bookReadManage.getBookById(new Integer(id));
            Book bookRes = new Book();
            BeanUtils.copyProperties(book,bookRes);
            return bookRes;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return  null;
        }


    }
}
