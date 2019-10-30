package com.ly.blog.blogsystem.exception;



/**
 * Created by BorisLiu on 2019/9/11
 */
public class ServiceException extends BaseException {
    public ServiceException(String message){
        super(-2,message);
    }
}
