package com.ynding.cloud.route.zuul.filter;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.DefaultRateLimiterErrorHandler;

import org.springframework.stereotype.Component;

/**
 * <p> 限流错误自定义处理</p>
 *
 * @author dyn
 * @version 2020/10/9
 * @since JDK 1.8
 */
@Component
public class MyRateLimitErrorHandler extends DefaultRateLimiterErrorHandler {
    /**
     * 限流数据存时候报错了的处理，一般不覆盖
     */
    @Override
    public void handleSaveError(String key, Exception e) {
        super.handleSaveError(key, e);
    }

    /**
     * 限流取数据报错的处理，一般不覆盖
     */
    @Override
    public void handleFetchError(String key, Exception e) {
        super.handleFetchError(key, e);
    }

    /**
     * 限流错误处理，记日志等
     */
    @Override
    public void handleError(String msg, Exception e) {
        super.handleError(msg, e);
    }
}
