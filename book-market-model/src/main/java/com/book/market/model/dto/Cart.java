package com.book.market.model.dto;

import com.book.market.model.po.CartBookRel;

import java.util.List;

/**
 * Created by shj on 16-10-1.
 */
public class Cart {
    private Integer id;
    private Integer userId;
    private Integer isDelete;
    private List<CartBookRel> cartBookRels;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", isDelete=" + isDelete +
                ", cartBookRels=" + cartBookRels +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public List<CartBookRel> getCartBookRels() {
        return cartBookRels;
    }

    public void setCartBookRels(List<CartBookRel> cartBookRels) {
        this.cartBookRels = cartBookRels;
    }
}
