package com.ly.blog.blogsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


/**
 * Created by BorisLiu on 2019/10/7
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
//    @NotEmpty
//    @Length(max = 255)
    private String content;
//    @NotEmpty
    private Integer articleId;
//    @NotEmpty
    private Long fatherComment;

    private String ipAddress;





}
