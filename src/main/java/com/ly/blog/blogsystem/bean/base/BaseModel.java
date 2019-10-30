package com.ly.blog.blogsystem.bean.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by BorisLiu on 2019/9/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel {
    private Date createTime;
    private Date updateTime;

}
