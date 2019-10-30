package com.ly.blog.blogsystem.controller;

import com.github.pagehelper.PageInfo;
import com.ly.blog.blogsystem.bean.Comment;
import com.ly.blog.blogsystem.common.CurrentUser;
import com.ly.blog.blogsystem.common.CurrentUserData;
import com.ly.blog.blogsystem.dto.ArticleDTO;
import com.ly.blog.blogsystem.dto.CommentDTO;
import com.ly.blog.blogsystem.service.CommentService;
import com.ly.blog.blogsystem.utils.CusAccessObjectUtil;
import com.ly.blog.blogsystem.vo.CommentFormatLsitVO;
import com.ly.blog.blogsystem.vo.CommentFormatVO;
import com.ly.blog.blogsystem.vo.CommentVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by BorisLiu on 2019/10/7
 */

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "发表评论")
    @ApiImplicitParam(name = "commentDTO",value = "评论信息",required = true)
    @PostMapping("/publish")
    public void publishComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request, @CurrentUser CurrentUserData currentUserData){
        commentDTO.setIpAddress(CusAccessObjectUtil.getIpAddress(request));
        commentService.publish(commentDTO,currentUserData.getUserId());
    }

    @ApiOperation(value = "查询所有文章的评论")
    @ApiImplicitParam(name = "curPage",value = "当前页",required = true)
    @GetMapping(value = "/find/list/{curPage}/{pageSize}")
    public PageInfo<CommentVO> findAllComments(@PathVariable("curPage")Integer curPage,@PathVariable("pageSize")Integer pageSize){
        PageInfo<CommentVO> comments;
        comments  =  commentService.findAll(curPage,pageSize);
        return comments;
    }

    @ApiOperation(value = "查询所有文章的评论")
    @GetMapping(value = "/find/formatList/{articleId}")
    public List<CommentFormatLsitVO> findFormatCommentsAboutArticleId(@PathVariable("articleId")Integer articleId){
        List<CommentFormatLsitVO> comments;
        comments  =  commentService.findFormatCommentsAboutArticleId(articleId);
        return comments;
    }

}
