package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.person.feign.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author dyn
 * @version 2019/12/12
 */
@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private BookService bookService;

    @GetMapping("/get/book")
    public ResponseBean getBooks(@RequestParam Map<String, Object> params){

        return bookService.findList(params);
    }
}
