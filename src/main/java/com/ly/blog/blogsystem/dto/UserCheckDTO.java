package com.ly.blog.blogsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by BorisLiu on 2019/11/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCheckDTO {
    private int type;
    private String target;
}
