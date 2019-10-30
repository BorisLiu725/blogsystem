create table  `user`(
  user_id char(32) not null ,
  user_name varchar (255)  not null ,
  `password` varchar (255) not null ,
  email varchar (255)  not null ,
  create_time datetime not null ,
  update_time datetime not null ,
  primary key (user_id),
  key (user_name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8