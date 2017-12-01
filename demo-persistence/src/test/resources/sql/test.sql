

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `testuser`;
CREATE TABLE `testuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `scope` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- ----------------------------
-- Records of testuser
-- ----------------------------
INSERT INTO `testuser` VALUES ('1', 'JackQ', '18', 'math', '95');
INSERT INTO `testuser` VALUES ('2', 'LeeO', '20', 'math', '99');
INSERT INTO `testuser` VALUES ('3', 'Cheer', '19', 'english', '96');
INSERT INTO `testuser` VALUES ('4', 'Tom', '19', 'chinese', '92');