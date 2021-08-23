package com.ynding.cloud.route.zuul.config.security;

import com.ynding.cloud.route.zuul.service.IPermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

/**
 * <p> 表达式处理器</p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
@Component
public class GatewayWebSecurityExpressionHandler extends OAuth2WebSecurityExpressionHandler {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 创建评估上下文
     */
    @Override
    protected StandardEvaluationContext createEvaluationContextInternal(Authentication authentication, FilterInvocation invocation) {
        //调用父类，创建一个标准的评估上下文
        StandardEvaluationContext sec = super.createEvaluationContextInternal(authentication, invocation);
        //GatewaySecurityConfig.configure(HttpSecurity http).access里面写的spring就认识了
        sec.setVariable("permissionService", permissionService);
        return sec;
    }
}
