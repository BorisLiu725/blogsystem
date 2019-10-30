package com.ly.blog.blogsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentFormatVO {
    private Long commentId;
    private String fromUserName;
    private String toUserName;
    private String content;
    private Integer articleId;
    private List<CommentFormatVO> subLists;
}
