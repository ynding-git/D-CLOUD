package com.ynding.cloud.route.gateway.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * <p> 资源服务器配置</p>
 *
 * @author dyn
 * @version 2020/10/28
 * @since JDK 1.8
 */
@Slf4j
@Configuration
@EnableResourceServer
public class GatewayAdminResourceConfig extends ResourceServerConfigurerAdapter {

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


}
