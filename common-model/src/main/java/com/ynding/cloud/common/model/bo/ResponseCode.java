package com.ynding.cloud.common.model.bo;

/**
 * 返回码
 */
public enum ResponseCode implements IResponseCode{
    SUCCESS_CODE(200, ""),
    PARAM_ERROR_CODE(400, "param_error"),
    LIMIT_ERROR_CODE(401, "limit_error"),
    TOKEN_TIMEOUT_CODE(402, "token_expired"),
    NO_AUTH_CODE(403, "forbidden"),
    NOT_FOUND(404, "not_found"),
    SERVER_ERROR_CODE(500, "server_error"),
    USER_AlREAD_EXIST_EXCEPTION(401,"用户已经存在"),
    DOWNGRADE(406, "downgrade"),
    BOOK_FEIGN_ERROR(300,"调用book服务失败")
    ;

    private int code;
    private String message;

    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
