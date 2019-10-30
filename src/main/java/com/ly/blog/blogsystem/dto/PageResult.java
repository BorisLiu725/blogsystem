package com.ly.blog.blogsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private int page;
    private long total;
    private List<T> result;
}
