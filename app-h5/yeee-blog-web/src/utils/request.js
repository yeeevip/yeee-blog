import axios from 'axios'
import router from '@/router/index'
import {getCookie} from "@/utils/cookieUtils";
import commonUtil from '@/utils/commonUtil'

// 创建axios实例
const service = axios.create({
  baseURL: '', // api 的 base_url
  timeout: 20000 // 请求超时时间 10秒
})

service.defaults.headers.common['Authorization'] = getCookie("token")

// request拦截器
service.interceptors.request.use(
  config => {
    config.headers.Cookie = ''
    if (getCookie("token") != undefined) {
      config.headers.Authorization = getCookie("token") // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    if (!config.data) {
      if (config.params instanceof FormData) {
        config.headers['Content-Type'] = 'multipart/form-data; charset=utf-8';
      } else if (config.params && config.params.constructor === String) {
        config.headers.post['Content-Type'] = 'application/json; charset=utf-8';
        config.data = config.params
        config.params = null
      } else {
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded; charset=utf-8';
      }
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)


// response 拦截器
service.interceptors.response.use(
  response => {
    if (response.status === 401 || response.status === 400) {
      console.log('返回错误内容', res)
      router.push('404')
      return res
    } else if (response.status === 500) {
      router.push('500')
      return Promise.reject('error')
    } else if (response.status === 502) {
      router.push('502')
      return Promise.reject('error')
    } else {
      return response.data
    }
  },
  error => {
    // 出现网络超时
    // router.push('500')
    return Promise.reject(error)
  }
)

export default service
