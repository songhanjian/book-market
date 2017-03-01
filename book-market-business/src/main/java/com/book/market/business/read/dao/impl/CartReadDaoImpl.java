package com.book.market.business.read.dao.impl;

import com.book.market.business.read.dao.CartReadDao;
import com.book.market.model.po.Cart;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by shj on 16-10-2.
 */
@Repository
public class CartReadDaoImpl implements CartReadDao {
    @Autowired
    @Qualifier("sqlMapClientRead")
    private SqlMapClient sqlMapClient;

    @Override
    public Cart getCart(Integer userId) throws Exception {
        return (Cart) sqlMapClient.queryForObject("cartService.query_by_user_id",userId);
    }
}
