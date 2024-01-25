import request from '@/utils/request'
import commonUtil from '@/utils/commonUtil'

/**
 * 获取搜索模式
 * @param params
 * @returns {*}
 */
export function getSearchModel (params) {
  // return request({
  //   url: process.env.WEB_API + '/search/getSearchModel',
  //   method: 'get',
  //   params
  // })
  return new Promise(resolve => {
    resolve({"code": commonUtil.ECode.SUCCESS})
  })
}

/**
 * 通过SQL搜索博客
 * @param params
 */
export function searchBlog (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/search/sqlSearchBlog',
    method: 'get',
    params
  })
}

/**
 * 通过ElasticSearch搜索博客
 * @param params
 */
export function searchBlogByES (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/search/elasticSearchBlog',
    method: 'get',
    params
  })
}

/**
 * 通过solr搜索博客
 * @param params
 */
export function searchBloBySolr (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/search/solrSearchBlog',
    method: 'get',
    params
  })
}

export function searchBlogByLabel (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    params
  })
}

export function searchBlogByClassify (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/article/page',
    method: 'post',
    params
  })
}

export function searchBlogByAuthor (params) {
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/search/searchBlogByAuthor',
    method: 'get',
    params
  })
}
