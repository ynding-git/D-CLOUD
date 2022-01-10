package com.ynding.cloud.route.gateway.config.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * <p> JWT 认证过滤器， 对token 进行校验</p>
 *
 * @author ynding
 * @version 2022/1/10
 **/
@Component
@Slf4j
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private TokenStore tokenStore;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(a -> a instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap(accessToken -> {
                    OAuth2AccessToken oAuth2AccessToken = this.tokenStore.readAccessToken(accessToken);
                    // 根据access_token 从数据库获取不到OAuth2AccessToken
                    if (oAuth2AccessToken == null) {
                        return Mono.error(new InvalidTokenException("无效的Token"));
                    } else if (oAuth2AccessToken.isExpired()) {
                        return Mono.error(new InvalidTokenException("Token已过期！"));
                    }
                    OAuth2Authentication oAuth2Authentication = this.tokenStore.readAuthentication(accessToken);
                    if (oAuth2AccessToken == null) {
                        return Mono.error(new InvalidTokenException("无效的Token"));
                    } else {
                        return Mono.just(oAuth2Authentication);
                    }
                }).cast(Authentication.class);
    }
}
