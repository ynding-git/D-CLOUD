package com.ynding.cloud.route.gateway.exception;

import com.ynding.cloud.common.exception.SystemErrorType;
import com.ynding.cloud.common.model.bo.ResponseBean;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


/**
 * @author Administrator
 */
@Slf4j
@Component
public class GateWayExceptionHandlerAdvice {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseBean handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.GATEWAY_ERROR);
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public ResponseBean handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.GATEWAY_CONNECT_TIME_OUT);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseBean handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return ResponseBean.fail(SystemErrorType.GATEWAY_NOT_FOUND_SERVICE);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return ResponseBean.fail();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return ResponseBean.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean handle(Throwable throwable) {
        ResponseBean result = ResponseBean.fail();
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
