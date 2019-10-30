package com.ly.blog.blogsystem.vo;

import com.ly.blog.blogsystem.bean.Comment;
import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.enumeration.CommentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by BorisLiu on 2019/10/8
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
    private Long id;
    private Date time;
    private String content;
    //private String userId;
    private Integer articleId;
    private Long fatherComment;
    private CommentState state;
    private String ipAddress;
    private String title; //文章标题
    private User commentUser;
    private List<CommentVO> subComments;

    @Override
    public String toString() {
        return "CommentVO{" +
                "id=" + id +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", articleId=" + articleId +
                ", fatherComment=" + fatherComment +
                ", state=" + state +
                ", ipAddress='" + ipAddress + '\'' +
                ", title='" + title + '\'' +
                ", commentUser=" + commentUser +
                ", subComments=" + subComments +
                '}';
    }
}
