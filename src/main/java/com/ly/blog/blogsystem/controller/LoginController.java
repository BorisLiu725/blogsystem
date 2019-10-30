package com.ly.blog.blogsystem.controller;

import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.common.Token;
import com.ly.blog.blogsystem.dto.LoginInfo;
import com.ly.blog.blogsystem.dto.RegisterDTO;
import com.ly.blog.blogsystem.enumeration.UserCheckEnum;
import com.ly.blog.blogsystem.exception.UserException;
import com.ly.blog.blogsystem.properties.JWTProperties;
import com.ly.blog.blogsystem.service.JWTService;
import com.ly.blog.blogsystem.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;


@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTProperties jwtProperties;


    @Autowired
    private JWTService jwtService;


    @ApiOperation(value="用户登陆",notes = "根据用户名密码登录")
    @ApiImplicitParam(name = "user",value = "用户信息",required = true)
    @PostMapping(value = "/login")
    public void login(@RequestBody User user, HttpServletResponse
            response) {
        User record = userService.findByUsernameAndPassword(user.getUserName(), user.getPassword());
        if(record!=null){
                //把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带cookie参数
                //String token = jwtProvider.createJWT("1", res.getUserName(), jwtProperties.getExpireTime());
                Token token = jwtService.refreshToken(record.getUserId());
                System.out.println("token为："+token.getToken());
                response.setHeader("Access-Control-Expose-Headers", "Authorization");
                response.setHeader("Authorization","Bearer "+token);
        }else{
           throw new UserException("用户名或密码错误！");
        }
    }


    @ApiOperation(value = "用户注册",notes ="注册需要邮箱" )
    @ApiImplicitParam(name = "registerDTO",value = "用户注册信息",required = true)
    @PostMapping(value = "/register")
    public void register(@Validated @RequestBody RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
    }

    @ApiOperation(value = "检查注册用户是否重复")
    @ApiImplicitParam(name = "type",value = "检查类型",required = true)
    @PostMapping(value = "/check")
    public boolean check(int type,Object target){
        UserCheckEnum userCheckEnum = UserCheckEnum.fromValue(type);
        if(Objects.isNull(userCheckEnum)){
            throw new UserException("参数错误！无该类型");
        }
       return userService.check(userCheckEnum,target);
    }




}