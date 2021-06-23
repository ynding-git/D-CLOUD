package com.ynding.cloud.route.gateway.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * access_token
 * @author ynding
 **/
@Data
public class AccessToken {

    private String access_token;

    private String refresh_token;

    private String token_type;

    /**
     * 过期时间 秒
     */
    private Long expires_in;

    private String scope;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * @return 设置token的失效日期
     */
    public AccessToken init(){
        //expires_in -3 秒，在token失效之前就失效
        expireTime = LocalDateTime.now().plusSeconds(expires_in -3);
        return this;
    }

    /**
     * @return 令牌是否过期
     */
    public boolean isExpired(){
        return expireTime.isBefore(LocalDateTime.now());
    }


}
