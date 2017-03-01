package com.book.market.web.controller;

import com.book.market.business.read.manage.BookReadManage;
import com.book.market.model.dto.BookIso;
import com.book.market.model.po.Book;
import com.book.market.web.util.BaseAction;
import com.mchange.v1.util.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shj on 16-10-2.
 */
@Controller
@RequestMapping(value = "/book")
public class BookAction extends BaseAction{



    private Logger logger = Logger.getLogger(BookAction.class);

    @Resource
    private BookReadManage bookReadManage;

    @RequestMapping(value = "/detail" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object getBookById(@RequestBody Map map){
        try {
            Book book = bookReadManage.getBookById(Integer.parseInt(map.get("id").toString()));
            List<BookIso> bookIsos = new ArrayList<>();
            String [] ids = null;
            if(!StringUtils.isEmpty(book.getRelate())){
              ids  = book.getRelate().trim().split(",");
            }

            if(ids!=null){
                for(String id:ids){
                    try {
                        int idInt = Integer.parseInt(id);

                        bookIsos.add(bookReadManage.getBookIsoById(idInt));
                    }catch (Exception e){
                        logger.error(e.getMessage(),e);
                    }
                }
            }
            Map<String,Object> mapRes = new HashMap<>();
            mapRes.put("book",book);
            mapRes.put("bookRelate",bookIsos);
            return successReturnObject(mapRes);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return failReturnObject("detail sys err");
        }

    }



    @RequestMapping(value = "/tag" ,consumes = APPLICATION_JSON, method = RequestMethod.POST)
    public @ResponseBody
    Object getBookByTag(@RequestBody Map map){
        String tagwords = null;
        if(map.get("tagwords")!=null){
            tagwords = map.get("tagwords").toString();
        }
        Integer pageNum = Integer.parseInt(map.get("pageNum").toString());


        try {
            Map mapResult = new HashMap();
            mapResult.put("books",bookReadManage.getBookIsoByTag(tagwords,pageNum));
            mapResult.put("pageCount",bookReadManage.pageCount(tagwords));
            return successReturnObject(mapResult);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return failReturnObject("tag sys err");
        }
    }

}
