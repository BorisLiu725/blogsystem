package com.ly.blog.blogsystem.controller;

import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.common.CurrentUser;
import com.ly.blog.blogsystem.common.CurrentUserData;
import com.ly.blog.blogsystem.common.Token;
import com.ly.blog.blogsystem.dto.LoginInfo;
import com.ly.blog.blogsystem.dto.RegisterDTO;
import com.ly.blog.blogsystem.dto.UserCheckDTO;
import com.ly.blog.blogsystem.dto.UserInfoDTO;
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
import java.util.List;
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
                response.setHeader("Authorization","Bearer "+token.getToken());
        }else{
           throw new UserException("用户名或密码错误！");
        }
    }


    @ApiOperation(value = "用户注册",notes ="注册需要邮箱" )
    @ApiImplicitParam(name = "registerDTO",value = "用户注册信息",required = true)
    @PostMapping(value = "/register")
    public void register(@Validated @RequestBody RegisterDTO registerDTO) {
        boolean userNameflag = userService.check(UserCheckEnum.USER_NAME, registerDTO.getUserName());
        boolean emailFlag = userService.check(UserCheckEnum.EMAIL, registerDTO.getEmail());
        if (userNameflag&&emailFlag){
            userService.registerUser(registerDTO);
        }else {
            throw new UserException("用户名或邮箱重复！");
        }
    }

    @ApiOperation(value = "检查注册用户是否重复")
    @ApiImplicitParam(name = "type",value = "检查类型",required = true)
    @PostMapping(value = "/check")
    public boolean check(@RequestBody UserCheckDTO userCheck){
        UserCheckEnum userCheckEnum = UserCheckEnum.fromValue(userCheck.getType());
        if(Objects.isNull(userCheckEnum)){
            throw new UserException("参数错误！无该类型");
        }
       return userService.check(userCheckEnum,userCheck.getTarget());
    }

    @ApiOperation(value = "根据token查询用户")
    @GetMapping(value = "/find/userInfo")
    public User check(@CurrentUser CurrentUserData currentUserData){
        User user = userService.findByToken(currentUserData.getUserId());
        if(Objects.isNull(user)){
            throw new UserException("该token失效！");
        }
        return user;
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping(value = "/update/userInfo")
    public boolean updateUserById(@RequestBody User user,@CurrentUser CurrentUserData currentUserData){
        return userService.updateUserById(user);
    }

    @ApiOperation(value = "根据用户id修改用户状态")
    @PutMapping(value = "/update/state/userInfo")
    public boolean updateUserStateById(@RequestBody UserInfoDTO userInfoDTO, @CurrentUser CurrentUserData currentUserData){
       return userService.updateUserStateById(userInfoDTO.getUserId(), userInfoDTO.getState());
    }

    @ApiOperation(value = "根据用户id修改用户密码")
    @PutMapping(value = "/update/pass/userInfo")
    public boolean updateUserPasswordById(@RequestBody UserInfoDTO userInfoDTO, @CurrentUser CurrentUserData currentUserData){
        return userService.updatePasswordById(currentUserData.getUserId(), userInfoDTO.getOldPassword(),userInfoDTO.getNewPassword());
    }

    @ApiOperation(value = "查找所有的用户")
    @GetMapping(value = "/find/list")
    public List<User> getUsers(){
        return  userService.findAll();
    }

}