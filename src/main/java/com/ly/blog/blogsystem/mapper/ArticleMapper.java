package com.ly.blog.blogsystem.mapper;

import com.ly.blog.blogsystem.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Mapper
public interface ArticleMapper {
    void insert(Article article);

    Article findByIdAndUserId(@Param("articleId") Integer articleId, @Param("userId") String userId);

    void update(Article articleRecord);

    void delete(@Param("articleId") Integer articleId, @Param("userId")String userId);

    List<Article> findAllByUserId(@Param("userId")String userId);

    List<Article> findAll();

    String findTitleById(@Param("articleId")Integer articleId);

    Article findById(@Param("articleId")Integer articleId);

    List<Article> findAllByClassfication(@Param("classficationId")Integer classficationId);

    Integer updateStateById(@Param("articleId")Integer articleId,@Param("state")Integer state);

    List<Article> findAllByUserIdAndState(@Param("userId")String userId,@Param("state")Integer state);

    List<Article> findAllByState(@Param("state")Integer state);
}
