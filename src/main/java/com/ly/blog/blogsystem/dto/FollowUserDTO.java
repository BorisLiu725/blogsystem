package com.ly.blog.blogsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BorisLiu on 2019/12/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowUserDTO {

    private String userId;

    private String userName;


}
