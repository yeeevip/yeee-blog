import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function getBlogSortList(params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/classify/page',
    method: 'post',
    params
  })
}

export function getArticleByBlogSortUid(params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    params
  })
}
