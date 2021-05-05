package com.springcloud.learn.example.exception;

public class BaseException extends RuntimeException {

    protected String code;
    protected String msg;

    public BaseException(){
        super();
    }

    public BaseException(String message){
        super(message);
        this.msg = message;
    }

    public BaseException(String message, String code) {
        super(message);
        this.code = code;
        this.msg = message;
    }
}
