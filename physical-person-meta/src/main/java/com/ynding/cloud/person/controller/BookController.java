package com.ynding.cloud.person.controller;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.entity.book.Book;
import com.ynding.cloud.person.clients.BookClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author dyn
 * @version 2019/12/12
 */
@RestController
@RequestMapping("person")
@Api(value = "BookController",tags = "book")
public class BookController {

    private final BookClient bookClient;
    public BookController(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @GetMapping("/get/book")
    @ApiOperation(value = "查询书本", produces = "application/json")
    public ResponseBean getBooks(@RequestParam Map<String, Object> params) {

        return bookClient.findList(params);
    }

    @PostMapping("/add/book")
    @ApiOperation(value = "添加书本", produces = "application/json")
    public ResponseBean saveBook(@RequestBody Book book) {

        return bookClient.saveBook(book);
    }
}
