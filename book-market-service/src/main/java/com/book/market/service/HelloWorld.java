package com.book.market.service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by shj on 17-1-7.
 */
@WebService
public interface HelloWorld {
    public String sayHello(@WebParam(name="text") String text);

}
