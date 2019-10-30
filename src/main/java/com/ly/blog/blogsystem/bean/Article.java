package com.ly.blog.blogsystem.bean;

import com.ly.blog.blogsystem.bean.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by BorisLiu on 2019/10/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article extends BaseModel {
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

    @Override
    public String toString() {
        return "Article{" +
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
                '}';
    }
}
