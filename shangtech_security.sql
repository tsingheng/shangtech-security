/*
Navicat MySQL Data Transfer

Source Server         : shangtech.mysql.rds.aliyuncs.com
Source Server Version : 50501
Source Host           : shangtech.mysql.rds.aliyuncs.com:3306
Source Database       : shangtech_security

Target Server Type    : MYSQL
Target Server Version : 50501
File Encoding         : 65001

Date: 2015-05-18 17:43:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_security_resouce
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_security_resouce`;
CREATE TABLE `t_sys_security_resouce` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_code` varchar(255) DEFAULT NULL,
  `resource_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `resource_path` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idx_sys_security_resouce_resource_path` (`resource_path`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_security_resouce
-- ----------------------------
INSERT INTO `t_sys_security_resouce` VALUES ('1', 'SECURITY_USER', '用户管理', '0', '', null);
INSERT INTO `t_sys_security_resouce` VALUES ('2', 'SECURITY_RESOURCE', '资源管理', '0', '', null);
INSERT INTO `t_sys_security_resouce` VALUES ('3', 'SECURITY_ROLE', '角色管理', '0', '', null);
INSERT INTO `t_sys_security_resouce` VALUES ('4', 'SECURITY_RESOURCE_MAIN', '资源管理主页面', '2', '/security/resource/main', null);
INSERT INTO `t_sys_security_resouce` VALUES ('5', 'SECURITY_RESOURCE_LIST', '资源列表', '2', '/security/resource/list', null);
INSERT INTO `t_sys_security_resouce` VALUES ('6', 'SECURITY_RESOURCE_ADD', '添加资源', '2', '/security/resource/form', null);
INSERT INTO `t_sys_security_resouce` VALUES ('7', 'SECURITY_RESOURCE_EDIT', '编辑资源', '2', '/security/resource/form', null);
INSERT INTO `t_sys_security_resouce` VALUES ('8', 'SECURITY_RESOURCE_REMOVE', '删除资源', '2', '/security/resource/remove', null);
INSERT INTO `t_sys_security_resouce` VALUES ('9', '', '添加资源保存', '2', '/security/resource/save', null);
INSERT INTO `t_sys_security_resouce` VALUES ('10', '', '编辑资源保存', '2', '/security/resource/save', null);
INSERT INTO `t_sys_security_resouce` VALUES ('11', '', '授权资源', '3', '/security/role/add-auth', null);
INSERT INTO `t_sys_security_resouce` VALUES ('12', '', '批量授权更新', '3', '/security/role/auth', null);
INSERT INTO `t_sys_security_resouce` VALUES ('13', '', '授权资源查看', '3', '/security/role/auth-tree', null);
INSERT INTO `t_sys_security_resouce` VALUES ('14', '', '添加角色', '3', '/security/role/form', null);
INSERT INTO `t_sys_security_resouce` VALUES ('15', '', '编辑角色', '3', '/security/role/form', null);
INSERT INTO `t_sys_security_resouce` VALUES ('16', '', '添加角色保存', '3', '/security/role/save', null);
INSERT INTO `t_sys_security_resouce` VALUES ('17', '', '编辑角色保存', '3', '/security/role/save', null);
INSERT INTO `t_sys_security_resouce` VALUES ('18', '', '角色管理主页面', '3', '/security/role/list', null);
INSERT INTO `t_sys_security_resouce` VALUES ('19', '', '移除资源授权', '3', '/security/role/remove-auth', null);
INSERT INTO `t_sys_security_resouce` VALUES ('20', '', '添加用户角色', '1', '/security/user/add-role', null);
INSERT INTO `t_sys_security_resouce` VALUES ('21', '', '添加用户', '1', '/security/user/form', null);
INSERT INTO `t_sys_security_resouce` VALUES ('22', '', '编辑用户', '1', '/security/user/form', null);
INSERT INTO `t_sys_security_resouce` VALUES ('23', '', '用户管理主页面', '1', '/security/user/list', null);
INSERT INTO `t_sys_security_resouce` VALUES ('24', '', '移除用户角色', '1', '/security/user/remove-role', null);
INSERT INTO `t_sys_security_resouce` VALUES ('25', '', '用户角色查看', '1', '/security/user/roles', null);
INSERT INTO `t_sys_security_resouce` VALUES ('26', '', '添加用户保存', '1', '/security/user/save', null);
INSERT INTO `t_sys_security_resouce` VALUES ('27', '', '编辑用户保存', '1', '/security/user/save', null);
INSERT INTO `t_sys_security_resouce` VALUES ('28', 'test', 'test', '2', 'test', null);

-- ----------------------------
-- Table structure for t_sys_security_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_security_role`;
CREATE TABLE `t_sys_security_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_security_role
-- ----------------------------
INSERT INTO `t_sys_security_role` VALUES ('1', 'SUPER_ADMIN', '超级管理员', null);
INSERT INTO `t_sys_security_role` VALUES ('2', 'GUEST', '游客', null);
INSERT INTO `t_sys_security_role` VALUES ('3', 'KAO_QIN', '考勤专员', null);
INSERT INTO `t_sys_security_role` VALUES ('4', 'CAIWU', '财务', null);
INSERT INTO `t_sys_security_role` VALUES ('5', 'TEST_1', '测试角色1', null);
INSERT INTO `t_sys_security_role` VALUES ('6', 'TEST_2', '测试角色2', null);
INSERT INTO `t_sys_security_role` VALUES ('7', 'TEST3', '测试角色3', null);
INSERT INTO `t_sys_security_role` VALUES ('8', 'MEMBER', '员工', null);

-- ----------------------------
-- Table structure for t_sys_security_role_to_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_security_role_to_resource`;
CREATE TABLE `t_sys_security_role_to_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `idx_sys_security_role_to_resource_role_id` (`role_id`),
  KEY `idx_sys_security_role_to_resource_resource_id` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_security_role_to_resource
-- ----------------------------
INSERT INTO `t_sys_security_role_to_resource` VALUES ('1', '0', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('2', '2', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('4', '6', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('6', '7', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('7', '1', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('8', '20', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('9', '21', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('10', '22', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('11', '23', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('12', '24', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('13', '25', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('14', '26', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('15', '27', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('16', '4', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('17', '5', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('18', '8', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('19', '9', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('20', '10', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('21', '3', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('22', '11', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('23', '12', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('24', '13', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('25', '14', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('26', '15', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('27', '16', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('28', '17', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('29', '18', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('30', '19', '1');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('31', '0', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('32', '1', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('34', '21', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('35', '22', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('36', '23', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('37', '25', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('38', '2', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('39', '4', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('40', '5', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('41', '6', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('42', '7', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('43', '3', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('44', '13', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('46', '15', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('47', '18', '2');
INSERT INTO `t_sys_security_role_to_resource` VALUES ('48', '14', '2');

-- ----------------------------
-- Table structure for t_sys_security_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_security_user`;
CREATE TABLE `t_sys_security_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_security_user
-- ----------------------------
INSERT INTO `t_sys_security_user` VALUES ('1', '123456', null, 'super');
INSERT INTO `t_sys_security_user` VALUES ('2', '123456', null, 'fei.zhang');
INSERT INTO `t_sys_security_user` VALUES ('3', '123456', null, 'guest');
INSERT INTO `t_sys_security_user` VALUES ('4', '123456', null, 'yun.zhao');
INSERT INTO `t_sys_security_user` VALUES ('5', '123456', null, 'wu.wang');
INSERT INTO `t_sys_security_user` VALUES ('6', '123456', null, 'san.zhang');

-- ----------------------------
-- Table structure for t_sys_security_user_to_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_security_user_to_role`;
CREATE TABLE `t_sys_security_user_to_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `sys_security_user_to_role_user_id` (`user_id`),
  KEY `sys_security_user_to_role_role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_security_user_to_role
-- ----------------------------
INSERT INTO `t_sys_security_user_to_role` VALUES ('1', '1', '1');
INSERT INTO `t_sys_security_user_to_role` VALUES ('6', '8', '4');
INSERT INTO `t_sys_security_user_to_role` VALUES ('7', '8', '5');
INSERT INTO `t_sys_security_user_to_role` VALUES ('8', '8', '6');
INSERT INTO `t_sys_security_user_to_role` VALUES ('10', '2', '3');
INSERT INTO `t_sys_security_user_to_role` VALUES ('16', '2', '2');
