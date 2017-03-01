package com.book.market.business.read.manage;

import com.book.market.model.dto.Cart;
import com.book.market.model.dto.CartBookRelDto;
import com.book.market.model.po.CartBookRel;

import java.util.List;

/**
 * Created by shj on 16-10-4.
 */
public interface CartReadManage {
     /**
      *
      * @param userId 用户id
      * @return 通过用户id 查询购物车 （一个用户一个购物车），不包含订购书本
      * @throws Exception
      */
     Cart getCart(int userId)throws Exception;

     /**
      *
      * @param userId 用户id
      * @return 通过用户id查询购物车信息，包含订购书本等信息
      * @throws Exception
      */
     com.book.market.model.po.Cart getCartBase(int userId)throws Exception;

     /**
      *
      * @param cartId 购物车Id
      * @param bookId bookId
      * @return 查询该购物车的订购的该书本信息
      * @throws Exception
      */
     CartBookRel getCartBookRelByCartIdBookId(Integer cartId,Integer bookId)throws Exception;

     /**
      *
      * @param cartBookRels 数据转化，为了适应前端的需求，重新包转了该对象，加入是否被选中等信息
      * @return
      * @throws Exception
      */
     List<CartBookRelDto> converCartBookRelToCartBookRelDto(List<CartBookRel> cartBookRels)throws Exception;

     /**
      *
      * @param cartId 购物车Id
      * @return 查询该购物车下订购的所有书本信息
      * @throws Exception
      */
     List<CartBookRelDto> getCartBookRelDtos(Integer cartId)throws Exception;



}
