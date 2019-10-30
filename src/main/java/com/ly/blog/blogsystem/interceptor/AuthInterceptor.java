package com.ly.blog.blogsystem.interceptor;

import com.ly.blog.blogsystem.common.CurrentUserData;
import com.ly.blog.blogsystem.common.RedisKeys;
import com.ly.blog.blogsystem.service.JWTService;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by BorisLiu on 2019/9/25
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       if (HttpMethod.OPTIONS.name().equals(request.getMethod())){
           return true;
       }
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
       if (StringUtils.isNotEmpty(header)){
           if (header.startsWith("Bearer ")){
               String token = header.substring(7);
               Optional<Claims> claimsOptional = jwtService.parseToken(token);
               //判断这个不为空
               if (claimsOptional.isPresent()){
                   String userId = claimsOptional.get().getSubject();
                   if (BooleanUtils.isTrue(redisTemplate.hasKey(String.format(RedisKeys.USER_JWT_KEY,userId)))){
                       CurrentUserData currentUserData = new CurrentUserData();
                       currentUserData.setUserId(userId);
                       request.setAttribute("currentUser", currentUserData);
                       return true;
                   }
               }
           }

       }

       logger.warn("Wrong authorization: {}", header);
       response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }
}
