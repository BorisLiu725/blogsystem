package com.ly.blog.blogsystem;

import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.service.UserFollowRedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogsystemApplicationTests {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;



    User user = new User();

    @Before
    public void before(){
        user.setUserName("hahaha");
        user.setEmail("25021654");
        user.setUpdateTime(new Date());
        user.setPassword("hahaha");
    }

    @Test
    public void contextLoads() {
        //set和get这两个连接可能属于不同的连接

        redisTemplate.opsForValue().set("myUser",user,2L,TimeUnit.HOURS);
        System.out.println(redisTemplate.opsForValue().get("myUser"));
    }

}
