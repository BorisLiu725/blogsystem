package com.ly.blog.blogsystem.bean.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by BorisLiu on 2019/10/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Poll implements Serializable {
    private Long id;
    private Date time;
    private String userId;

}
