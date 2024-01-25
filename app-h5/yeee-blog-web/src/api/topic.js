import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function getTopicList(params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/topic/page',
    method: 'post',
    params
  })
}

export function getTopicItemList(params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    data: params
  })
}



