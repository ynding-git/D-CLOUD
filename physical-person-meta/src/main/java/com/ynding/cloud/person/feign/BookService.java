package com.ynding.cloud.person.feign;

import com.ynding.cloud.common.model.bo.CloudServiceName;
import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.person.feign.hystrix.BookServiceHystrix;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用book服务的接口
 */
@FeignClient(value = CloudServiceName.PHYSICAL_BOOK_META, fallback = BookServiceHystrix.class)
@RequestMapping(value = "/book", produces = {"application/json;charset=UTF-8"})
public interface BookService {

    @GetMapping("/list")
    ResponseBean findList(@RequestParam Map<String, Object> params);
}
