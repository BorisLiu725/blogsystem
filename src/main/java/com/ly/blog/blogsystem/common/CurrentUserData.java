package com.ly.blog.blogsystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by BorisLiu on 2019/9/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserData implements Serializable {
    private String userId;
    private String token;
}
