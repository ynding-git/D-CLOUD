package com.ynding.cloud.route.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ynding.cloud.route.zuul.domain.TokenInfo;
import com.ynding.cloud.route.zuul.entity.AuditLog;
import com.ynding.cloud.route.zuul.service.IAuditLogService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>授权过滤器 </p>
 *
 * @author dyn
 * @version 2020/10/9
 * @since JDK 1.8
 */
@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {

    @Autowired
    private IAuditLogService auditLogService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        //在审计过滤器后
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("authorization start");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();


        //判断是否需要认证
        if (isNeedAuth(request)) {
            //需要认证,从request取出AuthFilter放入的tokenInfo
            TokenInfo tokenInfo = (TokenInfo) request.getAttribute("tokenInfo");
            //不为空且为激活状态
            if (tokenInfo != null && tokenInfo.isActive()) {
                //认证成功，看是否有权限
                if (!hasPermission(tokenInfo, request)) {
                    //没有权限
                    log.info("audit log update fail 403 ");
                    //更新审计日志 ，403
                    Long auditLogId = (Long) request.getAttribute("auditLogId");
                    AuditLog log = auditLogService.getById(auditLogId);
                    log.setUpdateTime(new Date());
                    log.setStatus(403);
                    auditLogService.updateById(log);

                    handleError(403, requestContext);
                }
                //走到这里说明权限也通过了，将用户信息放到请求头，供其他微服务获取
                //这里甚至可以传一个对象的json，然后在微服务里用@RequestHeader注解就能获取
                requestContext.addZuulRequestHeader("username", tokenInfo.getUsername());


            } else {
                //不是以 /token开头的，才拦截，否则登录请求也就被拦截了。这里放过
                if (!StringUtils.startsWith(request.getRequestURI(), "/token")) {
                    //////////更新审计日志////////////////
                    log.info("audit log update fail 401 ");
                    Long auditLogId = (Long) request.getAttribute("auditLogId");
                    AuditLog log = auditLogService.getById(auditLogId);
                    log.setUpdateTime(new Date());
                    log.setStatus(401);
                    auditLogService.updateById(log);

                    //认证失败，没有tokenInfo，报错,修改审计日志状态
                    handleError(401, requestContext);
                }
            }
        }
        return null;
    }


    /**
     * 认证成功，看是否有权限 TODO：从数据库查询权限，这里直接返回
     */
    private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {
        //RandomUtils.nextInt() % 2 == 0;
        return true;
    }


    /**
     * 处理认证失败或者没有权限
     *
     * @param status http状态码
     */
    private void handleError(int status, RequestContext requestContext) {
        //响应json
        requestContext.getResponse().setContentType("application/json");
        //响应状态码
        requestContext.setResponseStatusCode(status);
        requestContext.setResponseBody("{\"message\":\"auth fail\"}");
        //这一句是说，当前过滤器到此返回，不会再往下走了、
        requestContext.setSendZuulResponse(false);
    }

    /**
     * 判断当前请求是否需要认证 TODO:查数据库判断权限
     */
    private boolean isNeedAuth(HttpServletRequest request) {
        return true;
    }
}
