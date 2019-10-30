package com.ly.blog.blogsystem.exception;

import com.ly.blog.blogsystem.common.ResponseCodeConstant;

/**
 * Created by BorisLiu on 2019/9/26
 */
public class UserException extends BaseException{

    public UserException(String message) {
        super(ResponseCodeConstant.SERVICE_ERROR_CODE, message);
    }

}
