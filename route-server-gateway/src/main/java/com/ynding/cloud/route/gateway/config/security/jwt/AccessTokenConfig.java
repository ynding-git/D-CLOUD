package com.ynding.cloud.route.gateway.config.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * <p> </p>
 *
 * @author ynding
 * @version 2022/1/10
 **/
@Configuration
public class AccessTokenConfig {

    /**
     * 令牌的存储策略
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 对jwt进行签名的key，jwt是明文，签名防篡改。
     * 接收token的人需要用同样的key验签名，需要把这个key通过服务暴漏出去，使用服务的人才能拿到key
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("ynding");
        return converter;
    }
}
