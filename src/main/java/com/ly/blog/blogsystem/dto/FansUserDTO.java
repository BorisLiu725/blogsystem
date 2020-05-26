package com.ly.blog.blogsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BorisLiu on 2019/12/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FansUserDTO {
    private String userId;

    private String userName;

    private boolean followed;

}
