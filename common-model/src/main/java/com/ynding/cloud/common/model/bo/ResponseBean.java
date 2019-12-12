package com.ynding.cloud.common.model.bo;

/**
 * 返回数据bean
 */
public class ResponseBean<T> {
    private int code = 200;
    private String message = "";
    private T data;

    public ResponseBean() {
    }

    public ResponseBean(T data) {
        this.data = data;
    }

    public ResponseBean(String message) {
        this.message = message;
    }

    public ResponseBean(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ResponseBean(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResponseBean(int code) {
        this.code = code;
    }

    public ResponseBean(IResponseCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ResponseBean(T data, IResponseCode errorCode) {
        this.data = data;
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static ResponseBean ok() {
        return new ResponseBean();
    }

    public static <T> ResponseBean<T> ok(T data) {
        return new ResponseBean(data);
    }
    public static <T> ResponseBean<T> ok(String message,T data) {
        return new ResponseBean(message,data);
    }

    public static <T> ResponseBean<T> fail() {
        return new ResponseBean();
    }

    public static <T> ResponseBean<T> fail(IResponseCode errorCode) {
        return new ResponseBean(errorCode);
    }

    public static <T> ResponseBean<T> fail(IResponseCode errorCode, T data) {
        return new ResponseBean(data, errorCode);
    }

    public static <T> ResponseBean<T> fail(String message) {
        return new ResponseBean(message);
    }

    public static <T> ResponseBean<T> fail(int code) {
        return new ResponseBean(code);
    }

    public static <T> ResponseBean<T> fail(String message, int code) {
        return new ResponseBean(message, code);
    }

    public int getCode() {
        return this.code;
    }

    public ResponseBean<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ResponseBean<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ResponseBean<T> setData(T data) {
        this.data = data;
        return this;
    }
}
