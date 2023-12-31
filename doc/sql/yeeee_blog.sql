/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云68
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Schema         : yeeee_manage

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 17/06/2023 17:45:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog_article
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_article`;
CREATE TABLE `t_blog_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `classify_id` bigint DEFAULT NULL COMMENT '分类ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `title_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题图',
  `content` text COMMENT '内容',
  `author` varchar(32) NOT NULL COMMENT '作者',
  `ori_flag` tinyint(1) DEFAULT '1' COMMENT '是否原创：1-是，0-否',
  `sort` int DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '发布状态：1-已发布，0-未发布',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客文章';

-- ----------------------------
-- Records of t_blog_article
-- ----------------------------
BEGIN;
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, 1, '11', 'https://oos.yeee.vip/blog/vcs2yArn_1683791311936.jpg', '<p><s>11111111111111</s>😀</p><p><img src=\"https://oos.yeee.vip/blog/vcs2yArn_1683791311936.jpg\" alt=\"11\" data-href=\"https://oos.yeee.vip/blog/vcs2yArn_1683791311936.jpg\" style=\"\"/></p><p><br></p><p><br></p>', '11', 1, 1, 1, NULL, '1111', 0, 'admin', '2023-06-17 15:04:49', 'admin', '2023-06-17 16:49:51');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (2, 1, '知道知道不知道', NULL, NULL, 'yeee', 1, 0, 0, '2022-01-29 08:41:28', NULL, 0, '', '2022-01-29 08:41:28', NULL, '2022-01-29 08:41:28');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (3, 1, 'mac 安装node.js', NULL, '<ul><li><a href=\"https://blog.csdn.net/m0_67402341/article/details/126112558#mac_nodejs_2\" target=\"_self\">mac 安装node.js</a></li><ul><li><a href=\"https://blog.csdn.net/m0_67402341/article/details/126112558#_3\" target=\"_self\">第一步：官网下载</a></li><li><a href=\"https://blog.csdn.net/m0_67402341/article/details/126112558#_9\" target=\"_self\">第二步：安装</a></li><li><a href=\"https://blog.csdn.net/m0_67402341/article/details/126112558#_13\" target=\"_self\">第三步：验证</a></li><li><a href=\"https://blog.csdn.net/m0_67402341/article/details/126112558#_19\" target=\"_self\">第四步：环境配置</a></li></ul></ul><p>mac 安装node.js<br>第一步：官网下载<br>访问nodejs官网，链接: https://nodejs.org/en/，选择红色选框内的点击安装。<br>LTS是稳定版。<br>Current是最新版本，最新的功能特性都在这里面。<br><br><br>第二步：安装<br>点击 在 Finder 中显示，双击刚下载的文件，按步骤默认安装就行。<br><br><br>第三步：验证<br>安装完成后，打开终端，<br>输入 npm -v 和 node -v两个命令,<br>如下图出现版本信息，说明安装成功<br><br><br>第四步：环境配置<br>以上验证结束后，就可以进行环境配置了<br>打开Mac 终端，配置全局环境变量<br>键盘输入【vim .bash_profile】进入编辑状态<br>打开之后添加一行代码：PATH=$PATH:/usr/local/bin/<br>点击【esc】退出编辑状态，此时无法对内容进行修改<br>键盘输入【:wq!】强制保存并退出vim，回到终端的界面<br>重新打开终端，输入node，就会进入node环境<br><br><br>control+c 按两次退出node环境<br><br>到此，mac安装node.js就成功了。</p>', 'yeee', 1, 0, 0, '2021-07-14 04:55:29', NULL, 0, NULL, '2021-07-14 04:55:29', NULL, '2021-07-14 04:55:29');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (4, 1, 'Java编程-高并发情况下接口性能优化实践-提升吞吐量TPS', NULL, '<p>&nbsp;&nbsp;&nbsp;&nbsp;记得前段时间工作中接到一个任务是优化一个下单接口的性能提高接口的吞吐量TPS，前期通过arthas工具跟踪接口的具体方法调用链路及耗时，发现了影响此接口的性能瓶颈主要是加锁的方式，后来变更了锁的方式后性能大大提升。</p><p>&nbsp;&nbsp;&nbsp;&nbsp;程序的大致逻辑是，1.判断余额是否足够&gt;2.保存订单信息&gt;3.扣减钱包余额&gt;4.记录钱包流水；现在我将优化前及优化后的代码分别通过jmeter设置100个线程1s内请求这个接口通过实验观察效果，代码的变更及压测结果大家可以往下看<br></p><h3>优化前：采用悲观锁</h3><blockquote><p>该方案是将整个事务方法加锁，可以保证下单时接口成功的响应，即使余额不足也会友好的返回余额不足的提示，但是不足的是客户端用户等待的时间较长；此种方案如果是用在商品秒杀销量优先场景下也不是很合适。</p></blockquote><h3><em>&gt;代码</em></h3><div><img src=\"https://img-blog.csdnimg.cn/img_convert/28caaecdbbfce481e64c2d20915228a6.png#pic_center\" style=\"max-width:100%;\"><img src=\"https://img-blog.csdnimg.cn/img_convert/d9b2895f933e096e63eded737cbdd7d6.png#pic_center\" style=\"max-width: 100%;\"></div><h3>&gt;压测结果（tps 8.4左右）<br></h3><p><img src=\"https://img-blog.csdnimg.cn/img_convert/6a627ab9001df2d4c403241618a126ac.png#pic_center\" style=\"max-width:100%;\"><br></p><p><img0.08859649122807017\" src=\"https://mmbiz.qpic.cn/mmbiz_png/cSKvw8A2EM9ibfLtkODW8lRT5ibktLiaeBpgT56PGXuaExzVsXILmRDGpzXB09b20v5bsBjicrm9CLuBEyDqQ0MF2A/640?wx_fmt=png\"></img0.08859649122807017\"></p><p></p><h3>优化后：采用乐观锁</h3><blockquote><p>这种方式不会显示的给整个方法上锁，依赖的是通过sql的条件判断达到数据同步防止超卖情况发生，此种方式实际就是乐观锁的一种实现，经测试程序的吞吐量提升了很多，但是不足的是并发高的情况下扣减余额时仅仅通过数据库返回的更新结果不能准确判断是余额不足发生更新异常。</p></blockquote><p><img0.14719626168224298\" src=\"https://mmbiz.qpic.cn/mmbiz_png/cSKvw8A2EM9ibfLtkODW8lRT5ibktLiaeBpcpLhke9PrJJoOdW6uUMbwTBw1zVogtzfnbvIdmc3p0zfAKVuoicf28A/640?wx_fmt=png\"></img0.14719626168224298\"></p><p><img0.5814111261872456\" src=\"https://mmbiz.qpic.cn/mmbiz_png/cSKvw8A2EM9ibfLtkODW8lRT5ibktLiaeBpSrkL1AR2JGuhnKIckPsG8bXnxPWnVPW0pycSG50b1csJQVxEHzIaYQ/640?wx_fmt=png\"></img0.5814111261872456\"></p><p><img0.24285714285714285\" src=\"https://mmbiz.qpic.cn/mmbiz_png/cSKvw8A2EM9ibfLtkODW8lRT5ibktLiaeBpUicicb4v19iagFuy8cMDfNuibJw6HJF7UCF38cJZsuibJoZCpVn6tjh3uSw/640?wx_fmt=png\"></img0.24285714285714285\"></p><h3><img src=\"https://img-blog.csdnimg.cn/img_convert/1cc7d811f9617605e930fb86a01b0e3d.png#pic_center\" style=\"max-width:100%;\"><img src=\"https://img-blog.csdnimg.cn/img_convert/37f8455edfa5573db3de7ca72e0409a9.png#pic_center\" style=\"font-size: 14px; max-width: 100%;\"></h3><h3>&gt;压测结果（tps 30左右）<br></h3><p><img src=\"https://img-blog.csdnimg.cn/img_convert/77b27d4f80bec8da3f447e83d63e20ce.png#pic_center\" style=\"max-width:100%;\"><br></p><p><img0.08604038630377524\" src=\"https://mmbiz.qpic.cn/mmbiz_png/cSKvw8A2EM9ibfLtkODW8lRT5ibktLiaeBpT5JjCZmP9XqH37WNBNWJsrSXRtF1hicm1PYNvq2GzDticWrSJ6QXY4Xg/640?wx_fmt=png\"></img0.08604038630377524\"></p><h3>总结</h3><p>通常影响接口的吞吐量有可能就是锁的粒度的大小，在保证数据同步性的同时我们要根据具体场景具体分析评估后采用合适的锁。</p><p><br></p>', 'yeee', 1, 0, 0, '2021-08-28 12:53:00', NULL, 0, NULL, '2021-08-28 12:53:00', NULL, '2021-08-28 12:53:00');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (5, 1, '基于java配置nginx获取真实IP代码实例', NULL, '<p>1、java代码<br></p><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th style=\"text-align: left;\"><span style=\"font-weight: normal;\">/** 获取客户端IP */</span><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;</code><code>public</code> <code>static</code> <code>final</code> <code>String getClientIp(HttpServletRequest request) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>String ip = request.getHeader(</code><code>\"X-Forwarded-For\"</code><code>);</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>if</code> <code>(StringUtils.isBlank(ip) || </code><code>\"unknown\"</code><code>.equalsIgnoreCase(ip)|| </code><code>\"127.0.0.1\"</code><code>.equalsIgnoreCase(ip)) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>ip = request.getHeader(</code><code>\"Proxy-Client-IP\"</code><code>);</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>if</code> <code>(StringUtils.isBlank(ip) || </code><code>\"unknown\"</code><code>.equalsIgnoreCase(ip)|| </code><code>\"127.0.0.1\"</code><code>.equalsIgnoreCase(ip)) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>ip = request.getHeader(</code><code>\"WL-Proxy-Client-IP\"</code><code>);</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>if</code> <code>(StringUtils.isBlank(ip) || </code><code>\"unknown\"</code><code>.equalsIgnoreCase(ip)|| </code><code>\"127.0.0.1\"</code><code>.equalsIgnoreCase(ip)) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>ip = request.getHeader(</code><code>\"X-Real-IP\"</code><code>);</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>if</code> <code>(StringUtils.isBlank(ip) || </code><code>\"unknown\"</code><code>.equalsIgnoreCase(ip)|| </code><code>\"127.0.0.1\"</code><code>.equalsIgnoreCase(ip)) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>ip = request.getRemoteAddr();</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>if</code> <code>(StringUtils.isBlank(ip) ||</code><code>\"127.0.0.1\"</code><code>.equals(ip)|| ip.indexOf(</code><code>\":\"</code><code>) &gt; -</code><code>1</code><code>) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>try</code> <code>{</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>ip = InetAddress.getLocalHost().getHostAddress();</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>} </code><code>catch</code> <code>(UnknownHostException e) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>ip = </code><code>null</code><code>;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照\',\'分割</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>if</code> <code>(ip != </code><code>null</code> <code>&amp;&amp; ip.length() &gt; </code><code>15</code><code>) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>if</code> <code>(ip.indexOf(</code><code>\",\"</code><code>) &gt; </code><code>0</code><code>) {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>ip = ip.substring(</code><code>0</code><code>, ip.indexOf(</code><code>\",\"</code><code>));</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>return</code> <code>ip;</code></span></div><span style=\"font-weight: normal;\"><code style=\"font-size: 14px;\">&nbsp;&nbsp;</code><code style=\"font-size: 14px;\">}</code><br></span></th></tr></tbody></table><p><br>2、nginx需要进行相应修改,重点 proxy_set_header<br></p><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th style=\"text-align: left;\"><span style=\"font-weight: normal;\"></span>&nbsp;<span style=\"font-weight: normal;\">server {</span><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>listen&nbsp; xxxx;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>server_name 127.0.0.1;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code># 静态页面目录</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>root&nbsp;&nbsp; xxxxxxxxxx;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code># 默认首页</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>index&nbsp;&nbsp; login.html;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header Host $http_host;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header X-Real-IP $remote_addr;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header REMOTE-HOST $remote_addr;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;</code></span></div><div><span style=\"font-weight: normal;\">&nbsp;</span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>add_header Access-Control-Allow-Origin *;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>add_header Access-Control-Allow-Methods </code><code>\'GET,POST\'</code><code>;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>add_header Access-Control-Allow-Headers </code><code>\'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization\'</code><code>;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>#proxy_cookie_path /* /*;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>client_max_body_size&nbsp; 100m;</code></span></div><div><span style=\"font-weight: normal;\">&nbsp;</span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>location / {</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header Host $http_host;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header X-Real-IP $remote_addr;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header REMOTE-HOST $remote_addr;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header Upgrade $http_upgrade;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>proxy_set_header Connection </code><code>\"upgrade\"</code><code>;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>add_header Access-Control-Allow-Origin *;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>add_header Access-Control-Allow-Headers </code><code>\'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization\'</code><code>;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>add_header Access-Control-Allow-Methods GET,POST,OPTIONS;</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code>......</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;&nbsp;&nbsp;</code><code>}</code></span></div><div><span style=\"font-weight: normal;\"><code>&nbsp;&nbsp;</code><code>}</code></span></div></th></tr></tbody></table>', 'yeee', 1, 0, 0, '2021-07-17 15:17:51', NULL, 0, NULL, '2021-07-17 15:17:51', NULL, '2021-07-17 15:17:51');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (6, 1, '先计划再做。 --jack.ma', NULL, NULL, 'yeee', 1, 0, 0, '2021-08-31 06:39:45', NULL, 0, NULL, '2021-08-31 06:39:45', NULL, '2021-08-31 06:39:45');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (7, 1, 'CrowdFunding（众筹系统）', NULL, '<pre><span id=\"LC1\">    此系统是作者本人在上学期间使用java + servlet + jsp 基础语言开发，没有使用任何现在主流框架，如ssm，maven，很适合新手学习。</span>\n<span id=\"LC2\">此外数据源使用了c3p0连接池，同时系统支持mysql-8，并且兼容mysql5.7</span></pre><pre><span style=\"background-color: rgb(249, 150, 59);\"><span style=\"font-weight: bold;\"><a href=\"https://gitee.com/quhailong/CrowdFunding.git\" target=\"_blank\" style=\"\">码云：</a>https://gitee.com/quhailong/CrowdFunding.git</span><br></span></pre><p><span style=\"background-color: rgb(249, 150, 59);\"><a href=\"https://github.com/quhailong1995/CrowdFunding.git\" target=\"_blank\" style=\"\">GitHub：</a>https://github.com/quhailong1995/CrowdFunding.git</span></p><pre><h2>一、使用</h2><p>1.复制git地址拉取项目然后进行构建</p><p>2.所需依赖jar包位置：/WebContent/WEB-INF/lib</p><p>3.web服务器：Tomcat，</p><p>4.创建数据库名称，运行sql文件，sql文件位置：/sql</p><p>此外项目有问题欢迎提出 ，安装运行过程中遇到问题可以联系我的 QQ ： 1324459373，qq 群：69405215</p><h2><a id=\"二项目概述\" href=\"https://gitee.com/quhailong/CrowdFunding#%E4%BA%8C%E9%A1%B9%E7%9B%AE%E6%A6%82%E8%BF%B0\"></a>二、项目概述</h2><p>基础流程介绍（发布项目--审核--用户支持购买项目服务）</p><p>后台项目审核页面地址 ${根页面url}/admin ; 管理员账号：admin，密码：123456</p><h3><a id=\"一注册登陆后发起项目\" href=\"https://gitee.com/quhailong/CrowdFunding#%E4%B8%80%E6%B3%A8%E5%86%8C%E7%99%BB%E9%99%86%E5%90%8E%E5%8F%91%E8%B5%B7%E9%A1%B9%E7%9B%AE\"></a>一、注册登陆后发起项目</h3><h4><a id=\"1基础信息\" href=\"https://gitee.com/quhailong/CrowdFunding#1%E5%9F%BA%E7%A1%80%E4%BF%A1%E6%81%AF\"></a>1.基础信息</h4><p><img src=\"https://oos.yeee.vip/1.%E5%8F%91%E8%B5%B7%E9%A1%B9%E7%9B%AE-%E5%9F%BA%E7%A1%80%E4%BF%A1%E6%81%AF.png\" alt=\"image\"></p><h4><a id=\"2项目信息\" href=\"https://gitee.com/quhailong/CrowdFunding#2%E9%A1%B9%E7%9B%AE%E4%BF%A1%E6%81%AF\"></a>2.项目信息</h4><p><img src=\"https://oos.yeee.vip/2.%E5%8F%91%E8%B5%B7%E9%A1%B9%E7%9B%AE-%E9%A1%B9%E7%9B%AE%E4%BF%A1%E6%81%AF.png\" alt=\"image\"></p><h4><a id=\"3项目详情\" href=\"https://gitee.com/quhailong/CrowdFunding#3%E9%A1%B9%E7%9B%AE%E8%AF%A6%E6%83%85\"></a>3.项目详情</h4><p><img src=\"https://oos.yeee.vip/3.%E5%8F%91%E8%B5%B7%E9%A1%B9%E7%9B%AE-%E9%A1%B9%E7%9B%AE%E8%AF%A6%E6%83%85.png\" alt=\"image\"></p><h4><a id=\"4回报设置\" href=\"https://gitee.com/quhailong/CrowdFunding#4%E5%9B%9E%E6%8A%A5%E8%AE%BE%E7%BD%AE\"></a>4.回报设置</h4><p><img src=\"https://oos.yeee.vip/4.%E5%8F%91%E8%B5%B7%E9%A1%B9%E7%9B%AE-%E5%9B%9E%E6%8A%A5%E8%AE%BE%E7%BD%AE.png\" alt=\"image\"></p><h4><a id=\"5编辑预览\" href=\"https://gitee.com/quhailong/CrowdFunding#5%E7%BC%96%E8%BE%91%E9%A2%84%E8%A7%88\"></a>5.编辑预览</h4><p><img src=\"https://oos.yeee.vip/5.%E5%8F%91%E8%B5%B7%E9%A1%B9%E7%9B%AE-%E7%BC%96%E8%BE%91%E9%A2%84%E8%A7%88.png\" alt=\"image\"></p><h3><a id=\"二前台页面\" href=\"https://gitee.com/quhailong/CrowdFunding#%E4%BA%8C%E5%89%8D%E5%8F%B0%E9%A1%B5%E9%9D%A2\"></a>二、前台页面</h3><h4><a id=\"1项目详情\" href=\"https://gitee.com/quhailong/CrowdFunding#1%E9%A1%B9%E7%9B%AE%E8%AF%A6%E6%83%85\"></a>1.项目详情</h4><p><img src=\"https://oos.yeee.vip/13.%E6%B5%8B%E8%AF%95%E9%A1%B9%E7%9B%AE%20-%E8%AF%A6%E6%83%85%E9%A1%B5.png\" alt=\"image\"></p><h3><a id=\"三个人中心\" href=\"https://gitee.com/quhailong/CrowdFunding#%E4%B8%89%E4%B8%AA%E4%BA%BA%E4%B8%AD%E5%BF%83\"></a>三、个人中心</h3><h4><a id=\"1我的发起\" href=\"https://gitee.com/quhailong/CrowdFunding#1%E6%88%91%E7%9A%84%E5%8F%91%E8%B5%B7\"></a>1.我的发起</h4><p><img src=\"https://oos.yeee.vip/6.%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF-%E6%88%91%E7%9A%84%E5%8F%91%E8%B5%B7.png\" alt=\"image\"></p><h4><a id=\"2我的订单\" href=\"https://gitee.com/quhailong/CrowdFunding#2%E6%88%91%E7%9A%84%E8%AE%A2%E5%8D%95\"></a>2.我的订单</h4><p><img src=\"https://oos.yeee.vip/14.%E4%B8%AA%E4%BA%BA%E4%BF%A1%E6%81%AF-%E6%88%91%E7%9A%84%E8%AE%A2%E5%8D%95.png\" alt=\"image\"></p><h4><a id=\"3更新项目动态\" href=\"https://gitee.com/quhailong/CrowdFunding#3%E6%9B%B4%E6%96%B0%E9%A1%B9%E7%9B%AE%E5%8A%A8%E6%80%81\"></a>3.更新项目动态</h4><p><img src=\"https://oos.yeee.vip/11.%E5%8F%91%E8%B5%B7%E8%80%85%E6%9B%B4%E6%96%B0%E9%A1%B9%E7%9B%AE%E5%8A%A8%E6%80%81.png\" alt=\"image\"></p><h4><a id=\"3更新后页面\" href=\"https://gitee.com/quhailong/CrowdFunding#3%E6%9B%B4%E6%96%B0%E5%90%8E%E9%A1%B5%E9%9D%A2\"></a>3.更新后页面</h4><p><img src=\"https://oos.yeee.vip/12.%E6%9B%B4%E6%96%B0%E8%BF%9B%E5%B1%95%E5%90%8E.png\" alt=\"image\"></p><h3><a id=\"四平台后台管理\" href=\"https://gitee.com/quhailong/CrowdFunding#%E5%9B%9B%E5%B9%B3%E5%8F%B0%E5%90%8E%E5%8F%B0%E7%AE%A1%E7%90%86\"></a>四、平台后台管理</h3><h4><a id=\"1管理员登录\" href=\"https://gitee.com/quhailong/CrowdFunding#1%E7%AE%A1%E7%90%86%E5%91%98%E7%99%BB%E5%BD%95\"></a>1.管理员登录</h4><p><img src=\"https://oos.yeee.vip/7.%E7%AE%A1%E7%90%86%E5%91%98%E7%99%BB%E9%99%86%E9%A1%B5%E9%9D%A2.png\" alt=\"image\"></p><h4><a id=\"2待审核项目\" href=\"https://gitee.com/quhailong/CrowdFunding#2%E5%BE%85%E5%AE%A1%E6%A0%B8%E9%A1%B9%E7%9B%AE\"></a>2.待审核项目</h4><p><img src=\"https://oos.yeee.vip/8.%E5%90%8E%E5%8F%B0%E7%AE%A1%E7%90%86%E4%B8%AD%E5%BF%83-%E5%BE%85%E5%AE%A1%E6%A0%B8%E9%A1%B9%E7%9B%AE.png\" alt=\"image\"></p><h4><a id=\"3项目审核页面\" href=\"https://gitee.com/quhailong/CrowdFunding#3%E9%A1%B9%E7%9B%AE%E5%AE%A1%E6%A0%B8%E9%A1%B5%E9%9D%A2\"></a>3.项目审核页面</h4><p><img src=\"https://oos.yeee.vip/9.%E9%A1%B9%E7%9B%AE%E5%AE%A1%E6%A0%B8%E9%A1%B5%E9%9D%A2.png\" alt=\"image\"></p><h4><a id=\"4通过审核\" href=\"https://gitee.com/quhailong/CrowdFunding#4%E9%80%9A%E8%BF%87%E5%AE%A1%E6%A0%B8\"></a>4.通过审核</h4><p><img src=\"https://oos.yeee.vip/10.%E9%80%9A%E8%BF%87%E5%AE%A1%E6%A0%B8.png\" alt=\"image\"></p>\n\n</pre>', 'yeee', 1, 0, 0, '2021-07-18 07:33:54', '    此系统是作者本人在上学期间使用java + servlet + jsp 基础语言开发，没有使用任何现在主流框架，如ssm，maven，很适合新手学习。 此外数据源使用了c3p0连接池，同时系统支持mysql-8，并且兼容mysql5.7', 0, NULL, '2021-07-18 07:33:54', NULL, '2021-07-18 07:33:54');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (8, 1, 'Jackma：在暂时无法改变的僵化的环境中，必须保持学习才能突围', NULL, '<p>111</p>', 'yeee', 1, 0, 0, '2021-11-29 02:52:18', NULL, 0, NULL, '2021-11-29 02:52:18', NULL, '2021-11-29 02:52:18');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (9, 1, '保持上进、保持低调，不吐槽评论别人，做好自己。—Jack.Ma', NULL, NULL, 'yeee', 1, 0, 0, '2021-08-04 00:23:48', NULL, 0, NULL, '2021-08-04 00:23:48', NULL, '2021-08-04 00:23:48');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (10, 1, '鸿星尔克的营销厉害，合情合理吧', NULL, NULL, 'yeee', 1, 0, 0, '2021-07-24 15:19:34', NULL, 0, NULL, '2021-07-24 15:19:34', NULL, '2021-07-24 15:19:34');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (11, 1, '不仅仅是增删改查啊', NULL, NULL, 'yeee', 1, 0, 0, '2021-07-20 04:45:36', NULL, 0, NULL, '2021-07-20 04:45:36', NULL, '2021-07-20 04:45:36');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (12, 1, 'spring-cloud-gateway', NULL, '<p><a href=\"https://mp.weixin.qq.com/s?__biz=MjM5NjU5OTA1Mg==&amp;mid=2449736126&amp;idx=1&amp;sn=db44688d3607cd58f565adce9e660765&amp;chksm=b11665dd8661eccbcbff32b58e45c4223f20aefd9753dfd1aa4b8073cf997c81c11405aeb610&amp;token=1106047369&amp;lang=zh_CN#rd\" target=\"_blank\">spring-cloud-gateway</a><br></p>', 'yeee', 1, 0, 0, '2021-11-19 01:41:28', NULL, 0, NULL, '2021-11-19 01:41:28', NULL, '2021-11-19 01:41:28');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (13, 1, '佚名：暂时在无法改变的现状里，使人没动力得过且过可能是这样的因素促成的', NULL, '<p>辛苦的从0-1的劳动成果交给彩笔去弄，最后共享成果，彩笔的定义大致是从ta身上得不到有用的东西，而ta只会照猫画虎别人，而且脾气大<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\"></p>', 'yeee', 1, 0, 0, '2021-11-29 02:23:14', NULL, 0, NULL, '2021-11-29 02:23:14', NULL, '2021-11-29 02:23:14');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (14, 1, 'Spring-Web-MVC', NULL, '<p><a href=\"https://mp.weixin.qq.com/s?__biz=MjM5NjU5OTA1Mg==&amp;mid=2449736119&amp;idx=1&amp;sn=0eb4cc34f63a58ccb78ff0274a7f460c&amp;chksm=b11665d48661ecc2e7eb8d876a6cb4c634afe84fb4af2235564a3eee174312a0fb038c025454&amp;token=1106047369&amp;lang=zh_CN#rd\" target=\"_blank\">Spring-Web-MVC</a><br></p>', 'yeee', 1, 0, 0, '2021-11-19 01:38:23', NULL, 0, NULL, '2021-11-19 01:38:23', NULL, '2021-11-19 01:38:23');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (15, 1, 'JAVA脚手架', NULL, '<p><a href=\"https://gitee.com/quhailong/java-integrate\" target=\"_blank\">JAVA脚手架</a><br>11</p><p><br></p>', 'yeee', 1, 0, 0, '2022-11-25 09:08:07', 'https://gitee.com/quhailong/java-integrate', 0, NULL, '2022-11-25 09:08:07', NULL, '2022-11-25 09:08:07');
INSERT INTO `t_blog_article` (`id`, `classify_id`, `title`, `title_img`, `content`, `author`, `ori_flag`, `sort`, `status`, `publish_time`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (16, 1, 'IDEA2022.3永久激活破解 Mac/Win最新激活 2023亲测可用！', NULL, '<blockquote><p>申明：本教程 IntelliJ IDEA 破解补丁、激活码均收集于网络，请勿商用，仅供个人学习使用，如有侵权，请联系作者删除。若条件允许，希望大家购买正版 ！</p></blockquote><blockquote><span style=\"font-weight: normal;\"></span>如果你的idea的版本是2022.3.x，本文为大家带来的激活破解方式。<br>此激活工具，向下兼容，即 2022.3 之前的版本，也都可以使用此方式激活。</blockquote><h2 id=\"1-下载工具包\">1 下载工具包</h2><p>工具包已放在百度云盘，请关注公众号&nbsp;<strong>一页一</strong>&nbsp;，在后台回复 【<strong>2022.3</strong>】 获取激活工具包。<img src=\"https://oos.yeee.vip/wxwp/qrcode_for_gh_472a7acc3579_258.jpg\" style=\"max-width: 100%;\"></p><blockquote><p>重要说明：</p><p>1、工具下载后，放到本地合适的位置，工具的存放路径不要含有汉字和空格，否则会造成激活失败</p><p>2、工具下载后，是zip压缩格式，一定要先解压，不然在后续激活时，会报错，提示找不到jar文件，“ja-netfilter.jar not found”。如果提示无法解压，请先安装zip解压工具。</p></blockquote><p>激活工具包解压后，目录文件如下：</p><p><img src=\"https://oos.yeee.vip/blog/%E6%88%AA%E5%B1%8F2023-06-10%2014.35.22.png\" style=\"max-width:100%;\"><br></p><h2 id=\"2-软件激活\">2 软件激活<button title=\"显示目录导航\" aria-expanded=\"false\"></button></h2><h3 id=\"21-一键配置\">2.1 一键配置</h3><p>执行 scripts 目录下的脚本文件，根据自己的计算机操作系统选择，Mac/Linux 执行&nbsp;<codecourier new\',=\"\" monospace&quot;;=\"\" margin:=\"\" 0px=\"\" 3px;=\"\" padding:=\"\" 0.065em=\"\" 0.4em;=\"\" border:=\"\" 0px;=\"\" font-size:=\"\" 0.87em;=\"\" vertical-align:=\"\" middle;=\"\" box-sizing:=\"\" border-box;=\"\" line-height:=\"\" 1.8;=\"\" display:=\"\" inline;=\"\" overflow-x:=\"\" auto;=\"\" border-radius:=\"\" 2px;=\"\" background-color:=\"\" var(--color-basic-200);=\"\" color:=\"\" rgb(216,=\"\" 59,=\"\" 100);=\"\" word-break:=\"\" break-word;\"=\"\"><span style=\"color: rgb(249, 150, 59);\">install.sh</span>&nbsp;，Windows 直接双击&nbsp;<codecourier new\',=\"\" monospace&quot;;=\"\" margin:=\"\" 0px=\"\" 3px;=\"\" padding:=\"\" 0.065em=\"\" 0.4em;=\"\" border:=\"\" 0px;=\"\" font-size:=\"\" 0.87em;=\"\" vertical-align:=\"\" middle;=\"\" box-sizing:=\"\" border-box;=\"\" line-height:=\"\" 1.8;=\"\" display:=\"\" inline;=\"\" overflow-x:=\"\" auto;=\"\" border-radius:=\"\" 2px;=\"\" background-color:=\"\" var(--color-basic-200);=\"\" color:=\"\" rgb(216,=\"\" 59,=\"\" 100);=\"\" word-break:=\"\" break-word;\"=\"\">i<span style=\"color: rgb(249, 150, 59);\">nstall-all-users.vbs</span>&nbsp;或者&nbsp;<codecourier new\',=\"\" monospace&quot;;=\"\" margin:=\"\" 0px=\"\" 3px;=\"\" padding:=\"\" 0.065em=\"\" 0.4em;=\"\" border:=\"\" 0px;=\"\" font-size:=\"\" 0.87em;=\"\" vertical-align:=\"\" middle;=\"\" box-sizing:=\"\" border-box;=\"\" line-height:=\"\" 1.8;=\"\" display:=\"\" inline;=\"\" overflow-x:=\"\" auto;=\"\" border-radius:=\"\" 2px;=\"\" background-color:=\"\" var(--color-basic-200);=\"\" color:=\"\" rgb(216,=\"\" 59,=\"\" 100);=\"\" word-break:=\"\" break-word;\"=\"\"><span style=\"color: rgb(249, 150, 59);\">install-current-user.vbs</span>&nbsp;脚本。这个脚本会向添加一些环境变量。Windows 系统&nbsp;<codecourier new\',=\"\" monospace&quot;;=\"\" margin:=\"\" 0px=\"\" 3px;=\"\" padding:=\"\" 0.065em=\"\" 0.4em;=\"\" border:=\"\" 0px;=\"\" font-size:=\"\" 0.87em;=\"\" vertical-align:=\"\" middle;=\"\" box-sizing:=\"\" border-box;=\"\" line-height:=\"\" 1.8;=\"\" display:=\"\" inline;=\"\" overflow-x:=\"\" auto;=\"\" border-radius:=\"\" 2px;=\"\" background-color:=\"\" var(--color-basic-200);=\"\" color:=\"\" rgb(216,=\"\" 59,=\"\" 100);=\"\" word-break:=\"\" break-word;\"=\"\">install-all-users.vbs&nbsp;和&nbsp;<codecourier new\',=\"\" monospace&quot;;=\"\" margin:=\"\" 0px=\"\" 3px;=\"\" padding:=\"\" 0.065em=\"\" 0.4em;=\"\" border:=\"\" 0px;=\"\" font-size:=\"\" 0.87em;=\"\" vertical-align:=\"\" middle;=\"\" box-sizing:=\"\" border-box;=\"\" line-height:=\"\" 1.8;=\"\" display:=\"\" inline;=\"\" overflow-x:=\"\" auto;=\"\" border-radius:=\"\" 2px;=\"\" background-color:=\"\" var(--color-basic-200);=\"\" color:=\"\" rgb(216,=\"\" 59,=\"\" 100);=\"\" word-break:=\"\" break-word;\"=\"\">install-current-user.vbs&nbsp;的区别在于环境变量是为系统还是当前用户添加环境变量。</codecourier></codecourier></codecourier></codecourier></codecourier></p><p>我这里是 Macos 执行如下</p><p><img src=\"https://oos.yeee.vip/blog/%E6%88%AA%E5%B1%8F2023-06-10%2014.41.31.png\" style=\"max-width:100%;\"><br></p><p>看到执行结果&nbsp;<strong>Done</strong>&nbsp;，表示执行成功。</p><blockquote><p>说明：</p><ol><li><p>因为脚本会修改环境变量，所以在 Windows 系统可能会被安全软件拦截，大家允许执行即可。</p></li><li><p>激活完成后，不要随便移动工具，更不要删除工具，否则会造成激活失效，甚至无法启动IDE。如果确实需要更换工具路径，路径更换完后，可以先执行对应的卸载脚本 uninstall，然后在重新按上面的步骤，执行安装脚本。</p></li><li><p>在之前的版本中，激活方式都是先打开试用，再在 vmoptions 配置一个 agent，从最近的版本开始，IDEA要求登录账号才可以试用，不打开时也无法修改 vmoptions 了，所以这个版本的激活工具才会提供一个一键安装脚本，本质上这个过程还是在配置 vmoptions</p></li></ol></blockquote><h3 id=\"22-使用激活码\">2.2 使用激活码</h3><p>之前的版本中，有使用服务器激活的方式。由于服务器激活，非常不稳定，此文不再推荐服务器激活的方式，但也仍旧给出，请自行选择：</p><p><span style=\"color: rgb(249, 150, 59); text-decoration-line: underline;\">最新激活码：2023.06.10亲测有效！！！</span></p><pre><code>6G5NXCPJZB-eyJsaWNlbnNlSWQiOiI2RzVOWENQSlpCIiwibGljZW5zZWVOYW1lIjoic2lnbnVwIHNjb290ZXIiLCJhc3NpZ25lZU5hbWUiOiIiLCJhc3NpZ25lZUVtYWlsIjoiIiwibGljZW5zZVJlc3RyaWN0aW9uIjoiIiwiY2hlY2tDb25jdXJyZW50VXNlIjpmYWxzZSwicHJvZHVjdHMiOlt7ImNvZGUiOiJQU0kiLCJmYWxsYmFja0RhdGUiOiIyMDI1LTA4LTAxIiwicGFpZFVwVG8iOiIyMDI1LTA4LTAxIiwiZXh0ZW5kZWQiOnRydWV9LHsiY29kZSI6IlBEQiIsImZhbGxiYWNrRGF0ZSI6IjIwMjUtMDgtMDEiLCJwYWlkVXBUbyI6IjIwMjUtMDgtMDEiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiSUkiLCJmYWxsYmFja0RhdGUiOiIyMDI1LTA4LTAxIiwicGFpZFVwVG8iOiIyMDI1LTA4LTAxIiwiZXh0ZW5kZWQiOmZhbHNlfSx7ImNvZGUiOiJQUEMiLCJmYWxsYmFja0RhdGUiOiIyMDI1LTA4LTAxIiwicGFpZFVwVG8iOiIyMDI1LTA4LTAxIiwiZXh0ZW5kZWQiOnRydWV9LHsiY29kZSI6IlBHTyIsImZhbGxiYWNrRGF0ZSI6IjIwMjUtMDgtMDEiLCJwYWlkVXBUbyI6IjIwMjUtMDgtMDEiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUFNXIiwiZmFsbGJhY2tEYXRlIjoiMjAyNS0wOC0wMSIsInBhaWRVcFRvIjoiMjAyNS0wOC0wMSIsImV4dGVuZGVkIjp0cnVlfSx7ImNvZGUiOiJQV1MiLCJmYWxsYmFja0RhdGUiOiIyMDI1LTA4LTAxIiwicGFpZFVwVG8iOiIyMDI1LTA4LTAxIiwiZXh0ZW5kZWQiOnRydWV9LHsiY29kZSI6IlBQUyIsImZhbGxiYWNrRGF0ZSI6IjIwMjUtMDgtMDEiLCJwYWlkVXBUbyI6IjIwMjUtMDgtMDEiLCJleHRlbmRlZCI6dHJ1ZX0seyJjb2RlIjoiUFJCIiwiZmFsbGJhY2tEYXRlIjoiMjAyNS0wOC0wMSIsInBhaWRVcFRvIjoiMjAyNS0wOC0wMSIsImV4dGVuZGVkIjp0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</code></pre><p><br></p><blockquote><p>最新激活码：<a href=\"https://aijihuo.cn/jetbrains-activation-codes.html\" target=\"_blank\" rel=\"noopener\">JETBRAINS激活码</a></p></blockquote><p>启动IntelliJ IDEA2022.3，输入激活码</p><p><img src=\"https://oos.yeee.vip/blog/%E6%88%AA%E5%B1%8F2023-06-10%2015.10.26.png\" style=\"max-width:100%;\"><br></p><p>点击 Activate 按钮之后，激活成功！<br></p><p><img src=\"https://oos.yeee.vip/blog/%E6%88%AA%E5%B1%8F2023-06-10%2015.02.06.png\" style=\"max-width:100%;\"><br></p><p>激活完成，Enjoy it ~<br></p><p><br></p><h2 id=\"3-常见问题\">3 常见问题</h2><p><font color=\"red\"><em>问题 1：这个激活工具，能用来激活之前版本的IDEA吗，比如2022.1 或 2022.2 ?</em></font></p><p>答：可以，但没必要。有什么理由不使用最新版的IDEA呢？</p><p><font color=\"red\"><em>问题 2：激活之后，下载的工具包可以删除吗？</em></font></p><p>答：工具压缩包可以删除，但解压出来之后，放在特定位置的文件目录，不能移动。若真想要移动，请先执行 script 目录中的 uninstall 脚本，移动之后再重新 install 配置。</p><p><font color=\"red\"><em>问题 3：提示激活码无效怎么办？</em></font></p><p>答：1、断开网络试试；</p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、终端输入<span style=\"color: rgb(194, 79, 74);\">echo $IDEA_VM_OPTIONS</span>命令，如果没有内容输出，说明配置未生效，电脑重启试试。</p><p>&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;3、若还是不行，请评论区留言，或添加作者微信交流。</p><p><img src=\"https://oos.yeee.vip/wxwp/qrcode_for_gh_472a7acc3579_258.jpg\"><br></p>\n\n\n\n\n\n<div id=\"cnblogs_post_body\"><h2 id=\"4-最后\">4 最后</h2><p>本教程以 Jetbrains 官网版本 IntelliJ IDEA 2022.3.2 作为测试演示，本教程中涉及的所有内容，仅供学习参考，请勿用于一切商业用途！</p><blockquote><p>本文由公众号【一页一】撰写，欢迎关注，一起学习进步，期待你的关注！<br>谢谢你的阅读，希望此文对您有所助益！</p></blockquote></div><div></div><div id=\"post-signature\"><p>本文作者：一页、yeee</p><p>本文链接：https://www.yeee.vip/detail.html?id=10015</p><p>版权声明：本作品采用知识共享署名-非商业性使用-禁止演绎</p></div><h2 id=\"4-最后\"><svg aria-hidden=\"true\"><use xlink:href=\"#ac-food-chips\"></use></svg></h2><h2 id=\"1-下载工具包\"><svg aria-hidden=\"true\"><use xlink:href=\"#ac-food-eggyolkcake\"></use></svg></h2>', 'yeee', 1, 0, 0, NULL, '2023.06.10亲测可用！', 0, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_blog_article_label_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_article_label_rel`;
CREATE TABLE `t_blog_article_label_rel` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `label_id` bigint NOT NULL COMMENT '标签ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客文章标签关系';

-- ----------------------------
-- Records of t_blog_article_label_rel
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_blog_article_topic_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_article_topic_rel`;
CREATE TABLE `t_blog_article_topic_rel` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_id` bigint NOT NULL COMMENT '文章ID',
  `topic_id` bigint NOT NULL COMMENT '专题ID',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客文章专题关系';

-- ----------------------------
-- Records of t_blog_article_topic_rel
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_blog_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_classify`;
CREATE TABLE `t_blog_classify` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：1-启用，0-禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客分类';

-- ----------------------------
-- Records of t_blog_classify
-- ----------------------------
BEGIN;
INSERT INTO `t_blog_classify` (`id`, `name`, `sort`, `status`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, '1111', 0, 0, '1110', 0, 'admin', '2023-06-17 15:01:35', 'admin', '2023-06-17 15:30:13');
INSERT INTO `t_blog_classify` (`id`, `name`, `sort`, `status`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (2, '22', 2, 1, '1', 1, 'admin', '2023-06-17 17:44:08', 'admin', '2023-06-17 17:44:08');
COMMIT;

-- ----------------------------
-- Table structure for t_blog_label
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_label`;
CREATE TABLE `t_blog_label` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `type` tinyint NOT NULL DEFAULT '1' COMMENT '标签类型：1-普通标签，2-业务标签',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：1-启用，0-禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客标签';

-- ----------------------------
-- Records of t_blog_label
-- ----------------------------
BEGIN;
INSERT INTO `t_blog_label` (`id`, `name`, `sort`, `status`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, '11', 1, 1, '1111', 0, 'admin', '2023-06-17 15:03:17', 'admin', '2023-06-17 15:03:17');
COMMIT;

-- ----------------------------
-- Table structure for t_blog_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_topic`;
CREATE TABLE `t_blog_topic` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `cover_img` varchar(255) NOT NULL COMMENT '封面图',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：1-启用，0-禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '说明',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客专题';

-- ----------------------------
-- Records of t_blog_topic
-- ----------------------------
BEGIN;
INSERT INTO `t_blog_topic` (`id`, `name`, `cover_img`, `sort`, `status`, `remark`, `deleted`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1, '1', 'https://oos.yeee.vip/blog/vcs2yArn_1683791311936.jpg', 1, 1, '111', 0, 'admin', '2023-06-17 15:03:37', 'admin', '2023-06-17 16:47:26');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;



-- 2023.07.12

alter table t_blog_article add column type tinyint(1) default 0 comment '文章类型:0原文，1外链' after `publish_time`;
alter table t_blog_article add column link_url varchar(255) default null comment '外链地址' after `type`;

-- 2023.07.14

alter table t_blog_article add column read_num int(8) default 0 comment '阅读量' after `link_url`;
alter table t_blog_article add column like_num int(8) default 0 comment '点赞量' after `read_num`;

-- 2023.07.17

CREATE TABLE `t_blog_access_log` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `client_ip` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'IP',
    `client_address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
    `subject` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主体',
    `subject_id` bigint NOT NULL COMMENT '主体ID',
    `event` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作事件',
    `access_time` datetime DEFAULT NULL COMMENT '访问时间',
    `remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '详情描述',
    PRIMARY KEY (`id`),
    KEY `idx_access_time` (`access_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='博客访问日志';


INSERT INTO sys_menu
(id, pid, name, url, perm, icon, seq, remark, `type`, create_time, create_by, update_time, update_by)
VALUES(4353221119792166, 4353221119792144, '访问日志', 'blog/accessLog/accessLog', '', '', 5, NULL, 0, '2023-07-17 10:00:34', 'admin', '2023-07-17 10:00:34', 'admin');

INSERT INTO sys_menu
(id, pid, name, url, perm, icon, seq, remark, `type`, create_time, create_by, update_time, update_by)
VALUES(4353221119792167, 4353221119792166, '添加', '', 'blog:accessLog:add', '', 1, NULL, 1, '2023-07-17 10:00:34', 'admin', '2023-07-17 10:00:34', 'admin');

INSERT INTO sys_menu
(id, pid, name, url, perm, icon, seq, remark, `type`, create_time, create_by, update_time, update_by)
VALUES(4353221119792168, 4353221119792166, '修改', '', 'blog:accessLog:upd', '', 1, NULL, 1, '2023-07-17 10:00:34', 'admin', '2023-07-17 10:00:34', 'admin');

INSERT INTO sys_menu
(id, pid, name, url, perm, icon, seq, remark, `type`, create_time, create_by, update_time, update_by)
VALUES(4353221119792169, 4353221119792166, '查看', '', 'blog:accessLog:info', '', 1, NULL, 1, '2023-07-17 10:00:34', 'admin', '2023-07-17 10:00:34', 'admin');

INSERT INTO sys_menu
(id, pid, name, url, perm, icon, seq, remark, `type`, create_time, create_by, update_time, update_by)
VALUES(4353221119792170, 4353221119792166, '删除', '', 'blog:accessLog:del', '', 1, NULL, 1, '2023-07-17 10:00:34', 'admin', '2023-07-17 10:00:34', 'admin');


-- 2023.07.18

alter table t_blog_access_log add column referer varchar(255) default null comment 'Referer' after `client_ip`;
alter table t_blog_access_log add column user_agent varchar(1024) default null comment 'User-Agent' after `referer`;