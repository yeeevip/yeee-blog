import Vue from 'vue'
import Router from 'vue-router'
import HomeIndex from '@/views/home'
import store from '../store'
Vue.use(Router)

export const constantRouterMap = [
  {
    path: '/',
    name: 'index',
    component: HomeIndex,
    children: [
      { path: '/', component: () => import('@/views/index') },
      { path: '/about', component: () => import('@/views/about') },
      { path: '/life', component: () => import('@/views/life') },
      { path: '/list', component: () => import('@/views/list') },
      { path: '/sort', component: () => import('@/views/sort') },
      { path: '/share', component: () => import('@/views/share') },
      { path: '/topic', component: () => import('@/views/topic') },
      { path: '/classify', component: () => import('@/views/classify') },
      { path: '/label', component: () => import('@/views/label') },
      { path: '/time', component: () => import('@/views/time') },
      { path: '/info/:blogId', name: 'info', component: () => import('@/views/info') },
      { path: '/messageBoard', component: () => import('@/views/messageBoard') },
    ]
  },
  { path: "/index.html", redirect: { name: "index" } },
  { path: '/404', component: () => import('@/views/404') },
  { path: '/500', component: () => import('@/views/500') },
  { path: '/502', component: () => import('@/views/502') },
  { path: '/*', component: () => import('@/views/404') }

]

const router = new Router({
  mode: 'history',
  routes: constantRouterMap
})

// router.beforeEach((to, from, next) => {
//   console.log("获取路径", to, from)
//   console.log("------------")
//   console.log(store.state.app.webNavbarList)
//   next()
// })

export default router
