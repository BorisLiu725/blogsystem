package com.ly.blog.blogsystem.config;

import com.ly.blog.blogsystem.properties.JWTProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by BorisLiu on 2019/9/11
 */
@Configuration
@EnableConfigurationProperties(JWTProperties.class)
public class JWTPropertiesConfig {


}
