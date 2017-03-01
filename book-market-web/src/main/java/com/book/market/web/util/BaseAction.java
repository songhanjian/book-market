package com.book.market.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Colossus on 2016/1/31.
 */
public class BaseAction {



    public static final String APPLICATION_JSON = "application/json";

    //public final static Gson gson = new Gson();

    protected Object successReturnObject(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "0");
        map.put("data", object);
        return map;
    }

    protected Object successReturnObject(Object object, int num) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "0");
        map.put("data", object);
        map.put("total", num);
        return map;
    }

    protected Object failReturnObject(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "-1");
        map.put("data", object);
        return map;
    }
}

