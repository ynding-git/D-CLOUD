package com.ynding.cloud.route.gateway.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import com.ynding.cloud.auth.api.authentication.service.IAuthService;
import com.ynding.cloud.common.model.bo.AuthConstants;
import com.ynding.cloud.common.model.bo.ResponseCode;
import com.ynding.cloud.route.gateway.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;

/**
 * 请求url权限校验
 *
 * @author ynding
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AccessGatewayFilter implements GlobalFilter, Ordered {

    private final RedisTemplate redisTemplate;

    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.debug("url:{},method:{},headers:{}", url, method, request.getHeaders());
        // 白名单，不需要网关签权的url
       /* if (authService.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }*/
        // 非JWT或者JWT为空不作处理
        if (StringUtils.isBlank(authentication) || !authentication.startsWith(AuthConstants.JWT_TOKEN_PREFIX)) {
            log.debug("url:{},method:{},headers:{}, 请求未携带token信息", url, method, request.getHeaders());
            return chain.filter(exchange);
        }
        // 是否黑名单
        String token = authentication.replace(AuthConstants.JWT_TOKEN_PREFIX, Strings.EMPTY);
        JWSObject jwsObject = JWSObject.parse(token);
        String payload = jwsObject.getPayload().toString();
        JSONObject jsonObject = JSONUtil.parseObj(payload);
        String jti = jsonObject.getStr(AuthConstants.JWT_JTI);
        Boolean isBlack = redisTemplate.hasKey(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti);
        if (isBlack) {
            return ResponseUtils.writeErrorInfo(response, ResponseCode.TOKEN_ACCESS_FORBIDDEN);
        }

        // 存在token且不是黑名单，request写入JWT的载体信息
        request = exchange.getRequest().mutate()
                .header(AuthConstants.JWT_PAYLOAD_KEY, URLEncoder.encode(payload, "UTF-8"))
                .build();
        exchange = exchange.mutate().request(request).build();

        //调用签权服务看用户是否有权限，若有权限进入下一个filter
      /*  if (authService.hasPermission(authentication, url, method)) {
            return chain.filter(exchange);
        }*/
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
