package com.book.market.web.controller;

import com.book.market.business.read.manage.CartReadManage;
import com.book.market.business.write.manage.CartWriteManage;
import com.book.market.model.dto.Cart;
import com.book.market.model.dto.CartBookRelDto;
import com.book.market.model.po.CartBookRel;
import com.book.market.web.util.BaseAction;
import com.mchange.v1.util.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shj on 16-10-4.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartAction extends BaseAction{


    private Logger logger = Logger.getLogger(CartAction.class);

    @Resource
    private CartWriteManage cartWriteManage;

    @Resource
    private CartReadManage cartReadManage;

    @RequestMapping(value = "/addToCart" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object addToCart(@RequestBody CartBookRel cartBookRel){
        try {
            cartWriteManage.addBookToCart(cartBookRel);
            return successReturnObject(cartReadManage.getCartBookRelByCartIdBookId(cartBookRel.getCartId(),cartBookRel.getBookId()));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return failReturnObject("add to cart sys err");
        }
    }

    @RequestMapping(value = "/getCart" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object getCart(@RequestBody Integer userId){
        try {
            Cart cart = cartReadManage.getCart(userId);
            return successReturnObject(cart);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return failReturnObject("getCart sys err");
        }
    }

    @RequestMapping(value = "/updateCart" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object UpdateCart(@RequestBody List<CartBookRelDto> list){
        try {
            boolean flag = false;
            for(CartBookRelDto c:list){
                if(c.getIsChosen()&&c.getCount()!=0){
                    cartWriteManage.updateCart(c);
                    flag=true;
                }else if(c.getIsChosen()&&c.getCount()==0) {
                    cartWriteManage.deleteCartBookRel(c);
                    flag=true;
                }

            }
            if(!flag){
                return failReturnObject("no update book detected!");
            }

            return successReturnObject("update success");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return failReturnObject( "updateCart sys err");
        }
    }

    @RequestMapping(value = "/payoff" ,consumes =APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object payoff(@RequestBody List<CartBookRelDto> list){
        List<Integer> idList = new LinkedList<>();
        for(CartBookRelDto c:list){
            if(c.getIsChosen()){
                idList.add(c.getId());
            }
        }
        try {
            if(CollectionUtils.isEmpty(idList)){
                return failReturnObject("no pay book detected!");
            }
            cartWriteManage.payoff( idList.toArray(new Integer[idList.size()]));
            return successReturnObject("pay success");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return failReturnObject("sys err,pay failed");
        }

    }

    @RequestMapping(value = "/cartBookRelCovert" ,consumes =APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object cartBookRelConvert(@RequestBody List<CartBookRel> cartBookRels){
        try {
            List<CartBookRelDto> list = cartReadManage.converCartBookRelToCartBookRelDto(cartBookRels);
            if(CollectionUtils.isEmpty(list)){
                return failReturnObject("covert sys err");
            }
            return successReturnObject(list);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return failReturnObject("covert sys err");
        }
    }


    @RequestMapping(value = "/getCartBookRelDto" ,consumes =APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object getCartBookRelDtos(@RequestBody Integer cartId){
            try {
                return successReturnObject(cartReadManage.getCartBookRelDtos(cartId));
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                return failReturnObject("getCartBookRelDto sys err");
            }
    }


    @RequestMapping(value = "/deleteCart" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object deleteCart(@RequestBody List<CartBookRelDto> list){
        try {
            boolean flag = false;
            for(CartBookRelDto c:list){
                if(c.getIsChosen()){
                    cartWriteManage.deleteCartBookRel(c);
                    flag = true;
                }
            }
            if(!flag){
                return failReturnObject("no delete book detected!");
            }
            return successReturnObject("dalete success");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return failReturnObject("deleteCart sys err");
        }
    }

}
