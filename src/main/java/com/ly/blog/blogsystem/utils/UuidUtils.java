package com.ly.blog.blogsystem.utils;

import java.util.UUID;

/**
 * Created by BorisLiu on 2019/9/26
 */
public class UuidUtils {

    public static String getUUid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUid());
    }

}
