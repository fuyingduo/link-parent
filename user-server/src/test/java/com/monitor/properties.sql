/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : properties

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 08/07/2019 18:35:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `application` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务名称',
  `profile` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '环境(dev, prop, test)',
  `lable` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分支',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of properties
-- ----------------------------
BEGIN;
INSERT INTO `properties` VALUES (1, 'spring.datasource.url', 'jdbc:mysql://localhost:3306/link_user?useUnicode=true&characterEncoding=utf-8&useSSL=false', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (2, 'spring.datasource.username', 'root', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (3, 'spring.datasource.password', '12345678', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (4, 'spring.datasource.driver-class-name', 'com.mysql.jdbc.Driver', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (5, 'spring.jpa.show-sql', 'true', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (6, 'spring.jpa.properties.hibernate.hbm2ddl.auto', 'create-drop', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (7, 'spring.jpa.database-platform', 'org.hibernate.dialect.MySQL5InnoDBDialect', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (8, 'spring.jpa.database', 'mysql', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (9, 'spring.redis.database', '0', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (10, 'spring.redis.host', '127.0.0.1', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (11, 'spring.redis.port', '6379', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (12, 'spring.redis.password', '', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (13, 'spring.redis.timeout', '5000', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (14, 'server.port', '7001', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (15, 'spring.sleuth.sampler.probability', '1', 'user-server', 'dev', 'master');
INSERT INTO `properties` VALUES (16, 'spring.zipkin.base-url', '127.0.0.1:9220', 'user-server', 'dev', 'master');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
