
## 系统说明

- 基于 Spring Boot + Vue 开发的 **轻量级博客系统**
- 轻量级 + 组件化 + 可扩展

## 程序预览

[预览地址](https://www.yeee.vip)

### 前台

![](doc/blog/images/blog-index.png)

### 后台

- 博客管理

![](doc/blog/images/blog-list.png)

- 博客编辑

![](doc/blog/images/blog-edit.png)

- 博客预览

![](doc/blog/images/blog-preview.png)

## 快速开始

### 核心依赖

| 依赖                          | 版本                                                   |
|-----------------------------|------------------------------------------------------|
| Spring Boot                 | 2.6.3                                                |
| yeee-memo                   | [通用web脚手架](https://github.com/yeeevip/yeee-memo.git) |

### 模块说明

```lua
yeee-blog
├── app-h5
    ├── yeee-admin-ui -- 博客后台管理系统｜vue
    └── yeee-blog-web -- 博客客户端｜vue
├── doc -- 文档脚本
├── yeee-blog-bootstrap -- 主启动工程
├── yeee-blog-client -- 博客客户端服务
├── yeee-app-common -- 公共模块
├── yeee-blog-manage -- 博客管理端服务
├── yeee-sys-manage -- 系统权限管理服务
```

### 本地开发 运行

#### 环境要求

- jdk1.8
- mysql >= 5.7
- redis

```
# 下载yeee-memo工程
git clone https://github.com/yeeevip/yeee-memo.git

# 打包[JavaWeb通用脚手架]
cd memo-parent && mvn clean install

# 下载yeee-blog项目
git clone https://github.com/yeeevip/yeee-blog.git

# 运行博客服务
mvn clean install && java -jar -Dspring.profiles.active=test yeee-blog-bootstrap/target/yeee-blog-bootstrap-1.0.0-SNAPSHOT.jar

# 运行博客管理端ui
cd app-h5/yeee-admin-ui && npm install && npm run dev

# 运行博客客户端ui
cd app-h5/yeee-blog-web && npm install && npm run dev
```

### 其他说明

1. 欢迎提交 [PR](https://www.yeee.vip)，注意对应提交对应 `blog-dev` 分支

2. 欢迎提交 [issue](https://github.com/yeeevip/yeee-blog/issues)，请写清楚遇到问题的原因、开发环境、复显步骤。

## 技术交流群

作者QQ：1324459373
