package com.ly.blog.blogsystem;

import com.ly.blog.blogsystem.enumeration.BaseEnum;
import com.ly.blog.blogsystem.enumeration.CommentState;

/**
 * Created by BorisLiu on 2019/10/8
 */
public class Test {

    public static void main(String[] args) {

        Class<CommentState> clazz = CommentState.class;
        System.out.println(clazz.getEnumConstants());

    }

}
