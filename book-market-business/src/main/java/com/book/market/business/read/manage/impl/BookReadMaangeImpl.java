package com.book.market.business.read.manage.impl;

import com.book.market.business.read.dao.BookReadDao;
import com.book.market.business.read.manage.BookReadManage;
import com.book.market.model.constants.Constants;
import com.book.market.model.dto.BookIso;
import com.book.market.model.po.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shj on 16-10-2.
 */
@Service
public class BookReadMaangeImpl implements BookReadManage {
    @Autowired
    private BookReadDao bookReadDao;


    @Override
    public Book getBookById(Integer id) throws Exception {
        return bookReadDao.getBook(id);
    }


    @Override
    public BookIso getBookIsoById(Integer id) throws Exception {
        Book book = bookReadDao.getBookIso(id);
        BookIso bookIso = new BookIso();
        BeanUtils.copyProperties(book,bookIso);
        return bookIso;
    }


    @Override
    public List<BookIso> getBookIsoByTag(String tagwords,Integer pageNum) throws Exception {
        List<Book> books = bookReadDao.getBookBytag(tagwords,pageNum);
        if(books!=null){
            List<BookIso> bookIsos = new ArrayList<>();
            for (Book b:books){
                BookIso bookIso = new BookIso();
                BeanUtils.copyProperties(b,bookIso);
                bookIsos.add(bookIso);
            }
            return bookIsos;
        }else return null;

    }

    @Override
    public Integer pageCount(String tagwords) throws Exception {
        Integer res = 0;
        Integer tmep = bookReadDao.getBookCount(tagwords);
        res =  tmep/Constants.PAGE_SIZE;
        if(tmep% Constants.PAGE_SIZE!=0){
            res++;
        }
        return res;
    }
}
