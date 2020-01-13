CREATE TABLE `Spitter` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(64) NOT NULL COMMENT '用户姓名',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `firstName` varchar(64) NOT NULL COMMENT '名字',
  `lastName` varchar(64) NOT NULL COMMENT '姓氏',
  `email` varchar(64)  COMMENT '邮箱',
  `headPicPath` varchar(64)  COMMENT '头像路径',
  PRIMARY KEY (`id`)
);
alter table `Spitter` add constraint username unique(`username`);
CREATE TABLE `Spittle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(300),
  `time` datetime DEFAULT CURRENT_TIMESTAMP ,
  `latitude` double precision,
  `longitude` double precision,
  PRIMARY KEY (`id`)
);

CREATE TABLE `t_book`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reader` varchar(255),
  `isbn` varchar(255),
  `title` varchar(255),
  `author` varchar(255),
  `description` varchar(255),
  PRIMARY KEY (`id`)
);
