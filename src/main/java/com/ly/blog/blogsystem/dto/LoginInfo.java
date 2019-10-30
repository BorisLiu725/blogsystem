package com.ly.blog.blogsystem.dto;

import com.ly.blog.blogsystem.handler.ResInfo;
import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * Created by BorisLiu on 2019/9/18
 */
@Data
public class LoginInfo extends ResInfo {
    private Claims claims;
}
