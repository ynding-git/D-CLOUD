package com.ynding.cloud.route.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 不加这个过滤器，会一直报限流错误
 * @author ynding
 * 限流过滤器
 */
public class GatewayRateLimitFilter extends OncePerRequestFilter {

    /**
     * 每秒100个请求的限流器
     */
    private RateLimiter rateLimiter = RateLimiter.create(100);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.err.println("0 rate  limit ");

        if(rateLimiter.tryAcquire()){
            filterChain.doFilter(request,response);
        }else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"too many request\"}");
            response.getWriter().flush();
            return;
        }
    }
}
