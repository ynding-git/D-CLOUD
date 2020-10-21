package com.ynding.cloud.route.zuul.service.impl;

import com.ynding.cloud.route.zuul.service.IPermissionService;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * @author dyn
 * @version 2020/9/27
 * @since JDK 1.8
 */
@Service
@Slf4j
public class PermissionServiceImpl implements IPermissionService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //查数据库、查redis、调远程服务、或者内存里面的权限信息
        log.error(request.getRequestURI());
        log.error(ReflectionToStringBuilder.toString(authentication));
        //这里模拟,有一半的可能性访问失败，一半可能性成功
//        return RandomUtils.nextInt() % 2 ==0;
        return true;
    }
}
