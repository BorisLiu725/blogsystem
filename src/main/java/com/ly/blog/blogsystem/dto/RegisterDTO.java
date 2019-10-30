package com.ly.blog.blogsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * Created by BorisLiu on 2019/9/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @NotEmpty
    @Length(max = 255)
    private String userName;
    @NotEmpty
    @Length(max = 255)
    private String password;
    @NotEmpty
    @Length(max = 255)
    private String email;

    private int code;
}
