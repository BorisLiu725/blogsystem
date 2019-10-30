package com.ly.blog.blogsystem.service;

import com.ly.blog.blogsystem.common.RedisKeys;
import com.ly.blog.blogsystem.common.Token;
import com.ly.blog.blogsystem.properties.JWTProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by BorisLiu on 2019/9/25
 */
@Service
public class JWTService {

    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

    @Autowired
    private JWTProperties jwtProperties;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     *  生成token
     * */
    private String generateToken(String subject,Map<String,Object> claim) {

        // 将密钥加密
        byte[] encodeKey = Base64.getEncoder().encode(jwtProperties.getSecretKey().getBytes());
        //加密后的密钥
        SecretKey secretKey = Keys.hmacShaKeyFor(encodeKey);
        // 用加密的密钥进行签名
        return Jwts.builder().addClaims(claim).setSubject(subject).signWith(secretKey).compact();
    }

    /**
     * 解析token
     * */
    public Optional<Claims> parseToken(String token){
        byte[] encode = Base64.getEncoder().encode(jwtProperties.getSecretKey().getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(encode);
        Claims claims = null;
        //用签名对token进行解密
        try{
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException e){
            logger.error("JwtException :{}",e.getMessage());
        }
        //为null就返回一个空的optional
        // 不为null返回optional
        return Optional.ofNullable(claims);

    }

    /**
     * 重新刷新token
     * */
    public Token refreshToken(String userId){
        Map<String, Object> claim = new HashMap<>();
        claim.put("userId",userId);
        claim.put("createDate",System.currentTimeMillis());
        Token token = new Token(generateToken(userId, claim),jwtProperties.getExpireTime().getSeconds());
        redisTemplate.opsForValue().set(String.format(RedisKeys.USER_JWT_KEY,userId),userId,token.getExpiration(),TimeUnit.SECONDS);
        return token;
    }
    /**
     * 重新刷新缓存
     * */
    public void removeToken(String token){
        Optional<Claims> claimsOptional = parseToken(token);
        claimsOptional.ifPresent(claim -> redisTemplate.delete(String.format(RedisKeys.USER_JWT_KEY,claim.get("userId"))));
    }

}
