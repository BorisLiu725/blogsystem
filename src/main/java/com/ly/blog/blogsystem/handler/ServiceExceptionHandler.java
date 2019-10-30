package com.ly.blog.blogsystem.handler;


import com.ly.blog.blogsystem.exception.ArticleException;
import com.ly.blog.blogsystem.exception.ClassficationException;


import com.ly.blog.blogsystem.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.LoginException;

/**
 * Created by BorisLiu on 2019/9/26
 */
@ControllerAdvice
@ResponseBody
public class ServiceExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler(UserException.class)
    public ResInfo loginExceptionResponse(UserException ex){
        logger.error("{}",ex.getMessage());
        HttpStatus.OK.name();
        return new ResInfo(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(ArticleException.class)
    public ResInfo articleExceptionResponse(ArticleException ex){
        logger.error("{}",ex.getMessage());
        return new ResInfo(ex.getCode(),ex.getMessage());
    }


    @ExceptionHandler(ClassficationException.class)
    public ResInfo classficationExceptionResponse(ClassficationException ex){
        logger.error("{}",ex.getMessage());
        return new ResInfo(ex.getCode(),ex.getMessage());
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String serverError(RuntimeException ex){
        logger.error("{}",ex);
        return "系统服务器出错！";
    }

}
