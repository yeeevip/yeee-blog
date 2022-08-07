<template>
  <el-dialog title="详情"  class="mod-wxMenuItm-info"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="140px">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="标题:">
            <label>{{ dataForm.name }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="响应动作类型:">
            <label>{{ dataForm.type | typeHandle }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排序:">
            <label>{{ dataForm.seq }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="备注:">
            <label>{{ dataForm.rmks }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否oauth:">
            <label>{{ dataForm.hasOauth | hasOauth }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="应用授权作用域:">
            <label>{{ dataForm.scope }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="重定向带上的参数:">
            <label>{{ dataForm.state }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="菜单KEY值:"> <!-- 菜单KEY值，用于消息接口推送 -->
            <label>{{ dataForm.ke }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="网页链接:">
            <label>{{ dataForm.url }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="永久素材ID:">
            <label>{{ dataForm.mediaId }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="小程序的appid:">
            <label>{{ dataForm.appid }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="小程序的页面路径:">
            <label>{{ dataForm.pagepath }}</label>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        id: null,
        visible: false,
        dataForm: {}
      }
    },
    filters: {
      typeHandle (val) {
        if (val === 'click') {
          return '点击推事件'
        } else if (val === 'view') {
          return '跳转URL'
        } else if (val === 'miniprogram') {
          return '跳转到小程序'
        } else if (val === 'scancode_push') {
          return '扫码推事件'
        } else if (val === 'scancode_waitmsg') {
          return '扫码推事件且弹出“消息接收中”提示框'
        } else if (val === 'pic_sysphoto') {
          return '弹出系统拍照发图'
        } else if (val === 'pic_photo_or_album') {
          return '弹出拍照或者相册发图'
        } else if (val === 'pic_weixin') {
          return '弹出微信相册发图器'
        } else if (val === 'location_select') {
          return '弹出地理位置选择器'
        } else if (val === 'media_id') {
          return '下发消息（除文本消息）'
        } else if (val === 'view_limited') {
          return '跳转图文消息URL'
        } else {
          return ''
        }
      },
      hasOauth (val) {
        if (val === 0) {
          return '否'
        } else {
          return '是'
        }
      }
    },
    methods: {
      init (id) {
        this.id = id
        this.visible = true
        this.$nextTick(() => {
          if (this.id) {
            this.getInfo()
          }
        })
      },
      // 获取微信菜单子项信息
      getInfo () {
        this.$http.get('/sys/wxMenuItm/info/' + this.id).then(({data: res}) => {
          if (res.code !== 0) {
            return this.$message.error(res.msg)
          }
          this.dataForm = {
            ...this.dataForm,
            ...res.data
          }
        }).catch(() => {})
      }
    }
  }
</script>
