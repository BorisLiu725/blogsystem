package com.ly.blog.blogsystem.bean;

import com.ly.blog.blogsystem.bean.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by BorisLiu on 2019/9/11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String avatar;


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}