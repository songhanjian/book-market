package com.book.market.business.write.manage.impl;

import com.book.market.business.read.dao.CartBookRelReadDao;
import com.book.market.business.write.dao.CartBookRelWriteDao;
import com.book.market.business.write.dao.CartWriteDao;
import com.book.market.business.write.manage.CartWriteManage;
import com.book.market.model.po.Cart;
import com.book.market.model.po.CartBookRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shj on 16-10-3.
 */
@Service
public class CartWriteManageImpl implements CartWriteManage {
    @Autowired
    private CartWriteDao cartWriteDao;

    @Autowired
    private CartBookRelWriteDao cartBookRelWriteDao;

    @Autowired
    private CartBookRelReadDao cartBookRelReadDao;

    @Override
    public void saveCart(Integer userId) throws Exception {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cartWriteDao.insertCart(cart);
    }


    @Override
    public void addBookToCart(CartBookRel cartBookRel) throws Exception {
        CartBookRel cartBookRelRes = cartBookRelReadDao.getCartBookByCartIdBookId(cartBookRel.getCartId(),cartBookRel.getBookId());
        if(cartBookRelRes==null||cartBookRelRes.getId()==null){
            cartBookRelWriteDao.insertCartBookRel(cartBookRel);

        }else {
            cartBookRelRes.setCount(cartBookRelRes.getCount()+cartBookRel.getCount());
            cartBookRelRes.setTotalPrice(cartBookRelRes.getTotalPrice()+cartBookRel.getTotalPrice());
            cartBookRelWriteDao.updateCartBookRel(cartBookRelRes);
        }
    }

    @Override
    public void payoff(Integer [] ids)throws Exception{
        cartBookRelWriteDao.deleteCartBookRel(ids);
    }

    @Override
    public void updateCart(CartBookRel cartBookRel) throws Exception {
        cartBookRelWriteDao.updateCartBookRel(cartBookRel);
    }

    @Override
    public void deleteCartBookRel(CartBookRel cartBookRel) throws Exception {
        cartBookRelWriteDao.deleteCartBookRel(new Integer[]{cartBookRel.getId()});
    }
}
