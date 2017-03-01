package com.book.market.business.write.dao;

import com.book.market.model.po.Cart;

/**
 * Created by shj on 16-10-2.
 */
public interface CartWriteDao {
    void insertCart (Cart cart)throws Exception;
}
