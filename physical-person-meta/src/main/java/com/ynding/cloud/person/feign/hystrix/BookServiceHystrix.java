package com.ynding.cloud.person.feign.hystrix;

import com.ynding.cloud.common.model.bo.ResponseBean;
import com.ynding.cloud.person.feign.BookService;

import java.util.Map;

import static com.ynding.cloud.common.model.bo.ResponseCode.BOOK_FEIGN_ERROR;

/**
 * 断路器
 * @author dyn
 * @version 2019/12/12
 */
public class BookServiceHystrix implements BookService {

    @Override
    public ResponseBean findList(Map<String, Object> params) {
        return ResponseBean.fail(BOOK_FEIGN_ERROR);
    }
}
