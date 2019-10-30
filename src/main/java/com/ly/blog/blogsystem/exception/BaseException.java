package com.ly.blog.blogsystem.exception;

/**
 * Created by BorisLiu on 2019/9/26
 */
public abstract class BaseException extends RuntimeException{

    private int code;

    public BaseException(int code,String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
