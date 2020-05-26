package com.ly.blog.blogsystem.config;

import com.ly.blog.blogsystem.common.CurrentUserHandlerMethodArgumentResolver;
import com.ly.blog.blogsystem.converter.IntegerToBaseEnumConverterFactory;
import com.ly.blog.blogsystem.filter.CrossDomainFilter;
import com.ly.blog.blogsystem.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BorisLiu on 2019/9/11
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域请求拦截器
     * */
    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CrossDomainFilter crossDomainFilter = new CrossDomainFilter();
        registrationBean.setFilter(crossDomainFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
//    @Bean
//    public FilterRegistrationBean registrationBean(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        JWTFilter jwtFilter = new JWTFilter();
//        registrationBean.setFilter(jwtFilter);
//        List<String> urls = new ArrayList<>();
//        urls.add("/api/*");
//        registrationBean.setUrlPatterns(urls);
//        return registrationBean;
//    }

    @Autowired
    public AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/register","/user/login","/user/check/**")
                .excludePathPatterns("/swagger-ui.html","/swagger-resources/**","/webjars/**","/swagger-ui.html/**","/v2/**")
                .excludePathPatterns("/article/find/**")
                .excludePathPatterns("/comment/find/**")
                .excludePathPatterns("/user/find/**")
                .excludePathPatterns("/classfication/find/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserHandlerMethodArgumentResolver());


    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new IntegerToBaseEnumConverterFactory());
    }
}
