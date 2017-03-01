package com.book.market.business.write.dao;

import com.book.market.model.po.CartBookRel;

/**
 * Created by shj on 16-10-1.
 */
public interface CartBookRelWriteDao {
    /**
     *
     * @param cartBookRel 表示某一购物车下的订购的某一书本，跟新操作，包括订购数，总价等
     * @throws Exception
     */
    void updateCartBookRel(CartBookRel cartBookRel)throws Exception;

    /**
     *
     * @param ids 一组cartBookRel 对象id ,批量删除这些购物车下的书
     * @throws Exception
     */
    void deleteCartBookRel(Integer [] ids) throws Exception;

    /**
     *
     * @param cartBookRel 插入操作，把某一本书加入购物车
     * @throws Exception
     */
    void insertCartBookRel(CartBookRel cartBookRel)throws Exception;
}
