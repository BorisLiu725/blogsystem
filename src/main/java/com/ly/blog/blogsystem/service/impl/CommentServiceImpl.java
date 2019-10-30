package com.ly.blog.blogsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.blog.blogsystem.bean.Article;
import com.ly.blog.blogsystem.bean.Comment;
import com.ly.blog.blogsystem.bean.User;
import com.ly.blog.blogsystem.dto.CommentDTO;
import com.ly.blog.blogsystem.enumeration.CommentState;
import com.ly.blog.blogsystem.exception.ServiceException;
import com.ly.blog.blogsystem.mapper.ArticleMapper;
import com.ly.blog.blogsystem.mapper.CommentMapper;
import com.ly.blog.blogsystem.mapper.UserMapper;
import com.ly.blog.blogsystem.service.CommentService;
import com.ly.blog.blogsystem.service.UserService;
import com.ly.blog.blogsystem.vo.CommentFormatLsitVO;
import com.ly.blog.blogsystem.vo.CommentFormatVO;
import com.ly.blog.blogsystem.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BorisLiu on 2019/10/7
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void publish(CommentDTO commentDTO, String userId) {
        // 检查文章id是否存在
        String title = articleMapper.findTitleById(commentDTO.getArticleId());
        if (title == null){
            throw new ServiceException("您所评论的文章已被删除！");
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setState(CommentState.NO_PASS);
        comment.setTime(new Date());
        comment.setUserId(userId);
        commentMapper.insert(comment);
    }

    @Override
    public PageInfo<CommentVO> findAll(Integer curPage,Integer pageSize) {
        PageHelper.startPage(curPage,pageSize);
        List<Comment> comments =  commentMapper.selectAllRoot();
        List<CommentVO> commentVOList = new ArrayList<>();
        for (int i = 0; i < comments.size(); i++) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comments.get(i),commentVO);
            //查询用户名
            User user = userMapper.findById(comments.get(i).getUserId());
            //查询文章标题
            String title = articleMapper.findTitleById(comments.get(i).getArticleId());
            commentVO.setCommentUser(user);
            commentVO.setTitle(title);
            //查找子评论
            List<CommentVO> subComments = subComments(comments.get(i).getId());
            commentVO.setSubComments(subComments);

            commentVOList.add(commentVO);
        }
        comments = null;
        return new PageInfo<>(commentVOList);
    }



    /**
     * 根据跟评论id找出它的所有子评论
     * */
    public List<CommentVO> subComments(Long id){
        if (id == null){
            return null;
        }
        //查找它的子评论
        List<Comment> subLists = commentMapper.findByFatherId(id);
        List<CommentVO> commentVOList = new ArrayList<>();

        if (subLists == null){
            return null;
        }


        for (int i = 0; i < subLists.size(); i++) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(subLists.get(i),commentVO);
            //查询用户名
            User user = userMapper.findById(subLists.get(i).getUserId());
            //查询文章标题
            String title = articleMapper.findTitleById(subLists.get(i).getArticleId());
            commentVO.setCommentUser(user);
            commentVO.setTitle(title);
            commentVOList.add(commentVO);
        }

        //查找子评论的评论
        for (int i = 0; i < commentVOList.size(); i++) {
            commentVOList.get(i).setSubComments(subComments(commentVOList.get(i).getId()));
        }
        return commentVOList;
    }


    /**
     * 根据根评论id找出它的所有子评论(树形)
     * */
    @Override
    public List<CommentFormatVO> commentFormats(Long commentRootId){

        if (commentRootId == null)
            return null;
        //找到跟评论的用户名
        Comment rootComment = commentMapper.findById(commentRootId);
        User rootUser = userMapper.findById(rootComment.getUserId());
        //找到根评论的子评论
        List<Comment> subList = commentMapper.findByFatherId(commentRootId);
        List<CommentFormatVO> commentFormatLists = new ArrayList<CommentFormatVO>();
        if (subList == null){
            return null;
        }

        for (int i = 0; i < subList.size(); i++) {
            CommentFormatVO commentFormatVO = new CommentFormatVO();
            //查询用户名
            String fromUserName = userMapper.findNameById(subList.get(i).getUserId());
            commentFormatVO.setCommentId(subList.get(i).getId());
            commentFormatVO.setFromUserName(fromUserName);
            commentFormatVO.setToUserName(rootUser.getUserName());
            commentFormatVO.setArticleId(subList.get(i).getArticleId());
            commentFormatVO.setContent(subList.get(i).getContent());
            commentFormatLists.add(commentFormatVO);
        }
        for (int i = 0; i < commentFormatLists.size(); i++) {
            commentFormatLists.get(i).setSubLists(commentFormats(commentFormatLists.get(i).getCommentId()));
        }
        return commentFormatLists;
    }

    /**
     * 根据根评论id找出它的所有子评论（链表型）
     *
     * */
    @Override
    public List<CommentFormatLsitVO> findFormatCommentsAboutArticleId(Integer articleId) {

        //根据文章id查找跟评论
        List<Comment> rootComments = commentMapper.findByArticleId(articleId);
        Article article = articleMapper.findById(articleId);
        User rootUser = userMapper.findById(article.getUserId());
        List<CommentFormatVO> commentFormatLists = new ArrayList<CommentFormatVO>();
        if (rootComments == null){
            return null;
        }

        for (int i = 0; i < rootComments.size(); i++) {
            CommentFormatVO commentFormatVO = new CommentFormatVO();
            //查询用户名
            String fromUserName = userMapper.findNameById(rootComments.get(i).getUserId());
            commentFormatVO.setCommentId(rootComments.get(i).getId());
            commentFormatVO.setFromUserName(fromUserName);
            //根据
            commentFormatVO.setToUserName(rootUser.getUserName());
            commentFormatVO.setArticleId(rootComments.get(i).getArticleId());
            commentFormatVO.setContent(rootComments.get(i).getContent());
            commentFormatVO.setSubLists(commentFormats(rootComments.get(i).getId()));
            commentFormatLists.add(commentFormatVO);
        }
        List<CommentFormatLsitVO> finalResult = new ArrayList<>();
        //树形转化成列表形
        treetoList(commentFormatLists,finalResult);
        return finalResult;
    }

    /**
     * resource 一个树形结构
     * result   一个链表结构
     * */
    private  void treetoList(List<CommentFormatVO> resource, List<CommentFormatLsitVO> result){
        if (resource == null){
            return ;
        }
        if (resource.size()<=0){
            return ;
        }
        for (int i = 0; i < resource.size(); i++) {
            CommentFormatVO commentFormatVO = resource.get(i);
            CommentFormatLsitVO commentFormatLsitVO = new CommentFormatLsitVO();
            BeanUtils.copyProperties(commentFormatVO,commentFormatLsitVO);
            result.add(commentFormatLsitVO);
            if (commentFormatVO.getSubLists()!=null&& commentFormatVO.getSubLists().size()>0){
                treetoList(commentFormatVO.getSubLists(),result);
            }
        }
    }
}
