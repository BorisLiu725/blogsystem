package com.ly.blog.blogsystem.bean;

import com.ly.blog.blogsystem.enumeration.CommentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by BorisLiu on 2019/10/7
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private Date time;
    private String content;
    private String userId;
    private Integer articleId;
    private Long fatherComment;
    private CommentState state;
    private String ipAddress;

}
