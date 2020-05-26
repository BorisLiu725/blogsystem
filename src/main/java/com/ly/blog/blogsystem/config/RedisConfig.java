package com.ly.blog.blogsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * Created by BorisLiu on 2019/11/10
 *
 * 如果我们使用
 * @Resource
 *     private RedisTemplate<String,Object>redisTemplate;
 *     的话，会采用默认的序列化器
 *     JdkSerializationRedisSerializer
 *     会造成乱码
 *
 *
 *      RedisTemplate<String,String>redisTemplate;
 *      StringRedisTemplate-->public static final StringRedisSerializer UTF_8 = new StringRedisSerializer(StandardCharsets.UTF_8);
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Serializable> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Serializable> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

}
