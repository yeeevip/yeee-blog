import request from '@/utils/request'
import commonUtil from '@/utils/commonUtil'

export function getBlogByUid (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + 'api/blog/article/detail',
    method: 'post',
    params
  })
}

export function getSameBlogByTagUid (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/content/getSameBlogByTagUid',
    method: 'get',
    params
  })
}

export function getSameBlogByBlogId (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + 'api/blog/article/page',
    method: 'post',
    params
  })
}

