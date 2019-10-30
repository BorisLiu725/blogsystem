package com.ly.blog.blogsystem.common;

import java.lang.annotation.*;

/**
 * Created by BorisLiu on 2019/9/25
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
