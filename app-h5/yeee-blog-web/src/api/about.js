import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function getMe (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/about/getMe',
    method: 'get',
    params
  })
}

export function getContact (params) {
    return request({
      url: commonUtil.FUNCTIONS.base_url() + '/about/getContact',
      method: 'get',
      params
    })
  }
