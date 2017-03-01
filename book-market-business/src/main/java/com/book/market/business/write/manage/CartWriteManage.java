package com.book.market.business.write.manage;

import com.book.market.model.po.CartBookRel;

/**
 * Created by shj on 16-10-3.
 */
public interface CartWriteManage {

    /**
     *
     * @param userId 用户id,通过用户id新建一个购物车，该函数只会执行一次
     * @throws Exception
     */
    void saveCart(Integer userId)throws Exception;

    /**
     *
     * @param cartBookRel 添加书本到购物车，若购物车已经存在该书，则数量+1
     * @throws Exception
     */
    void addBookToCart(CartBookRel cartBookRel)throws Exception;

    /**
     *
     * @param ids cartBookRel id 批量购买，购买完成后移出该购物车
     * @throws Exception
     */
    void payoff(Integer [] ids)throws Exception;

    /**
     *
     * @param cartBookRel 跟新购物车，仅限数量
     * @throws Exception
     */
    void updateCart(CartBookRel cartBookRel) throws Exception;

    /**
     *
     * @param cartBookRel 取消订购该购物车的某本书
     * @throws Exception
     */
    void deleteCartBookRel(CartBookRel cartBookRel)throws Exception;
}
