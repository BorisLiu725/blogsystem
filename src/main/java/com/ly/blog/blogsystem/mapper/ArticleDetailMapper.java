package com.ly.blog.blogsystem.mapper;

import com.ly.blog.blogsystem.bean.ArticleDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Mapper
public interface ArticleDetailMapper {


    void insert(ArticleDetail articleDetail);

    ArticleDetail findByArticleId(Integer articleId);


    void update(@Param("articleId") Integer articleId, @Param("content") String content);

    void delete(@Param("articleId") Integer articleId);
}
