package com.ly.blog.blogsystem.mapper;

import com.ly.blog.blogsystem.bean.Comment;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/7
 */

@Mapper
public interface CommentMapper {

    void insert(Comment comment);

    List<Comment> selectAllRoot();

    List<Comment> findByFatherId(@Param("fatherComment") Long fatherComment);

    Comment findById(@Param("id") Long id);

    List<Comment> findByArticleId(@Param("articleId")Integer articleId);

    List<Comment> findAllByState(@Param("state")Integer state);

    Integer updateCommentStateById(@Param("state")Integer state,@Param("id") Integer id);
}
