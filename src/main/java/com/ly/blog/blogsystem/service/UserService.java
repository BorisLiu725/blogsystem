package com.ly.blog.blogsystem.service;


import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.dto.RegisterDTO;
import com.ly.blog.blogsystem.enumeration.UserCheckEnum;

/**
 * Created by BorisLiu on 2019/9/11
 */

public interface UserService {

     User findByUsernameAndPassword(String userName, String password);

    void registerUser(RegisterDTO registerDTO);

    boolean check(UserCheckEnum userCheckEnum,Object target);
}
