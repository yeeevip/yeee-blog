import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function getSortList (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/sort/getSortList',
    method: 'get',
    params
  })
}

export function getArticleByMonth (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/sort/getArticleByMonth',
    method: 'get',
    params
  })
}
