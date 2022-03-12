/*
 Navicat Premium Data Transfer

 Source Server         : 本机-mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : hairorder

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 09/03/2022 18:41:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_barber
-- ----------------------------
DROP TABLE IF EXISTS `t_barber`;
CREATE TABLE `t_barber`  (
  `barber_id` bigint(0) NOT NULL COMMENT 'id',
  `barber_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名字',
  `barber_des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述\r\n',
  `barber_type_id` bigint(0) NULL DEFAULT NULL COMMENT '类型id',
  `barber_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `barber_status` tinyint(0) NULL DEFAULT 1 COMMENT '状态',
  `barber_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
  `order_count` int(0) NULL DEFAULT 0 COMMENT '订单量',
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`barber_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_barber
-- ----------------------------

-- ----------------------------
-- Table structure for t_barber_type
-- ----------------------------
DROP TABLE IF EXISTS `t_barber_type`;
CREATE TABLE `t_barber_type`  (
  `barber_type_id` bigint(0) NOT NULL,
  `barber_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`barber_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_barber_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_hair
-- ----------------------------
DROP TABLE IF EXISTS `t_hair`;
CREATE TABLE `t_hair`  (
  `hair_id` bigint(0) NOT NULL,
  `hair_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hair_des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hair_type_id` bigint(0) NULL DEFAULT NULL,
  `barber_type_id` bigint(0) NULL DEFAULT NULL,
  `hai_price` decimal(10, 2) NULL DEFAULT NULL,
  `hair_status` blob NULL,
  `hair_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `order_count` int(0) NULL DEFAULT 0,
  `creat_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`hair_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_hair
-- ----------------------------

-- ----------------------------
-- Table structure for t_hair_type
-- ----------------------------
DROP TABLE IF EXISTS `t_hair_type`;
CREATE TABLE `t_hair_type`  (
  `hair_type_id` bigint(0) NOT NULL,
  `hair_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`hair_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_hair_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` int(0) NOT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  `hair_id` int(0) NULL DEFAULT NULL,
  `barber_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `order_date` datetime(0) NULL DEFAULT NULL,
  `order_status` int(0) NULL DEFAULT 0,
  `order_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys
-- ----------------------------
DROP TABLE IF EXISTS `t_sys`;
CREATE TABLE `t_sys`  (
  `sys_id` bigint(0) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` date NULL DEFAULT NULL,
  PRIMARY KEY (`sys_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys
-- ----------------------------
INSERT INTO `t_sys` VALUES (1, 'e70e2222a9d67c4f2eae107533359aa4', 'test', 'test', NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `integral` int(0) NULL DEFAULT NULL,
  `create_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
