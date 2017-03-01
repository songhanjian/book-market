package com.book.market.service;

import junit.framework.TestCase;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by shj on 17-1-7.
 */
public class HelloWorldTestCase extends TestCase {

    public void testSayHello(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(HelloWorld.class);
        factoryBean.setAddress("http://localhost:8080/book-market-service/service/HelloWorld");
        HelloWorld helloWorld =(HelloWorld)factoryBean.create();
        System.out.println(helloWorld.sayHello("helloWorld"));
    }
}
