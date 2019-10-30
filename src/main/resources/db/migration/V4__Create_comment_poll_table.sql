
CREATE TABLE `article_poll`(
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '点赞id',
  `time` DATETIME NOT NULL COMMENT '点赞时间',
  article_id BIGINT(20) NOT NULL COMMENT '文章id',
  user_id CHAR(32) NOT NULL COMMENT '用户id',
  KEY(article_id),
  KEY(user_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `comment`(

  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '评论id',
  `time` DATETIME NOT NULL COMMENT '评论时间',
  content VARCHAR(255) NOT NULL COMMENT '评论内容',
  user_id CHAR(32) NOT NULL COMMENT '用户id',
  article_id BIGINT(20) NOT NULL COMMENT '文章id',
  father_comment  BIGINT(20)  COMMENT '父评论id',
  state INT DEFAULT 0 COMMENT '评论状态',
  ip_address VARCHAR (50) COMMENT '评论者的ip地址',
  KEY(user_id),
  KEY(article_id),
  KEY(state)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `comment_poll`(

  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '评论点赞表id',
  `time` DATETIME NOT NULL COMMENT '点赞时间',
  user_id CHAR(32) NOT NULL COMMENT '用户id',
  comment_id BIGINT(20) NOT NULL COMMENT '评论id',
  KEY(user_id),
  KEY(comment_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
