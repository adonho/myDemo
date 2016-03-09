
CREATE TABLE `tb_modify_record` (
  `id`  int NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
  `target_id`  int NOT NULL COMMENT '修改目标ID' ,
  `class_full_name`  varchar(128) NOT NULL COMMENT '类全名' ,
  `class_name`  varchar(64) NOT NULL COMMENT '类名' ,
  `data_type`  varchar(128) NOT NULL COMMENT '数据类型' ,
  `filed`  varchar(128) NOT NULL COMMENT '修改字段' ,
  `src_value`  varchar(1024) NOT NULL COMMENT '旧值' ,
  `new_value`  varchar(1024) NOT NULL COMMENT '新值' ,
  `create_date`  datetime NOT NULL ,
  PRIMARY KEY (`id`),
  INDEX `idx_modify_record_1` (`target_id`)
)
  ENGINE=InnoDB
  DEFAULT CHARACTER SET=utf8
;

