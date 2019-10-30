package com.ly.blog.blogsystem.handler;

import com.github.pagehelper.PageInfo;
import com.ly.blog.blogsystem.exception.BaseException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * Created by BorisLiu on 2019/10/7
 */
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (Objects.isNull(body)){
            return ResInfo.ok();
        }
        if (body instanceof PageInfo){
            return ResInfo.result(((PageInfo) body).getList());
        }
        if (body instanceof BaseException){
            return ResInfo.myError(((BaseException) body).getMessage());
        }
        return ResInfo.result(body);
    }
}
