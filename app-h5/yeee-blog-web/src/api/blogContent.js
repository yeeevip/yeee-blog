import request from '@/utils/request'
import commonUtil from '@/utils/commonUtil'

export function getBlogByUid (params) {
  return request({
    url: process.env.WEB_API + '/api/blog/article/detail',
    method: 'post',
    params
  })
}

export function getSameBlogByTagUid (params) {
  return request({
    url: process.env.WEB_API + '/content/getSameBlogByTagUid',
    method: 'get',
    params
  })
}

export function getSameBlogByBlogId (params) {
  return request({
    url: process.env.WEB_API + '/api/blog/article/page',
    method: 'post',
    params
  })
}

export function praiseBlogByUid (params) {
  return request({
    url: process.env.WEB_API + '/content/praiseBlogByUid',
    method: 'get',
    params
  })
}

export function getBlogPraiseCountByUid (params) {
  return request({
    url: process.env.WEB_API + '/content/getBlogPraiseCountByUid',
    method: 'get',
    params
  })
}
