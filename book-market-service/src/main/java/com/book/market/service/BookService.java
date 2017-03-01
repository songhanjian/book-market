package com.book.market.service;

import com.book.market.model.soap.Book;

import javax.jws.WebService;
import java.util.ArrayList;

/**
 * Created by shj on 17-1-7.
 */
@WebService
public interface BookService {
    public Book getBook(int id) ;
}
