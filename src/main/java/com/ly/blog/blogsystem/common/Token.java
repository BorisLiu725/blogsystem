package com.ly.blog.blogsystem.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BorisLiu on 2019/9/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String token;
    private long expiration;
}
