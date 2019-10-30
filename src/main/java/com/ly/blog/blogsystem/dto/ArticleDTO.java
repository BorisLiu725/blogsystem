package com.ly.blog.blogsystem.dto;

import com.ly.blog.blogsystem.bean.ArticleDetail;
import com.ly.blog.blogsystem.bean.Classfication;
import com.ly.blog.blogsystem.vo.CommentFormatLsitVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Integer id;
    private String title;
    private String userId;
    private Date createTime;
    private Date updateTime;
    private String summary;
    private Integer pollCount;
    private Integer commentCount;
    private Integer readCount;
    private Integer classId;
    private boolean isEssence;
    private boolean isTop;
    private String content;
    private ArticleDetail articleDetail;
    private List<CommentFormatLsitVO> comments;

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", summary='" + summary + '\'' +
                ", pollCount=" + pollCount +
                ", commentCount=" + commentCount +
                ", readCount=" + readCount +
                ", classId=" + classId +
                ", isEssence=" + isEssence +
                ", isTop=" + isTop +
                ", content='" + content + '\'' +
                ", articleDetail=" + articleDetail +
                '}';
    }
}
