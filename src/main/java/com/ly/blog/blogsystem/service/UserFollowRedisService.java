package com.ly.blog.blogsystem.service;

import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.dto.FansUserDTO;
import com.ly.blog.blogsystem.dto.FollowDTO;
import com.ly.blog.blogsystem.dto.FollowUserDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by BorisLiu on 2019/12/15
 *
 * redis实现
 * 关注列表，粉丝列表，关注数，粉丝数，关注，取消关注
 *
 */
public interface UserFollowRedisService {

    /**
     * userId去关注别人，判断是否已关注
     *
     * 我  --我的粉丝id   粉丝名
     * */
    boolean isFollowed(String followId, User user);

    /**
     * followid 关注  别人
     * */
    boolean isFollowedByFollowId(String followId, User user);

    void follow(String followId, User user);

    void unFollow(String followId, User user);

    public List<FollowUserDTO> myFollows(String userId);

    public List<FansUserDTO> myFans(String userId);

    public Long  myFollowCount(String userId);

    public FollowDTO myFollowInf(String userId);

    public Long myFansCount(String userId);
}
