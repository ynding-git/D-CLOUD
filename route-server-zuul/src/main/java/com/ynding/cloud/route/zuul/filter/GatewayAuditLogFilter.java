package com.ynding.cloud.route.zuul.filter;

import com.ynding.cloud.route.zuul.entity.AuditLog;
import com.ynding.cloud.route.zuul.service.IAuditLogService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> 审计日志过滤器：
 * 流控 - 认证 - 审计 - 授权
 * 这里不要声名为spring的Component 如果声名了，springboot会自动把这个过滤器加在web过滤器链里，再自己配置其位置就会加两次。
 * </p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
@Slf4j
public class GatewayAuditLogFilter extends OncePerRequestFilter {

    private final IAuditLogService auditLogService;

    public GatewayAuditLogFilter(IAuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //认证过滤器会把jwt令牌转换为Authentication放在SecurityContext安全上下文里，Principal就是申请令牌的用户名
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //1,记录日志
        AuditLog log = new AuditLog();
        log.setUsername(username);
        log.setMethod(request.getMethod());
        log.setPath(request.getRequestURI());
        log.setCreateTime(new Date());
        auditLogService.save(log);
        System.err.println("1 记录日志 ：" + log.toString());
        //2,调用其他过滤器链
        filterChain.doFilter(request, response);
        //3,更新日志
        log.setUpdateTime(new Date());
        log.setStatus(response.getStatus());
        auditLogService.updateById(log);
        System.err.println("3 更新日志 ：" + log.toString());
    }
}
