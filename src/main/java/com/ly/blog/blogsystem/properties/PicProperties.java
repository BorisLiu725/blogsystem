package com.ly.blog.blogsystem.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by BorisLiu on 2019/11/5
 */

@ConfigurationProperties(prefix = "images")
@Data
public class PicProperties {
    private String uploadPath;
    private String url;
}
