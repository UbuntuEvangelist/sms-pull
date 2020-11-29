/*
Navicat MySQL Data Transfer

Source Server         : root_con
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-11-06 10:30:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sms_admin
-- ----------------------------
DROP TABLE IF EXISTS `sms_admin`;
CREATE TABLE `sms_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_pwd` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_admin
-- ----------------------------
INSERT INTO `sms_admin` VALUES ('1', 'admin', '000000', '0', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3d3cudGFuZ2ppYWJpbi5jbiIsInVzZXIiOjEsImp0aSI6IjdBODgxQTgyLTlDMzYtNDMzNC05MjBGLTQxMUZBNjJEQjVFRCJ9._f2BdRX7dI6qd7HEb9fYOjrCtK7rJWaAAn2D1ymhD2Q');

-- ----------------------------
-- Table structure for sms_invite
-- ----------------------------
DROP TABLE IF EXISTS `sms_invite`;
CREATE TABLE `sms_invite` (
  `invite_id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`invite_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_invite
-- ----------------------------

-- ----------------------------
-- Table structure for sms_message
-- ----------------------------
DROP TABLE IF EXISTS `sms_message`;
CREATE TABLE `sms_message` (
  `sms_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `message_id` varchar(255) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `region` int(11) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  PRIMARY KEY (`sms_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_message
-- ----------------------------
INSERT INTO `sms_message` VALUES ('1', 'dsf', 'SMd7de51148042465e98b176a191cea272', '102058110', '2', '2020-09-18 21:23:10', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('2', 'sdf', 'SM62eeae9472774f77968ffeb5444ad812', '102058110', '2', '2020-09-18 23:06:09', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('3', 'wa.me/6584301657\n* NO DEPOSIT\n* Weekly Settlement\n* 4D\n* Soccer Bet\n* Live C@sino\n* Slot Jackpot\n* Lo@n\ncall or whatsapp me/6584301657', 'SM87b9c0e9db5f4990ade03c5970b4bd34', '102058110', '2', '2020-09-18 23:59:53', '管理员', '1', '6582848605', '0');
INSERT INTO `sms_message` VALUES ('4', 'test', 'SMb2f0506f54b04c8399d826446324a22c', '102058110', '2', '2020-09-19 00:00:36', '管理员', '1', '6582848605', '0');
INSERT INTO `sms_message` VALUES ('5', 'asd', 'SMa7b97b639d9d4dee918c6769ab2447bc', '102058110', '2', '2020-09-19 03:36:07', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('6', 'sdf', 'SMf98da59a012741fdaf9f7829aaf2d828', '102058110', '2', '2020-09-19 07:15:07', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('7', 'testing', 'SM94b157e5af964488b72a998018478c32', '102058110', '2', '2020-09-19 22:28:05', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('8', 'teting1234', '', '60102058110', '0', '2020-09-20 18:37:50', '管理员', '2', 'hi', '0');
INSERT INTO `sms_message` VALUES ('9', 'teting1234', 'SM770bbd47aaa443f899beb79572b17b82', '102058110', '2', '2020-09-20 18:38:12', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('10', 'testing SMS 19:00', 'SM54383a8a2c6e41e2805ebf321a1482a6', '102058110', '2', '2020-09-20 19:02:39', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('11', 'testing SMS 19:00', 'SMdf08b79b5ce7496c95d163ef5a493e97', '1156202943', '2', '2020-09-20 19:02:40', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('12', 'testing SMS 19:00', 'SM6b5f37b9676e4b85bccbecd0cb06b176', '1156235245', '2', '2020-09-20 19:02:40', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('13', 'testing SMS 19:00', 'SM2740840f1cf241ca88e0aa873f4eff24', '1133942330', '2', '2020-09-20 19:02:42', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('14', 'testing SMS 19:00', 'SMcc54a2ac3d8c4327a2d303df548ba152', '1151842016', '2', '2020-09-20 19:02:42', '管理员', '1', 'hi', '0');
INSERT INTO `sms_message` VALUES ('15', 'http://linktr.ee/Ocean_Online_Game', '', '60124355582', '2', '2020-09-21 00:37:29', '管理员', '2', '123', '0');
INSERT INTO `sms_message` VALUES ('16', 'http://linktr.ee/Ocean_Online_Game', 'SMfea089c7723b4bbdba4df4ecd108a642', '124355582', '2', '2020-09-21 00:38:11', '管理员', '1', '123', '0');
INSERT INTO `sms_message` VALUES ('17', 'http://linktr.ee/Ocean_Online_Game', 'SMa18d56b48bb84fd1afa3a355bd9c0360', '124801280', '2', '2020-09-21 00:38:32', '管理员', '1', '123', '0');
INSERT INTO `sms_message` VALUES ('18', 'http://linktr.ee/Ocean_Online_Game', 'SM58b535a8eb72421eb97c906cfff6af48', '169201280', '2', '2020-09-21 00:39:02', '管理员', '1', '123', '0');
INSERT INTO `sms_message` VALUES ('19', 'http://linktr.ee/Ocean_Online_Game', 'SM69e15a3a6cf34442815ceb4550befde3', '133608932', '2', '2020-09-21 00:40:09', '管理员', '1', '123', '0');
INSERT INTO `sms_message` VALUES ('20', 'http://linktr.ee/Ocean_Online_Game', 'SM5ae17bf42b2c4591b87aad8ba78f3fbe', '164289831', '2', '2020-09-21 00:40:21', '管理员', '1', '123', '0');
INSERT INTO `sms_message` VALUES ('21', 'http://linktr.ee/Ocean_Online_Game', 'SMcbb9ecaf66c64db49805d4d0514cab84', '164289831', '2', '2020-09-21 00:40:24', '管理员', '1', '123', '0');
INSERT INTO `sms_message` VALUES ('22', 'http://linktr.ee/Ocean_Online_Game', 'SM65ffb80f7ffb4c11a78c8bab726a6b5b', '123210242', '2', '2020-09-21 00:42:26', '管理员', '1', '123', '0');
INSERT INTO `sms_message` VALUES ('23', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SMda028c562daa449fa8d752e3fe910c77', '162210148', '2', '2020-09-21 01:07:09', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('24', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SM59f4a6e6cb9c41f082965db1692e9627', '175017985', '2', '2020-09-21 01:07:10', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('25', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SM28a39de671004b0898c60a754d5eec85', '173172230', '2', '2020-09-21 01:07:11', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('26', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SMa1430705fbad4564bf48f8edc6182f9a', '102367121', '2', '2020-09-21 01:07:11', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('27', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SMa53a559fc12a4209917f177092b0649d', '177599139', '2', '2020-09-21 01:07:12', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('28', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SM3d7c528b8765459183a19e080d0a8bf1', '172214981', '2', '2020-09-21 01:07:12', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('29', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SM5b53283cb3234b038824ded8941c3228', '163700034', '2', '2020-09-21 01:07:12', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('30', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SMb5864fb705f646c89fd537e1c022d481', '1115355509', '2', '2020-09-21 01:07:14', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('31', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SMd31f9e87471b49ecbbc50cbfb0bce6cc', '165119456', '2', '2020-09-21 01:07:14', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('32', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SM5fb6d86288cd4a0b8dde166052b0e8f8', '174782193', '2', '2020-09-21 01:07:15', '管理员', '1', 'Ocean Online Gaming', '0');
INSERT INTO `sms_message` VALUES ('33', 'FREE CREDIT CAMPAIGN STARTING SOON\nhttp://wa.me/601169958891', 'SM08554fde61774495b06d914fcf3e191a', '169201280', '2', '2020-09-21 01:07:16', '管理员', '1', 'Ocean Online Gaming', '0');

-- ----------------------------
-- Table structure for sms_rate
-- ----------------------------
DROP TABLE IF EXISTS `sms_rate`;
CREATE TABLE `sms_rate` (
  `id` int(11) NOT NULL,
  `rate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_rate
-- ----------------------------

-- ----------------------------
-- Table structure for sms_twilio_phone
-- ----------------------------
DROP TABLE IF EXISTS `sms_twilio_phone`;
CREATE TABLE `sms_twilio_phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) DEFAULT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_twilio_phone
-- ----------------------------
INSERT INTO `sms_twilio_phone` VALUES ('1', '+15812220642', 'AC851ae75c0f9633b8ec613bc8b00cce19', '1', '0eac5c4594759c2834d51631bde65a33');
INSERT INTO `sms_twilio_phone` VALUES ('2', '+17069792411', 'AC851ae75c0f9633b8ec613bc8b00cce19', '1', '0eac5c4594759c2834d51631bde65a33');

-- ----------------------------
-- Table structure for sms_user
-- ----------------------------
DROP TABLE IF EXISTS `sms_user`;
CREATE TABLE `sms_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` decimal(19,2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `notice` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `region` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pwd` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sms_user
-- ----------------------------
INSERT INTO `sms_user` VALUES ('1', '1.00', '2020-09-20 19:20:40', '1', '13751817494', '1', '1', '0.00', 'dennis', '000000', null);
