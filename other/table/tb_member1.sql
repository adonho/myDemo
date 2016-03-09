
CREATE TABLE `tb_member` (
`id`  int NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
`nickname`  varchar(100) NOT NULL COMMENT '昵称' ,
`password`  varchar(32) NOT NULL COMMENT '密码' ,
`mobile`  varchar(20) NOT NULL COMMENT '手机号' ,
`create_date`  datetime NOT NULL ,
`recommend_id`  int NULL ,
  `state` tinyint(4) NOT NULL default '1' COMMENT '状态',
PRIMARY KEY (`id`),
UNIQUE INDEX `idx_member_1` (`nickname`),
UNIQUE INDEX `idx_member_2` (`mobile`),
INDEX `idx_member_3` (`recommend_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8
;

