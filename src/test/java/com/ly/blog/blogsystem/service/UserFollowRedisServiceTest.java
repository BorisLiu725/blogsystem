package com.ly.blog.blogsystem.service;

import com.ly.blog.blogsystem.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by BorisLiu on 2019/12/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFollowRedisServiceTest {

    @Autowired
    private UserFollowRedisService userFollowRedisService;

    @Autowired
    private UserService userService;

    @Test
    public void isFollowed() {

    }

    @Test
    public void isFollowedByFollowId() {
    }

    @Test
    public void follow() {
       String userId = "273a79f4f6d74372a6923ffee0a46de2";
       String followId = "376b56309cd943e5af729e15777681c9";
        User user = userService.findById(userId);
        userFollowRedisService.follow(followId,user);

    }

    @Test
    public void unFollow() {
    }

    @Test
    public void myFollows() {


    }

    @Test
    public void myFans() {
    }

    @Test
    public void myFollowCount() {
    }

    @Test
    public void myFansCount() {
    }
}