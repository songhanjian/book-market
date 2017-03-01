package com.book.market.web.controller;

import com.book.market.business.read.manage.CartReadManage;
import com.book.market.business.read.manage.UserReadManage;
import com.book.market.business.write.manage.CartWriteManage;
import com.book.market.business.write.manage.UserWriteManage;


import com.book.market.model.dto.Cart;
import com.book.market.model.po.User;
import com.book.market.web.util.BaseAction;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shj on 16-9-27.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountAction extends BaseAction {

    private Logger logger = Logger.getLogger(AccountAction.class);

    @Resource
    private UserReadManage userReadManage;

    @Resource
    private UserWriteManage userWriteManage;

    @Resource
    private CartWriteManage cartWriteManage;

    @Resource
    private CartReadManage cartReadManage;


    @RequestMapping(value = "/login" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object login(@RequestBody Map user){
        try {
            User userData = new User();
            userData.setId(1);
            userData.setUsername(user.get("username").toString());
            userData.setPassword(user.get("password").toString());
            User result = userReadManage.getUser(userData);
            if(result==null) return failReturnObject("username or password incorrected");
            Cart cart = cartReadManage.getCart(result.getId());
            Map<String,Object> map = new HashMap();
            map.put("user",user);
            map.put("cart",cart);
           return successReturnObject(map);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return failReturnObject("login system err");
        }

    }


    @RequestMapping(value = "/register" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object register(@RequestBody User user){
        try {
            System.out.println(user);
            Integer res = userWriteManage.register(user);
            if(res!=null){
                if(res.intValue()==-1){
                    return failReturnObject("username has register");
                }else {
                    cartWriteManage.saveCart(res);
                    return successReturnObject("register success");
                }
            }
            return failReturnObject("register sys err");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return failReturnObject("register sys err");
        }

    }
}
