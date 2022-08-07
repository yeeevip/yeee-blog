import request from '@/utils/request'

export function getBlogSortList(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/classify/page',
    method: 'post',
    params
  })
}

export function getArticleByBlogSortUid(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/article/page',
    method: 'post',
    params
  })
}
