package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.entity.Book;
import com.ynding.cloud.person.feign.BookClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author dyn
 * @version 2019/12/12
 */
@RestController
@RequestMapping("person")
public class PersonController {

    private final BookClient bookClient;
    public PersonController(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @GetMapping("/get/book")
    public ResponseBean getBooks(@RequestParam Map<String, Object> params) {

        return bookClient.findList(params);
    }

    @PostMapping("/add/book")
    public ResponseBean saveBook(@RequestBody Book book) {

        return bookClient.saveBook(book);
    }
}
