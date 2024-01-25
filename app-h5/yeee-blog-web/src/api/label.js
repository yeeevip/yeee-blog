import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function getLabelList(params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/label/page',
    method: 'post',
    params
  })
}

export function getArticleByLabelId(params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    params
  })
}
