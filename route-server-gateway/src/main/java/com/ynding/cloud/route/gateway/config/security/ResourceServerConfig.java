package com.ynding.cloud.route.gateway.config.security;

import com.ynding.cloud.auth.api.authentication.service.IAuthService;
import com.ynding.cloud.common.model.bo.ResponseCode;
import com.ynding.cloud.route.gateway.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.filter.CorsFilter;
import reactor.core.publisher.Mono;

/**
 * <p> 资源服务器配置</p>
 *
 * @author ynding
 * @version 2021/7/26
 **/
@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
@ComponentScan(basePackages = "com.ynding.cloud.auth.api.authentication")
public class ResourceServerConfig {
    private final AuthorizationManager accessManager;
    private final IAuthService authService;
    private final ReactiveAuthenticationManager jwtAuthenticationManager;

    /**
     * JWT采用非对称加密，不需要配置公钥
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
//                .publicKey(rsaPublicKey()) // 本地获取公钥
        //.jwkSetUri() // 远程获取公钥
        ;
        // 自定义处理JWT请求头过期或签名错误的结果
//        http.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());
        // 认证过滤器
        AuthenticationWebFilter authorizationWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authorizationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());

        http.httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                // 白名单
                .pathMatchers(authService.ignoreUrls()).permitAll()
                .anyExchange().access(accessManager)
                .and().exceptionHandling()
                // 处理未授权
                .accessDeniedHandler(accessDeniedHandler())
                //处理未认证
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                // 跨域过滤器
//                .addFilterAt(corsFilter, SecurityWebFiltersOrder.CORS)
                // token的认证过滤器，用于校验token和认证
                .addFilterAt(authorizationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

    /**
     * 自定义未授权响应401
     */
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> ResponseUtils.writeErrorInfo(response, ResponseCode.ACCESS_UNAUTHORIZED));
            return mono;
        };
    }

    /**
     * token无效或者已过期自定义响应402
     */
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> ResponseUtils.writeErrorInfo(response, ResponseCode.TOKEN_TIMEOUT_CODE));
            return mono;
        };
    }

    /**
     * @link https://blog.csdn.net/qq_24230139/article/details/105091273
     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
     * 需要把jwt的Claim中的authorities加入
     * 方案：重新定义权限管理器，默认转换器JwtGrantedAuthoritiesConverter
     */
    /*@Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.JWT_AUTHORITIES_KEY);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }*/

    /**
     * 本地获取JWT验签公钥
     */
    /*@SneakyThrows
    @Bean
    public RSAPublicKey rsaPublicKey() {
        Resource resource = new ClassPathResource("public.key");
        InputStream is = resource.getInputStream();
        String publicKeyData = IoUtil.read(is).toString();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((Base64.decode(publicKeyData)));

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        return rsaPublicKey;
    }*/

}
