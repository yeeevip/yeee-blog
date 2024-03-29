import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function getCommentList (params) {
  // return request({
  //   url: process.env.WEB_API + '/web/comment/getList',
  //   method: 'post',
  //   data: params
  // })
  return new Promise(resolve => {
    resolve({"code": 201})
  })
}

export function getCommentListByUser (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/web/comment/getListByUser',
    method: 'post',
    data: params
  })
}

export function getPraiseListByUser (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/web/comment/getPraiseListByUser',
    method: 'post',
    data: params
  })
}

export function addComment (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/web/comment/add',
    method: 'post',
    data: params
  })
}

export function deleteComment (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/web/comment/delete',
    method: 'post',
    data: params
  })
}

export function reportComment (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/web/comment/report',
    method: 'post',
    data: params
  })
}

export function getUserReceiveCommentCount (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/web/comment/getUserReceiveCommentCount',
    method: 'get',
    params
  })
}

export function readUserReceiveCommentCount (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/web/comment/readUserReceiveCommentCount',
    method: 'post',
    params
  })
}

