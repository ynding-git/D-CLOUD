package com.ynding.cloud.route.zuul.config;

import com.ynding.cloud.route.zuul.GatewayWebSecurityExpressionHandler;
import com.ynding.cloud.route.zuul.filter.GatewayAuditLogFilter;
import com.ynding.cloud.route.zuul.service.IAuditLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * <p> 作为一个资源服务器存在</p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
@Configuration
@EnableResourceServer
public class GatewaySecurityConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private GatewayWebSecurityExpressionHandler gatewayWebSecurityExpressionHandler;

    @Autowired
    private IAuditLogService auditLogService;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源服务器的id，“现在我就是资源服务器 gateway-server！！！”
        resources.resourceId("gateway-server");
        //注入自己的 表达式处理器
        resources.expressionHandler(gatewayWebSecurityExpressionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //配置审计日志过滤器位置
                .addFilterBefore(new GatewayAuditLogFilter(auditLogService), ExceptionTranslationFilter.class)
                .authorizeRequests()
                //放过/token开头的请求，是在申请令牌
                .antMatchers("/token/**").permitAll()
                .anyRequest()
                /**
                 * 指定权限访问规则，permissionService需要自己实现，返回布尔值，true-能访问；false-无权限
                 * 传进去2个参数，1-当前请求 ，2-当前用户
                 */
                .access("#permissionService.hasPermission(request, authentication)");
        ;
    }
}
