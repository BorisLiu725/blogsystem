package com.ly.blog.blogsystem.controller;

import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.common.CurrentUser;
import com.ly.blog.blogsystem.common.CurrentUserData;
import com.ly.blog.blogsystem.dto.FansUserDTO;
import com.ly.blog.blogsystem.dto.FollowDTO;
import com.ly.blog.blogsystem.dto.FollowUserDTO;
import com.ly.blog.blogsystem.exception.UserException;
import com.ly.blog.blogsystem.service.UserFollowRedisService;
import com.ly.blog.blogsystem.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BorisLiu on 2019/9/11
 */
@RestController
@RequestMapping("/follows")
public class FollowController {

    private Logger logger = LoggerFactory.getLogger(FollowController.class);

    @Autowired
    private UserFollowRedisService userFollowRedisService;

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户信息测试",notes = "根据用户名密码登录")
    @GetMapping("/isFollowed/{followId}")
    public boolean isFollowed(@PathVariable("followId") String followId,@CurrentUser CurrentUserData currentUserData){
        User user = userService.findById(currentUserData.getUserId());
        logger.info("CurrentUser==>"+currentUserData+"===>"+currentUserData.getUserId()+"===>"+user+"==>"+followId);
        if (Objects.isNull(user) || Objects.isNull(followId)){
            throw new UserException("没有该用户");
        }
       return userFollowRedisService.isFollowed(followId,user);
    }

    @ApiOperation(value="***",notes = "**")
    @GetMapping("/isFollowedByFollowId/{followId}")
    public boolean isFollowedByFollowId(@PathVariable("followId") String followId, @CurrentUser CurrentUserData currentUserData){
        User user = userService.findById(currentUserData.getUserId());
        logger.info("CurrentUser==>"+currentUserData+"===>"+currentUserData.getUserId()+"===>"+user+"==>"+followId);
        if (Objects.isNull(user) || Objects.isNull(followId)){
            throw new UserException("没有该用户");
        }
        return userFollowRedisService.isFollowedByFollowId(followId,user);
    }


    @ApiOperation(value="****",notes = "***")
    @GetMapping("/api/userinfo")
    public String user(@CurrentUser CurrentUserData currentUserData){
        logger.info("test method: {}",currentUserData.getUserId());
        return "hahahhaha";
    }

    @ApiOperation(value="关注",notes = "**")
    @GetMapping(value = "/follow/{followId}")
    public void follow(@PathVariable("followId") String followId, @CurrentUser CurrentUserData currentUserData){

        User user = userService.findById(currentUserData.getUserId());
        logger.info("CurrentUser==>"+currentUserData+"===>"+currentUserData.getUserId()+"===>"+user+"==>"+followId);
        if (Objects.isNull(user) || Objects.isNull(followId)){
            throw new UserException("没有该用户");
        }
        userFollowRedisService.follow(followId,user);
    }

    @ApiOperation(value="取消关注",notes = "**")
    @GetMapping("/unFollow/{followId}")
    public void unFollow(@PathVariable("followId") String followId, @CurrentUser CurrentUserData currentUserData){
        User user = userService.findById(currentUserData.getUserId());
        if (Objects.isNull(user) || Objects.isNull(followId)){
            throw new UserException("没有该用户");
        }
        userFollowRedisService.unFollow(followId,user);
    }

    @ApiOperation(value="我的关注",notes = "**")
    @GetMapping("/myFollows")
    public List<FollowUserDTO> myFollows(@CurrentUser CurrentUserData currentUserData){
        return userFollowRedisService.myFollows(currentUserData.getUserId());
    }

    @ApiOperation(value="我的粉丝",notes = "**")
    @GetMapping("/myFans")
    public List<FansUserDTO> myFans(@CurrentUser CurrentUserData currentUserData){
        return userFollowRedisService.myFans(currentUserData.getUserId());
    }

    @ApiOperation(value="我的关注数量",notes = "**")
    @GetMapping("/myFollowCount")
    public Long  myFollowCount(@CurrentUser CurrentUserData currentUserData){
        return userFollowRedisService.myFollowCount(currentUserData.getUserId());
    }

    @ApiOperation(value="我的粉丝数量",notes = "**")
    @GetMapping("/myFansCount")
    public Long myFansCount(@CurrentUser CurrentUserData currentUserData){
        return userFollowRedisService.myFansCount(currentUserData.getUserId());
    }


    @ApiOperation(value="我们各种数量",notes = "**")
    @GetMapping("/myFollowsInf")
    public FollowDTO myFollowsInf(@CurrentUser CurrentUserData currentUserData){
        FollowDTO followDTO = userFollowRedisService.myFollowInf(currentUserData.getUserId());
        return followDTO;
    }


}
