package com.ynding.cloud.book.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dyn
 * @version 2019/12/25
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Api(value="TestConfig",tags={"TestConfig-Controller"})
@RefreshScope
public class TestConfigController {

    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String getHello(){
        return hello;
    }
}
