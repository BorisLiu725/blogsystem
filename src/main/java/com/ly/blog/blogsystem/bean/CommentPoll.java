package com.ly.blog.blogsystem.bean;

import com.ly.blog.blogsystem.bean.base.Poll;
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
public class CommentPoll extends Poll {
    private Integer commentId;
}

