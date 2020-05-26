alter table `user` add column avatar varchar(255);

alter table `user` add column `sex` char (10) comment '性别';

alter table `user` add column `birthday` datatime comment '生日';

alter table `user` add column `brief_info` varchar (255) comment '简介';

alter table `user` add column `state` tinyint comment '1-正常 2-拉黑';