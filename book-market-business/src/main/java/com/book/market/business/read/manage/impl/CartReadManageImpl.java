package com.book.market.business.read.manage.impl;

import com.book.market.business.read.dao.BookReadDao;
import com.book.market.business.read.dao.CartBookRelReadDao;
import com.book.market.business.read.dao.CartReadDao;
import com.book.market.business.read.manage.CartReadManage;
import com.book.market.model.dto.Cart;
import com.book.market.model.dto.CartBookRelDto;
import com.book.market.model.po.Book;
import com.book.market.model.po.CartBookRel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shj on 16-10-4.
 */
@Service
public class CartReadManageImpl implements CartReadManage {

    @Autowired
    private CartReadDao cartReadDao;

    @Autowired
    private CartBookRelReadDao cartBookRelReadDao;

    @Autowired
    private BookReadDao bookReadDao;

    @Override
    public Cart getCart(int userId) throws Exception {
        com.book.market.model.po.Cart cart = cartReadDao.getCart(userId);
        if(cart==null){
            return null;
        }
        Cart cartDto = new Cart();
        BeanUtils.copyProperties(cart,cartDto);
        List<CartBookRel> cartBookRels = cartBookRelReadDao.getCarBookRels(cart.getId());
        cartDto.setCartBookRels(cartBookRels);
        return cartDto;
    }

    @Override
    public com.book.market.model.po.Cart getCartBase(int userId) throws Exception {
        return cartReadDao.getCart(userId);
    }

    @Override
    public CartBookRel getCartBookRelByCartIdBookId(Integer cartId, Integer bookId) throws Exception {
        return cartBookRelReadDao.getCartBookByCartIdBookId(cartId,bookId);
    }

    @Override
    public List<CartBookRelDto> converCartBookRelToCartBookRelDto(List<CartBookRel> cartBookRels) throws Exception {
        if(cartBookRels==null) return null;
        List<CartBookRelDto> cartBookRelDtos = new ArrayList<>();
        for(CartBookRel c:cartBookRels){
            CartBookRelDto cartBookRelDto = new CartBookRelDto();
            BeanUtils.copyProperties(c,cartBookRelDto);
            Book book = bookReadDao.getBook(c.getBookId());
            cartBookRelDto.setImgUrl(book.getImgUrl());
            cartBookRelDto.setIsChosen(false);
            cartBookRelDtos.add(cartBookRelDto);
        }
        return cartBookRelDtos;
    }

    @Override
    public List<CartBookRelDto> getCartBookRelDtos(Integer cartId) throws Exception {
        List<CartBookRel> list = cartBookRelReadDao.getCarBookRels(cartId);
        List<CartBookRelDto> cartBookRelDtos = new ArrayList<>();
        for(CartBookRel c:list){
            CartBookRelDto cartBookRelDto = new CartBookRelDto();
            BeanUtils.copyProperties(c,cartBookRelDto);
            Book book = bookReadDao.getBook(c.getBookId());
            cartBookRelDto.setIsChosen(false);
            cartBookRelDto.setImgUrl(book.getImgUrl());
            cartBookRelDtos.add(cartBookRelDto);
        }
        return cartBookRelDtos;
    }
}
