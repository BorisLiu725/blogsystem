package com.ly.blog.blogsystem.controller;

import com.ly.blog.blogsystem.common.CurrentUser;
import com.ly.blog.blogsystem.common.CurrentUserData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BorisLiu on 2019/9/11
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value="用户信息测试",notes = "根据用户名密码登录")
    @GetMapping("/api/userinfo")
    public String user(@CurrentUser CurrentUserData currentUserData){
        logger.info("test method: {}",currentUserData.getUserId());
        return "hahahhaha";
    }




}
