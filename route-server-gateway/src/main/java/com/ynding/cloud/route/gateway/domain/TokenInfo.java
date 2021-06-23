package com.ynding.cloud.route.gateway.domain;

import lombok.Data;

import java.util.Date;

/**
 * <p> 包装从认证服务器获取token信息响应对象</p>
 *
 * @author dyn
 * @version 2020/10/9
 * @since JDK 1.8
 */
@Data
public class TokenInfo {
    /**
     * token是否可用
     */
    private boolean active;

    /**
     * 令牌发给那个客户端应用的 客户端id
     */
    private String clientId;

    /**
     * 令牌scope
     */
    private String[] scope;

    /**
     * 用户名
     */
    private String username;

    /**
     * 令牌能访问哪些资源服务器，资源服务器的id
     */
    private String[] aud;
    /**
     * 令牌过期时间
     */
    private Date exp;
    /**
     * 令牌对应的user的 权限集合 UserDetailsService里loadUserByUsername()返回的User的权限集合
     */
    private String[] authorities;
}
