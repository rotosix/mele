#建表 模拟序列
CREATE TABLE `secret`.`sequence_uid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
COMMENT = 'uid序列';

CREATE TABLE `secret`.`sequence_secret` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = MyISAM
COMMENT = '自增序列';
