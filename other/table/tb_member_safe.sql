
CREATE TABLE `tb_member_safe` (
  `id`  int NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
  `member_id`  int NOT NULL COMMENT '关联tb_member' ,
  `bind_target`  varchar(100) NOT NULL COMMENT '绑定邮箱\手机号码' ,
  `bind_type`  tinyint NOT NULL COMMENT '手机\邮箱类型' ,
  `create_date`  datetime NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `idx_member_safe_1` (`member_id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8
;

