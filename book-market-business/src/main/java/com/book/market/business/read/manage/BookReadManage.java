package com.book.market.business.read.manage;

import com.book.market.model.dto.BookIso;
import com.book.market.model.po.Book;

import java.util.List;

/**
 * Created by shj on 16-10-2.
 */
public interface BookReadManage {
    /**
     *
     * @param id bookId
     * @return 通过bookId 查询该id的book所有信息
     * @throws Exception
     */
    Book getBookById(Integer id)throws Exception;

    /**
     *
     * @param id bookId
     * @return 通过该bookId查询该book的部分信息
     * @throws Exception
     */
    BookIso getBookIsoById(Integer id) throws Exception;

    /**
     *
     * @param tagwords 关键字
     * @param pageNum 当前页面数
     * @return 返回该关键字的当前页面book集合
     * @throws Exception
     */
    List<BookIso> getBookIsoByTag(String tagwords,Integer pageNum)throws Exception;

    /**
     *
     * @param tagwords 关键字
     * @return 返回该关键字下的总页数
     * @throws Exception
     */
    Integer pageCount(String tagwords)throws Exception;
}
