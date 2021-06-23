package com.ynding.cloud.route.gateway.config;

import com.ynding.cloud.route.gateway.service.IAuditLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * <p> 作为一个资源服务器存在</p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
@Slf4j
@Configuration
@EnableResourceServer
public class GatewaySecurityConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private IAuditLogService auditLogService;

    /**
     * 声名OAuth2RestTemplate
     * 会从请求的上下文里拿到jwt令牌，放到请求头里，发出去。需要两个参数，springboot会自动出入进来
     * @param resource
     * @param context
     * @return
     */
    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context){
        return new OAuth2RestTemplate(resource,context);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //配置资源服务器的id，“现在我就是资源服务器 gateway-server！！！”
        resources.resourceId("gateway-server");

//        resources
//                //自定义处理401
//                .authenticationEntryPoint(authenticationEntryPoint)
//                //自定义403没有权限的处理逻辑
//                .accessDeniedHandler(accessDeniedHandler)
//                //注入自己的 表达式处理器
//                .expressionHandler(gatewayWebSecurityExpressionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //放过/token开头的请求，是在申请令牌
                .antMatchers("/token/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login","/resources/**", "/index.html", "/**/register").permitAll()
                .anyRequest()
                /**
                 * 指定权限访问规则，permissionService需要自己实现，返回布尔值，true-能访问；false-无权限
                 * 传进去2个参数，1-当前请求 ，2-当前用户
                 */
                .access("#permissionService.hasPermission(request, authentication)");
        ;
    }
}
