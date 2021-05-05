package com.springcloud.learn.example.orderserviceprovider.exception;

import com.springcloud.learn.example.api.R;
import com.springcloud.learn.example.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handleException(Exception exception, HttpServletRequest request){
        log.info("GlobalExceptionHandler.handleException:{},{}", request.getRequestURI(),exception);
        String msg = "系统繁忙：" + exception.getMessage();
        if (exception instanceof ValidationException){
            msg = exception.getMessage();
        }
        return new R.Builder<>().builderCustomize(msg);
    }
}
