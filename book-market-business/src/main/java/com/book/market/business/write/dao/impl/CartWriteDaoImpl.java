package com.book.market.business.write.dao.impl;

import com.book.market.business.write.dao.CartWriteDao;
import com.book.market.model.po.Cart;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by shj on 16-10-2.
 */
@Repository
public class CartWriteDaoImpl implements CartWriteDao{


    @Autowired
    @Qualifier("sqlMapClientWrite")
    private SqlMapClient sqlMapClient;

    @Override
    public void insertCart(Cart cart) throws Exception {
        sqlMapClient.insert("cartService.insert_cart",cart);
    }
}
