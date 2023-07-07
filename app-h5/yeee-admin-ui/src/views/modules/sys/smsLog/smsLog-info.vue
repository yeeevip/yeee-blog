<template>
  <el-dialog title="详情"  class="mod-smsLog-info"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="短信编码:">
            <label>{{ dataForm.smsCd }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态:">
            <label>{{ dataForm.statNm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手机号码:">
            <label>{{ dataForm.mob }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="业务ID:">
            <label>{{ dataForm.outId }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="参数:">
            <label>{{ dataForm.param }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分组:">
            <label>{{ dataForm.grpNo }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="内容:">
            <label>{{ dataForm.cont }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发送时间:">
            <label>{{ dataForm.sendTm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="接收时间:">
            <label>{{ dataForm.receTm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="请求ID:">
            <label>{{ dataForm.reqId }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发送回执ID:">
            <label>{{ dataForm.bizId }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态码:">
            <label>{{ dataForm.sendNo }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态码信息:">
            <label>{{ dataForm.sendMsg }}</label>
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
      // 获取系统短信日志信息
      getInfo () {
        this.$http.get('/sys/smsLog/info/' + this.id).then(({data: res}) => {
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
