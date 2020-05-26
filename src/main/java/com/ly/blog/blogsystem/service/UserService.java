package com.ly.blog.blogsystem.service;


import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.dto.FollowDTO;
import com.ly.blog.blogsystem.dto.RegisterDTO;
import com.ly.blog.blogsystem.dto.UserInfoDTO;
import com.ly.blog.blogsystem.enumeration.UserCheckEnum;

import java.util.List;

/**
 * Created by BorisLiu on 2019/9/11
 */

public interface UserService {

     User findByUsernameAndPassword(String userName, String password);

    void registerUser(RegisterDTO registerDTO);

    boolean check(UserCheckEnum userCheckEnum,Object target);

    User findByToken(String userId);

    User findById(String userId);


    boolean updateUserById(User user);

    boolean updateUserStateById( String userId,Integer state);

    boolean updatePasswordById(String userId, String oldPassword,String newPassword);

    List<User> findAll();
}
