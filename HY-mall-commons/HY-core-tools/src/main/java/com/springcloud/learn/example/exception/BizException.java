package com.springcloud.learn.example.exception;

public class BizException extends BaseException{
    public BizException(){
        super();
    }

    public BizException(String message){
        super(message);
        this.msg = message;
    }

    public BizException(String message, String code) {
        super(message, code);
    }
}
