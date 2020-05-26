package com.ly.blog.blogsystem.dto;

import lombok.Data;

/**
 * Created by BorisLiu on 2019/12/18
 */
@Data
public class UserInfoDTO {
    private String userId;
    private Integer state;
    private String oldPassword;
    private String newPassword;
}
