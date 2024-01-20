<template>
  <div class="mod-articleEditTxt">
    <el-form :model="dataForm" ref="dataForm" label-width="0px">
      <el-form-item label="" prop="content">
        <wangeditor ref="wangeditor" :getEditConts="getEditConts" :insertImg="insertImgHandle"></wangeditor>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dataFormSubmit()">保存</el-button>
      <el-button @click="blogPreview()">预览</el-button>
    </span>
  </div>
</template>

<script>
import debounce from 'lodash/debounce'
import wangeditor from '@/components/wangeditor'
import { mainRoutes } from '@/router'

export default {
  data () {
    return {
      id: null,
      visible: false,
      otherParamsVisible: false,
      roleList: [],
      orgList: [],
      dataForm: {
        username: ''
      },
      classifyList: [],
      labelList: [],
      topicList: []
    }
  },
  components: {
    wangeditor
  },
  activated () {
    this.init(this.$route.params.id)
  },
  methods: {
    init (id) {
      this.dataForm.id = null
      this.id = id
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.id) {
          this.getInfo()
        }
      })
    },
    // 获取信息
    getInfo () {
      this.$http.json().post('/manage/blog/article/info', {
        'id': this.id
      }).then(({data: res}) => {
        if (res.code !== 200) {
          return this.$message.error(res.msg)
        }
        this.dataForm = {
          ...this.dataForm,
          ...res.data
        }
        this.$refs.wangeditor.setEditorContent(res.data.content)
      }).catch(() => {})
    },
    // 表单提交
    dataFormSubmit: debounce(function () {
      // console.log(this.dataForm)
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          let submitData = {}
          submitData.id = this.dataForm.id
          submitData.content = this.dataForm.content
          this.$http.json().post('/manage/blog/article/upd-txt', submitData).then(({data: res}) => {
            if (res && res.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 500,
                onClose: () => {

                }
              })
            } else {
              this.$message.error(res.message)
            }
          })
        }
      })
    }, 1000, { 'leading': true, 'trailing': false }),
    blogPreview () {
      let row = this.dataForm
      // 组装路由名称, 并判断是否已添加, 如是: 则直接跳转
      var routeName = `blog-article/articlePreview__${row.id}`
      var route = window.SITE_CONFIG['dynamicRoutes'].filter(item => item.name === routeName)[0]
      var params = { 'id': row.id }
      if (route) {
        return this.$router.push({ name: routeName, params: params })
      }
      // 否则: 添加并全局变量保存, 再跳转
      route = {
        path: routeName,
        // component: () => import(`@/views/modules/blog/article/articlePreview`),
        component: (resolve) => require([`@/views/modules/blog/article/articlePreview`], resolve),
        name: routeName,
        meta: {
          ...window.SITE_CONFIG['contentTabDefault'],
          menuId: this.$route.meta.menuId,
          title: `预览 - ${row.title}`
        }
      }
      this.$router.addRoutes([
        {
          ...mainRoutes,
          name: `main-dynamic__${route.name}`,
          children: [route]
        }
      ])
      window.SITE_CONFIG['dynamicRoutes'].push(route)
      this.$router.push({ name: route.name, params: params })
    },
    getEditConts (value) {
      this.dataForm.content = value
    },
    insertImgHandle (res, insertImg, editor) {

    }
  }
}
</script>
<style lang="scss">
.mod-article-edit {
  .input-bar i {
    padding-top: 6px;
    font-size: 24px;
    color: #3a8ee6;
    transition-property: color;
    transition-duration: 0.15s;
    transition-timing-function: linear;
    transition-delay: initial;
  }
}
</style>
