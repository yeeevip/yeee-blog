import request from '@/utils/request'

export function getTopicList(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/topic/page',
    method: 'post',
    params
  })
}

export function getTopicItemList(params) {
  return request({
    url: process.env.WEB_API + '/api/blog/article/page',
    method: 'post',
    data: params
  })
}



