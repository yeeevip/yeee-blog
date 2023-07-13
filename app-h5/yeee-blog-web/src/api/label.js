import request from '@/utils/request'

export function getLabelList(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/label/page',
    method: 'post',
    params
  })
}

export function getArticleByLabelId(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/article/page',
    method: 'post',
    params
  })
}
