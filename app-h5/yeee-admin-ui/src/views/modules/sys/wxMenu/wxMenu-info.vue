<template>
  <el-dialog title="详情"  class="mod-wxMenu-info"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="名称:">
            <label>{{ dataForm.nm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="匹配规则:">
            <label>{{ dataForm.hasMatchrule | MatchruleHandle }}</label>
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
      </el-row>
      <el-row :gutter="20" v-if="dataForm.hasMatchrule === 1">
        <el-col :span="12">
          <el-form-item label="用户标签的ID:">
            <label>{{ dataForm.tagId }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label=" 性别:">
            <label>{{ dataForm.sex | sexHandle }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户端版本:">
            <label>{{ dataForm.clientPlatformType | clientPlatformTypeHandle }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="国家信息:">
            <label>{{ dataForm.country }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="省份信息:">
            <label>{{ dataForm.province }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="城市信息:">
            <label>{{ dataForm.city }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="语言信息:">
            <label>{{ dataForm.language }}</label>
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
      MatchruleHandle (val) {
        if (val === 0) {
          return '不匹配'
        } else {
          return '匹配'
        }
      },
      sexHandle (val) {
        if (val === 1) {
          return '男'
        } else {
          return '女'
        }
      },
      clientPlatformTypeHandle (val) { // IOS(1) ANDROID(2) OTHERS(3)
        if (val === 1) {
          return 'IOS'
        } else if (val === 2) {
          return 'ANDROID'
        } else {
          return 'OTHERS'
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
      // 获取微信菜单信息
      getInfo () {
        this.$http.get('/sys/wxMenu/info/' + this.id).then(({data: res}) => {
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
