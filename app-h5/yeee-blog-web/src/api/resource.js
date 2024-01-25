import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function getStudyVideoBySort (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/resource/getStudyVideoBySort',
    method: 'get',
    params
  })
}
