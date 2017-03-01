package com.book.market.business.read.dao;

import com.book.market.model.dto.BookIso;
import com.book.market.model.po.Book;

import java.util.List;

/**
 * Created by shj on 16-10-1.
 */
public interface BookReadDao {
    /**
     *
     * @param pageNum 当前页数
     * @return 返回当前页面书本数，以12本为一页显示
     * @throws Exception
     */
    List<Book> getBooks(int pageNum)throws Exception;

    /**
     *
     * @param id book 的 id
     * @return 返回拥有该id的book对象
     * @throws Exception
     */
    Book getBook(int id)throws Exception;

    /**
     *
     * @param tagwords 关键词查询，因功能未分页，已弃用
     * @return 返回搜索该关键词的条目数
     * @throws Exception
     */
    Integer getBookCount(String tagwords)throws Exception;

    /**
     *
     * @param id book id 因为book包含较多信息，通过新建一个bookIso对象保存部分有用信息用于前端输出
     * @return 返回该bookISO对象
     * @throws Exception
     */
    Book getBookIso(int id)throws Exception;

    /**
     *
     * @param tagWords 搜索关键字
     * @param pageNum 当前页面号
     * @return 返回搜索该关键字的当前页的book对象集合
     * @throws Exception
     */
    List<Book> getBookBytag(String tagWords,Integer pageNum)throws Exception;
}
