package com.ly.blog.blogsystem.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;

/**
 * Created by BorisLiu on 2019/9/11
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JWTProperties {
    @NotEmpty
    private String secretKey;
    @Min(0)
    private Duration expireTime;
}
