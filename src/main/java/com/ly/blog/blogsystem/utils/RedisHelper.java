package com.ly.blog.blogsystem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by BorisLiu on 2019/12/15
 */
@Component
public class RedisHelper {

    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;

    public Map<Object, Object> getHash(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    public void hset(String key, String hashKey, Object value){
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void hdel(String key, String...hashKeys) {    //...代表可以有一个参数，也可以有多个参数！
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public Long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    public Object hget(String key,String hashKey) {
        return redisTemplate.opsForHash().get(key,hashKey);
    }
}
