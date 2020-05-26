package com.ly.blog.blogsystem.service;

import com.github.pagehelper.PageInfo;
import com.ly.blog.blogsystem.bean.Comment;
import com.ly.blog.blogsystem.dto.ArticleDTO;
import com.ly.blog.blogsystem.dto.CommentDTO;
import com.ly.blog.blogsystem.vo.CommentFormatLsitVO;
import com.ly.blog.blogsystem.vo.CommentFormatVO;
import com.ly.blog.blogsystem.vo.CommentVO;

import java.util.List;

/**
 * Created by BorisLiu on 2019/10/7
 */
public interface CommentService  {

    void publish(CommentDTO commentDTO,String userId);

    PageInfo<CommentVO> findAll(Integer curPage,Integer pageSize);

    public List<CommentFormatVO> commentFormats(Long commentRootId);

    List<CommentFormatLsitVO> findFormatCommentsAboutArticleId(Integer articleId);


    PageInfo<Comment> findAllByState(Integer state, Integer curPage, Integer pageSize);

    boolean updateCommentStateById(Integer state, Integer id);
}
