package com.ynding.cloud.route.gateway.config;

import com.ynding.cloud.route.gateway.config.security.AuthorizationManager;
import com.ynding.cloud.route.gateway.config.security.CustomServerAccessDeniedHandler;
import com.ynding.cloud.route.gateway.config.security.CustomServerAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * <p> 资源服务器配置</p>
 *
 * @author ynding
 * @version 2021/7/26
 **/
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final AuthorizationManager accessManager;
    private final CustomServerAccessDeniedHandler customServerAccessDeniedHandler;
    private final CustomServerAuthenticationEntryPoint customServerAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        // 自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(customServerAuthenticationEntryPoint);
        http.authorizeExchange()
//                .pathMatchers(ArrayUtil.toArray(whiteListConfig.getUrls(),String.class)).permitAll()
                .anyExchange().access(accessManager)
                .and()
                .exceptionHandling()
                // 处理未授权
                .accessDeniedHandler(customServerAccessDeniedHandler)
                //处理未认证
                .authenticationEntryPoint(customServerAuthenticationEntryPoint)
                .and().csrf().disable();
        return http.build();
    }

}
