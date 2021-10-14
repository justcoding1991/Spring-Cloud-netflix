package com.springcloud.learn.example.api;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public R(Builder<T> builder){
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static class Builder<T>{
        private int code;
        private String msg;
        private T data;

        public Builder(){

        }
        public R builderCustomize(String msg){
            this.code = -1;
            this.msg = msg;
            return new R(this);
        }
        public R builderCustomize(int code, String msg){
            this.code = code;
            this.msg = msg;
            return new R(this);
        }

        public R buildOk(){
            this.code = 200;
            this.msg = "success";
            return new R(this);
        }

        public R buildFail(){
            this.code = -1;
            this.msg = "failed";
            return new R(this);
        }

        public Builder setData(T data){
            this.data = data;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}
