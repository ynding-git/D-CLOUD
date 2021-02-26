package com.ynding.cloud.route.zuul.controller;

import com.ynding.cloud.route.zuul.domain.AccessToken;
import com.ynding.cloud.route.zuul.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>登录控制器</p>
 *
 * @author ynding
 * @version 2021/2/25
 **/
@Slf4j
@Controller
@RequestMapping("/")
public class LoginController {
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/index")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

    /**
     * 登录
     * @param userInfo 登录类
     * @param response response
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfo, HttpServletResponse response) {

        log.info("userInfo is {}", userInfo.toString());

        //认证服务器验token地址 /oauth/check_token 是  spring .security.oauth2的验token端点
        String oauthServiceUrl = "http://localhost:10402/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        //不是json请求
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //网关的appId，appSecret，需要在数据库oauth_client_details注册
        headers.setBasicAuth("admin", "123456");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", userInfo.getUsername());
        params.add("password", userInfo.getPassword());
        //授权类型-密码模式
        params.add("grant_type", "password");
        params.add("scope", "read write");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        ResponseEntity<AccessToken> tokenResponse = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, AccessToken.class);
        String token = tokenResponse.getBody().getAccess_token();
        //将token存入redis
        redisTemplate.opsForValue().set(token, userInfo.getUsername());

        return "redirect:/index";
    }
}
