
CREATE TABLE `tb_member_safe_qa` (
  `id`  int NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
  `member_safe_id`  int NOT NULL COMMENT '关联tb_member_safe' ,
  `question`  varchar(100) NOT NULL COMMENT '问题' ,
  `answer`  varchar(100) NOT NULL COMMENT '答案' ,
  `state`  tinyint NOT NULL COMMENT '状态：0，无效；1，有效' ,
  `create_date`  datetime NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `idx_member_safe_qa_1` (`member_safe_id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8
;

