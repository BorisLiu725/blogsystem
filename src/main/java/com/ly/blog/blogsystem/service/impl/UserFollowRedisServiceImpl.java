package com.ly.blog.blogsystem.service.impl;

import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.dto.FansUserDTO;
import com.ly.blog.blogsystem.dto.FollowDTO;
import com.ly.blog.blogsystem.dto.FollowUserDTO;
import com.ly.blog.blogsystem.mapper.ArticleMapper;
import com.ly.blog.blogsystem.service.UserFollowRedisService;
import com.ly.blog.blogsystem.service.UserService;
import com.ly.blog.blogsystem.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by BorisLiu on 2019/12/15
 *
 */
@Service
@Slf4j
public class UserFollowRedisServiceImpl implements UserFollowRedisService {

    @Autowired
    private RedisHelper redisHelper;

    private static final String MY_FAN = "USER_%s:myfans";

    private static final String MY_FOLLOWS = "USER_%s:myfollows";

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean isFollowed(String followId, User user) {
        String myFollowsKey = String.format(MY_FOLLOWS, user.getUserId());
        Object res = redisHelper.hget(myFollowsKey, String.valueOf(followId));
        return res != null;
    }

    @Override
    public boolean isFollowedByFollowId(String followId, User user) {
        String myFollowsKey = String.format(MY_FOLLOWS, followId);
        Object res = redisHelper.hget(myFollowsKey, String.valueOf(user.getUserId()));
        return res != null;
    }

    @Override
    public void follow(String followId, User user) {

        User followUser = userService.findById(followId);
        log.info("==>follow：followId:{},user:{},followUser:{}",followId,user,followUser);
        //将user添加到followId的follow中
        String myFollowsKey = String.format(MY_FOLLOWS, user.getUserId());
        System.out.println("myFollowsKey: ========>"+myFollowsKey);

        redisHelper.hset(myFollowsKey, String.valueOf(followUser.getUserId()),followUser.getUserName());

        //followId的粉丝添加上
        String followFansKey = String.format(MY_FAN, followId);
        redisHelper.hset(followFansKey, String.valueOf(user.getUserId()),user.getUserName());

    }

    @Override
    public void unFollow(String followId, User user) {
        User followUser = userService.findById(followId);
        log.info("==>unFollow：followId:{},user:{},followUser:{}",followId,user,followUser);

        String myFollowsKey = String.format(MY_FOLLOWS, user.getUserId());
        redisHelper.hdel(myFollowsKey, String.valueOf(followUser.getUserId()),followUser.getUserName());


        String followFansKey = String.format(MY_FAN, followId);
        redisHelper.hdel(followFansKey, String.valueOf(user.getUserId()),user.getUserName());

    }

    @Override
    public List<FollowUserDTO> myFollows(String userId) {
        String myFollowsKey = String.format(MY_FOLLOWS, userId);
        Map<Object, Object> res = redisHelper.getHash(myFollowsKey);

        FollowUserDTO followUserDTO = null;
        List<FollowUserDTO> lists = new ArrayList<>();

        for(Map.Entry<Object, Object> entry : res.entrySet()){
            followUserDTO = new FollowUserDTO(entry.getKey().toString(),entry.getValue().toString());
            lists.add(followUserDTO);
        }

        return lists;

    }

    @Override
    public List<FansUserDTO> myFans(String userId) {
        String myFansKey = String.format(MY_FAN, userId);
        Map<Object, Object> res = redisHelper.getHash(myFansKey);
        User user = userService.findById(userId);
        FansUserDTO fansUserDTO = null;
        List<FansUserDTO> lists = new ArrayList<>();

        for(Map.Entry<Object, Object> entry : res.entrySet()){
            boolean followed = isFollowed(entry.getKey().toString(), user);
            fansUserDTO = new FansUserDTO(entry.getKey().toString(),entry.getValue().toString(),followed);
            lists.add(fansUserDTO);
        }
        return lists;
    }

    @Override
    public Long myFollowCount(String userId) {
        String myFollowsKey = String.format(MY_FOLLOWS, userId);
        return  redisHelper.hLen(myFollowsKey);
    }

    @Override
    public FollowDTO myFollowInf(String userId) {
        String myFansKey = String.format(MY_FAN, userId);
        String myFollowsKey = String.format(MY_FOLLOWS, userId);
        FollowDTO followDTO = new FollowDTO();
        followDTO.setArticlesCount((long) articleMapper.findAllByUserId(userId).size());
        followDTO.setFansCount(redisHelper.hLen(myFansKey));
        followDTO.setFollowCount(redisHelper.hLen(myFollowsKey));
        return followDTO;
    }

    @Override
    public Long myFansCount(String userId) {
        String myFansKey = String.format(MY_FAN, userId);
        return  redisHelper.hLen(myFansKey);
    }

}
