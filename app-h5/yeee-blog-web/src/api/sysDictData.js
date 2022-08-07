import request from '@/utils/request'
import commonUtil from '@/utils/commonUtil'

export function getListByDictType(params) {
  return request({
    url: process.env.WEB_API + '/sysDictData/getListByDictType',
    method: 'post',
    params
  })
}

export function getListByDictTypeList(params) {
  // return request({
  //   url: process.env.WEB_API + '/sysDictData/getListByDictTypeList',
  //   method: 'post',
  //   data: params
  // })
  return new Promise(resolve => {
    resolve({"code": ""})
  })
}
