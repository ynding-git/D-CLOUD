package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.entity.Book;
import com.ynding.cloud.person.feign.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author dyn
 * @version 2019/12/12
 */
@RestController
@RequestMapping("person")
public class PersonController {

    private final BookService bookService;

    public PersonController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/book")
    public ResponseBean getBooks(@RequestParam Map<String, Object> params){

        return bookService.findList(params);
    }

    @PostMapping("/add/book")
    public ResponseBean saveBook(@RequestBody Book book){

        return bookService.saveBook(book);
    }
}
