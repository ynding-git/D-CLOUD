package com.ynding.cloud.route.gateway.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p> 登录接受类</p>
 *
 * @author ynding
 * @version 2021/2/25
 **/
@Data
@ToString
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8184236717637461216L;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
