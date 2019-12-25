package com.ynding.cloud.person.feign;

import com.ynding.cloud.common.model.bo.CloudServiceName;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.common.model.entity.Book;
import com.ynding.cloud.person.feign.hystrix.BookServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 调用book服务的接口
 */
@FeignClient(value = CloudServiceName.PHYSICAL_BOOK_META, fallback = BookServiceHystrix.class)
@Component
public interface BookService {
    @GetMapping("/book/list")
    ResponseBean findList(@RequestParam Map<String, Object> params);

    @PostMapping("/book/insert")
    ResponseBean saveBook(@RequestBody Book book);
}
