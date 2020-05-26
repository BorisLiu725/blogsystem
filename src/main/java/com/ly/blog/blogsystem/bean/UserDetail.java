package com.ly.blog.blogsystem.bean;

import com.ly.blog.blogsystem.bean.base.BaseModel;
import com.ly.blog.blogsystem.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BorisLiu on 2019/10/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail extends BaseModel {
    private Gender gender;

    private String sex;
    private String birthday;
    private String briefInfo;
    private Integer state;
}
