import request from '@/utils/request'
import commonUtil from '@/utils/commonUtil'

export function getBlogByLevel (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    params
  })
}

export function getNewBlog (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    params
  })
}

export function getBlogByTime (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/index/getBlogByTime',
    method: 'get',
    params
  })
}

export function getHotBlog (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    params
  })
}

export function getHotTag (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/label/page',
    method: 'post',
    params
  })
}

export function getLink (params) {
  // return request({
  //   url: process.env.WEB_API + '/index/getLink',
  //   method: 'get',
  //   params
  // })
  let linkRes = {
    "data": [
      {
        "title": "百度",
        "summary": "百度官方搜索",
        "url": "https://www.baidu.com/",
        "linkStatus": 1.0,
        "status": 1.0
      }
    ],
    "code": commonUtil.ECode.SUCCESS
  }
  return new Promise((resolve) => {
    resolve(linkRes);
  });
}

export function addLinkCount (params) {
  // return request({
  //   url: process.env.WEB_API + '/index/addLinkCount',
  //   method: 'get',
  //   params
  // })
  return new Promise(resolve => {
    resolve({"code": commonUtil.ECode.SUCCESS})
  })
}

export function getWebConfig (params) {
  // return request({
  //   url: process.env.WEB_API + '/index/getWebConfig',
  //   method: 'get',
  //   params
  // })
  let res = {
    "code": commonUtil.ECode.SUCCESS,
    "data": {
      "name": "一页的博客",
      "title": "一页的博客 - YEEE.VIP",
      "summary": "提供分享Java基础、Spring教程、源码分析、架构师、开源框架、互联网大厂、面试宝典记录、高并发案例、SpringCloud实战、前端教程",
      "keyword": "Java基础,Spring教程,源码分析,架构师,开源框架,互联网大厂,面试宝典记录,高并发案例,SpringCloud实战,前端教程",
      "author": "Yeee、一页",
      "recordNum": "蒙ICP备20001946号",
      "aliPay": "",
      "weixinPay": "",
      "linkApplyTemplate": "",
      "openComment": "1",
      "openMobileComment": "0",
      "openAdmiration": "1",
      "openMobileAdmiration": "0",
      "showList": "[\"2\",\"3\",\"4\",\"5\",\"1\"]",
      "github": "https://github.com/yeeevip/yeee-memo",
      "gitee": "https://gitee.com/yeeevip/yeee-memo",
      "qqNumber": "1324459373",
      "qqGroup": "96305921",
      "weChat": "",
      "email": "wwwyeeevip@gmail.com",
      "aliPayPhoto": "",
      "weixinPayPhoto": "",
    }
  }
  return new Promise((resolve) => {
    resolve(res);
  });
}

export function getWebNavbar (params) {
  // return request({
  //   url: process.env.WEB_API + '/index/getWebNavbar',
  //   method: 'get',
  //   params
  // })
  let webNavbarList = [
    {
      "name": "首页",
      "navbarLevel": 1,
      "summary": "首页",
      "icon": "el-icon-remove",
      "url": "/",
      "sort": 7,
      "isShow": 1,
      "isJumpExternalUrl": 0,
      "childWebNavbar": [],
      "status": 1
    },
    // {
    //   "name": "关于我",
    //   "navbarLevel": 1,
    //   "summary": "关于我",
    //   "icon": "el-icon-user",
    //   "url": "/about",
    //   "sort": 6,
    //   "isShow": 1,
    //   "isJumpExternalUrl": 0,
    //   "childWebNavbar": [],
    //   "status": 1
    // },
    // {
    //   "name": "归档",
    //   "navbarLevel": 1,
    //   "summary": "归档",
    //   "icon": "el-icon-camera",
    //   "url": "/sort",
    //   "sort": 5,
    //   "isShow": 1,
    //   "isJumpExternalUrl": 0,
    //   "childWebNavbar": [],
    //   "status": 1
    // },
    {
      "name": "分类",
      "navbarLevel": 1,
      "summary": "博客分类",
      "icon": "el-icon-folder-checked",
      "url": "/classify",
      "sort": 4,
      "isShow": 1,
      "isJumpExternalUrl": 0,
      "childWebNavbar": [],
      "status": 1
    },
    {
      "name": "标签",
      "navbarLevel": 1,
      "summary": "博客标签",
      "icon": "el-icon-headset",
      "url": "/label",
      "sort": 3,
      "isShow": 1,
      "isJumpExternalUrl": 0,
      "childWebNavbar": [],
      "status": 1
    },
    {
      "name": "专题",
      "navbarLevel": 1,
      "summary": "博客专题",
      "icon": "el-icon-data-analysis",
      "url": "/topic",
      "sort": 2,
      "isShow": 1,
      "isJumpExternalUrl": 0,
      "childWebNavbar": [],
      "status": 1
    }
  ]
  return new Promise((resolve) => {
    resolve({
      "code": commonUtil.ECode.SUCCESS,
      "data": webNavbarList
    });
  });
}

export function recorderVisitPage (params) {
  return new Promise(resolve => {
    resolve({"code": commonUtil.ECode.SUCCESS})
  })
  // return request({
  //   url: process.env.WEB_API + '/index/recorderVisitPage',
  //   method: 'get',
  //   params
  // })
}
