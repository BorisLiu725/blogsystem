package com.ly.blog.blogsystem.service.impl;

import com.ly.blog.blogsystem.BlogsystemApplication;
import com.ly.blog.blogsystem.service.CommentService;
import com.ly.blog.blogsystem.vo.CommentVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by BorisLiu on 2019/10/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogsystemApplication.class})
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    public void findAll() {
        List<CommentVO> commentVOList = commentService.subComments(1l);
        for (int i = 0; i < commentVOList.size(); i++) {
            System.out.println(commentVOList);
        }

    }
}