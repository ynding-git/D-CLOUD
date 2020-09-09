package com.ynding.cloud.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器
 * 配置了@EnableResourceServer ，所有发往physical-book-meta的请求，都会去请求头里找token，找不到不让你过
 * @author ynding
 * @version 2020/9/9
 **/
@Configuration
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源服务器的id，“现在我就是资源服务器book-server！！！”
        resources.resourceId("book-server");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 进入physical-book-meta的所有请求，哪些要拦截，哪些要放过，在这里配置
         */
        http.authorizeRequests()
                .antMatchers("/hello")
                .permitAll() //放过/haha不拦截
                .anyRequest().authenticated();//其余所有请求都拦截
    }
}
