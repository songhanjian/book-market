package com.book.market.service.impl;

import com.book.market.service.HelloWorld;

/**
 * Created by shj on 17-1-7.
 */
public class HelloWordImpl implements HelloWorld {

    public HelloWordImpl(){
    }
    @Override
    public String sayHello(String text) {
        System.out.println("sayHello is called by " + text);
        return "Hello world!";
    }
}
