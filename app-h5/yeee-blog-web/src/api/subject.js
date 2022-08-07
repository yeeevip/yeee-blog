import request from '@/utils/request'

export function getSubjectList(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/topic/page',
    method: 'post',
    params
  })
}

export function getSubjectItemList(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/article/page',
    method: 'post',
    data: params
  })
}



