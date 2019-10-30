CREATE TABLE `article`(
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
    title VARCHAR (255) NOT NULL COMMENT '标题',
    user_id CHAR (32) NOT NULL COMMENT '用户id',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME NOT NULL COMMENT '更新时间',
    summary VARCHAR (255) COMMENT '文章概要',
    poll_count INT DEFAULT 0 COMMENT '点赞数',
    comment_count INT DEFAULT 0 COMMENT '评论数',
    read_count INT DEFAULT 0 COMMENT '阅读数',
    class_id BIGINT(20) NOT NULL COMMENT '文章分类id',
    is_essence BOOL DEFAULT FALSE COMMENT '是否精华',
    is_top BOOL DEFAULT FALSE COMMENT '是否顶置' ,
    PRIMARY KEY(id),
    KEY(user_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `article_detail`(
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '文章详情id',
    article_id BIGINT(20) NOT NULL COMMENT '文章id',
    content TEXT COMMENT '文章内容',
    PRIMARY KEY(id),
    KEY(article_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `classfication`(
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '文章分类id',
    `name` VARCHAR (255) NOT NULL COMMENT '分类名',
    PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;