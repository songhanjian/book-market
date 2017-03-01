package com.book.market.business.write.dao.impl;

import com.book.market.model.po.CartBookRel;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by shj on 16-10-2.
 */
@Repository
public class CartBookRelWriteDao implements com.book.market.business.write.dao.CartBookRelWriteDao {

    @Autowired
    @Qualifier("sqlMapClientWrite")
    private SqlMapClient sqlMapClient;

    @Override
    public void deleteCartBookRel(Integer[] ids) throws Exception {
        Map<String,Object> map =  new HashMap<>();
        map.put("ids",ids);
        sqlMapClient.update("cartBookRelService.delete_by_ids",map);
    }

    @Override
    public void updateCartBookRel(CartBookRel cartBookRel) throws Exception {
        sqlMapClient.update("cartBookRelService.update_cart_book_rel",cartBookRel);
    }

    @Override
    public void insertCartBookRel(CartBookRel cartBookRel) throws Exception {
        sqlMapClient.insert("cartBookRelService.insert_cart_book_rel",cartBookRel);
    }
}
