package com.book.market.business.read.dao;

import com.book.market.model.po.Cart;

/**
 * Created by shj on 16-10-1.
 */
public interface CartReadDao {
    /**
     *
     * @param userId 用户id
     * @return 返回该用户的购物车对象
     * @throws Exception
     */
    Cart getCart(Integer userId)throws Exception;
}
