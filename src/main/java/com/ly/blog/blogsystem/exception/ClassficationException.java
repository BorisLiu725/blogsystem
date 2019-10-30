package com.ly.blog.blogsystem.exception;

import com.ly.blog.blogsystem.common.ResponseCodeConstant;

/**
 * Created by BorisLiu on 2019/10/6
 */
public class ClassficationException extends BaseException {
    public ClassficationException(String message) {
        super(ResponseCodeConstant.SERVICE_ERROR_CODE, message);
    }
}
