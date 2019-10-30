package com.ly.blog.blogsystem.controller;

import com.github.pagehelper.PageInfo;
import com.ly.blog.blogsystem.common.CurrentUser;
import com.ly.blog.blogsystem.common.CurrentUserData;
import com.ly.blog.blogsystem.common.ResponseCodeConstant;
import com.ly.blog.blogsystem.dto.ArticleDTO;
import com.ly.blog.blogsystem.handler.ResInfo;
import com.ly.blog.blogsystem.exception.ArticleException;
import com.ly.blog.blogsystem.service.ArticleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * Created by BorisLiu on 2019/10/6
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @ApiOperation(value = "查询所有文章列表")
    @GetMapping(value = "/find/list/{curPage}/{pageSize}")
    public PageInfo<ArticleDTO> findArticles(@PathVariable("curPage")Integer curPage,@PathVariable("pageSize")Integer pageSize){
        PageInfo<ArticleDTO> articles;
        articles  =  articleService.findAll(curPage,pageSize);
        return articles;
    }

    @ApiOperation(value = "根据用户id查询文章列表")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    @GetMapping(value = "/find/list/{userId}/{curPage}/{pageSize}")
    public PageInfo<ArticleDTO> findArticlesByUserId(@PathVariable("userId")String userId,@PathVariable("curPage")Integer curPage,@PathVariable("pageSize")Integer pageSize){
        PageInfo<ArticleDTO> articles;
        articles  =  articleService.findAllByUserId(userId,curPage,pageSize);
        return articles;
    }


    @ApiOperation(value = "发表文章")
    @ApiImplicitParam(name = "articleDTO",value = "文章信息",required = true)
    @PostMapping(value = "/publish")
    public void publishArticle(@RequestBody ArticleDTO articleDTO, @CurrentUser CurrentUserData currentUserData){
        articleService.createArticle(currentUserData.getUserId(),articleDTO);
    }

    @ApiOperation(value = "修改文章")
    @ApiImplicitParam(name = "articleDTO",value = "文章信息",required = true)
    @PutMapping(value = "/update/{articleId}")
    public void updateArticle(@RequestBody ArticleDTO articleDTO,@PathVariable(value = "articleId")Integer articleId, @CurrentUser CurrentUserData currentUserData){
        articleService.update(articleId,currentUserData.getUserId(),articleDTO);
    }

    @ApiOperation(value = "删除文章")
    @ApiImplicitParam(name = "articleId",value = "文章id",required = true)
    @DeleteMapping(value = "/delete/{articleId}")
    public void deleteArticle(@PathVariable(value = "articleId")Integer articleId, @CurrentUser CurrentUserData currentUserData){
        articleService.deleteArticle(articleId,currentUserData.getUserId());
    }




}
