package com.ly.blog.blogsystem.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.blog.blogsystem.bean.Article;
import com.ly.blog.blogsystem.bean.ArticleDetail;
import com.ly.blog.blogsystem.dto.ArticleDTO;
import com.ly.blog.blogsystem.dto.PageResult;
import com.ly.blog.blogsystem.exception.ArticleException;
import com.ly.blog.blogsystem.mapper.ArticleDetailMapper;
import com.ly.blog.blogsystem.mapper.ArticleMapper;
import com.ly.blog.blogsystem.mapper.ClassficationMapper;
import com.ly.blog.blogsystem.mapper.CommentMapper;
import com.ly.blog.blogsystem.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private Logger logger  = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    @Autowired
    private ClassficationMapper classficationMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 发表文章
     * */
    @Override
    public void createArticle(String userId, ArticleDTO articleDTO) {
        //检查文章类型是否合法
        if (Objects.isNull(classficationMapper.findById(articleDTO.getClassId()))){
            throw new ArticleException("文章类型不存在！");
        }
        Article article = new Article();
        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtils.copyProperties(articleDTO,article);
        article.setUserId(userId);
        article.setCreateTime(new Date());
        article.setUpdateTime(article.getCreateTime());
        logger.info("用户{}==>开始发表文章",userId);
        articleMapper.insert(article);
        articleDetail.setContent(articleDTO.getContent());
        articleDetail.setArticleId(article.getId());
        logger.info("用户{}==>开始插入文章详情==>",userId);
        articleDetailMapper.insert(articleDetail);
        logger.info("用户{}==>保存文章成功！",userId);
    }

    @Override
    public void update(Integer articleId, String userId, ArticleDTO articleDTO) {
        Article record = articleMapper.findByIdAndUserId(articleId, userId);
        if (record==null){
            throw new ArticleException("文章不存在！更新失败...");
        }
        Article articleRecord = new Article();
        articleRecord.setUserId(userId);
        articleRecord.setId(articleId);
        BeanUtils.copyProperties(articleDTO,articleRecord);
        articleRecord.setUpdateTime(new Date());
        logger.info("用户{}==>开始更新文章:id:{}",userId,articleId);
        articleMapper.update(articleRecord);
        logger.info("用户{}==>开始更新文章详情:articleId:{}",userId,articleId);
        articleDetailMapper.update(articleId,articleDTO.getContent());
        logger.info("用户{}==>更新文章完成！",userId);
    }

    @Override
    public void deleteArticle(Integer articleId, String userId) {
        articleMapper.delete(articleId,userId);
        articleDetailMapper.delete(articleId);
    }

    @Override
    public PageInfo<ArticleDTO> findAllByUserId(String userId,Integer curPage, Integer pageSize) {
        PageHelper.startPage(curPage,pageSize);
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        List<Article> articles = articleMapper.findAllByUserId(userId);
        for (int i = 0; i < articles.size(); i++) {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(articles.get(i),articleDTO);
            ArticleDetail articleDetail = articleDetailMapper.findByArticleId(articles.get(i).getId());
            articleDTO.setArticleDetail(articleDetail);
            articleDTOList.add(articleDTO);
        }
        articles = null;
        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(articleDTOList);
        return pageInfo;
    }

    @Override
    public PageInfo<ArticleDTO> findAll(Integer curPage, Integer pageSize) {
        PageHelper.startPage(curPage,pageSize);

        List<ArticleDTO> articleDTOList = new ArrayList<>();
        List<Article> articles = articleMapper.findAll();
        for (int i = 0; i < articles.size(); i++) {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(articles.get(i),articleDTO);
            ArticleDetail articleDetail = articleDetailMapper.findByArticleId(articles.get(i).getId());
            articleDTO.setArticleDetail(articleDetail);
            articleDTOList.add(articleDTO);
        }
        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(articleDTOList);
        articles = null;
        return pageInfo;
    }
}
