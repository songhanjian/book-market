package com.book.market.business.read.dao.impl;

import com.book.market.business.read.dao.CartBookRelReadDao;
import com.book.market.model.po.CartBookRel;
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
public class CartBookRelReadDaoImpl implements CartBookRelReadDao{

    @Autowired
    @Qualifier("sqlMapClientRead")
    private SqlMapClient sqlMapClient;

    @Override
    public List<CartBookRel> getCarBookRels(int cartId) throws Exception {
        return sqlMapClient.queryForList("cartBookRelService.query_by_cart_id",cartId);
    }

    @Override
    public CartBookRel getCartBookByCartIdBookId(int cartId, int bookId) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("cartId",cartId);
        map.put("bookId",bookId);
        return (CartBookRel) sqlMapClient.queryForObject("cartBookRelService.query_by_cart_id_book_id",map);
    }
}
