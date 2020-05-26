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
public class FollowDTO {
    private Long followCount; //关注
    private Long fansCount; //粉丝
    private Long articlesCount; //文章
}
