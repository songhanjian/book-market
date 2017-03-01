package com.book.market.business.read.dao.impl;

import com.book.market.business.read.dao.BookReadDao;
import com.book.market.model.constants.Constants;
import com.book.market.model.po.Book;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by shj on 16-10-2.
 */
@Repository
public class BookReadDaoImpl implements BookReadDao {

    @Autowired
    @Qualifier("sqlMapClientRead")
    private SqlMapClient sqlMapClient;


    @Override
    public Book getBook(int id) throws Exception {
        return (Book) sqlMapClient.queryForObject("bookService.query_by_id",id);
    }

    @Override
    public Integer getBookCount(String tagwords) throws Exception {
        Map map = new HashMap();
        map.put("name",tagwords);
        return (Integer) sqlMapClient.queryForObject("bookService.query_for_count",map);
    }

    @Override
    public List<Book> getBooks(int pageNum) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("pageSize",Constants.PAGE_SIZE);
        map.put("pageNum",(pageNum-1)* Constants.PAGE_SIZE);
        return sqlMapClient.queryForList("bookService.query_all",map);
    }

    @Override
    public Book getBookIso(int id) throws Exception {
        return (Book) sqlMapClient.queryForObject("bookService.query_for_iso_by_id",id);
    }

    @Override
    public List<Book> getBookBytag(String tagWords,Integer pageNum) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("pageSize",Constants.PAGE_SIZE);
        map.put("pageNum",(pageNum-1)* Constants.PAGE_SIZE);
        map.put("name",tagWords);
        return sqlMapClient.queryForList("bookService.query_for_iso_by_tag",map);
    }
}
