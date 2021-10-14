package com.springcloud.learn.example.exception;

public class ValidationException extends BaseException {

    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
        this.msg = message;
    }

    public ValidationException(String message, String code) {
        super(message, code);
    }
}
