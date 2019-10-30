package com.ly.blog.blogsystem.service;

import com.github.pagehelper.PageInfo;
import com.ly.blog.blogsystem.dto.ArticleDTO;
import com.ly.blog.blogsystem.dto.PageResult;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */
public interface ArticleService {

    void createArticle(String userId, ArticleDTO articleDTO);

    void update(Integer articleId, String userId, ArticleDTO articleDTO);

    void deleteArticle(Integer articleId, String userId);

    PageInfo<ArticleDTO> findAllByUserId(String userId, Integer curPage, Integer pageSize);

    PageInfo<ArticleDTO> findAll(Integer curPage, Integer pageSize);
}
