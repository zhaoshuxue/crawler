
```
CREATE TABLE `t_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
  `href` varchar(512) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'url地址',
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '网页内容，用于分析',
  `success` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '分析状态，1,2,3,4,5，，，，',
  `imgs` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '图片个数',
  `create_time` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3796 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='抓取网页信息表';
```


success 
0 未解析图片
2 已解析图片
3 已处理部分图片
4 处理完全部图片