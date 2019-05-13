/*
Navicat SQLite Data Transfer

Source Server         : 青年图摘
Source Server Version : 30714
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30714
File Encoding         : 65001

Date: 2019-05-14 01:31:56
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS "main"."t_article";
CREATE TABLE "t_article" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"title"  TEXT(100) NOT NULL,
"href"  TEXT(100) NOT NULL,
"content"  TEXT(9999) NOT NULL,
"success"  TEXT(10) NOT NULL,
"create_time"  TEXT(50) NOT NULL
);

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS "main"."t_image";
CREATE TABLE "t_image" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"pid"  INTEGER NOT NULL,
"content"  TEXT(1000) NOT NULL,
"title"  TEXT(1000) NOT NULL,
"more"  INTEGER NOT NULL,
"src"  TEXT(1000) NOT NULL,
"status"  INTEGER NOT NULL,
"disk"  TEXT(10) NOT NULL,
"path"  TEXT(10) NOT NULL,
"file_size"  TEXT(100) NOT NULL,
"file_type"  TEXT(10) NOT NULL,
"remark"  TEXT(500) NOT NULL,
"create_time"  TEXT(50) NOT NULL
);

-- ----------------------------
-- Table structure for t_page
-- ----------------------------
DROP TABLE IF EXISTS "main"."t_page";
CREATE TABLE "t_page" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"page"  INTEGER NOT NULL
);
