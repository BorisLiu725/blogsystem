package com.ly.blog.blogsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BorisLiu on 2019/10/9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentFormatLsitVO {
    private Long commentId;
    private String fromUserName;
    private String toUserName;
    private String content;
    private Integer articleId;
}
