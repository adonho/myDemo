
CREATE TABLE `tb_member_login_log` (
  `id`  int NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
  `member_id`  int NOT NULL COMMENT '关联tb_member' ,
  `login_target`  varchar(100) NOT NULL COMMENT '登录昵称\手机号' ,
  `login_ip`  varchar(32) NOT NULL COMMENT '登录IP' ,
  `create_date`  datetime NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `idx_member_login_log_1` (`member_id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8
;

