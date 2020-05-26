package com.ly.blog.blogsystem.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by BorisLiu on 2019/9/11
 */
public class CrossDomainFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CrossDomainFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("==>进入跨域拦截器");
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
