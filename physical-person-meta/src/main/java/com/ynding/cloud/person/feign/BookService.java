package com.ynding.cloud.person.feign;

import com.ynding.cloud.common.model.bo.CloudServiceName;
import com.ynding.cloud.common.model.bo.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(CloudServiceName.PHYSICAL_BOOK_META)
@RequestMapping(value = "/book", produces = {"application/json;charset=UTF-8"})
@Component
public interface BookService {

    @GetMapping("/list")
    @ApiOperation(value = "查询列表")
    ResponseBean findList(@RequestParam Map<String, Object> params);
}
