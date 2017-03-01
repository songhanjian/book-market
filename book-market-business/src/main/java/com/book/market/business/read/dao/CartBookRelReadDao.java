package com.book.market.business.read.dao;

import com.book.market.model.po.CartBookRel;

import java.util.List;

/**
 * Created by shj on 16-10-1.
 */
public interface CartBookRelReadDao {
    /**
     *
     * @param cartId 购物车对象id
     * @return 该购物车对象下的所有加入到购物车的book集合
     * @throws Exception
     */
    List<CartBookRel> getCarBookRels(int cartId)throws Exception;

    /**
     *
     * @param cartId 购物车id
     * @param bookId
     * @return 返回加入该购物车的该书本信息
     * @throws Exception
     */
    CartBookRel getCartBookByCartIdBookId(int cartId,int bookId)throws Exception;
}
