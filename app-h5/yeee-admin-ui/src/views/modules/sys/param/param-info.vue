<template>
  <el-dialog title="详情"  class="mod-param-info"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="120px">
      <el-row :gutter="22">
        <el-col :span="22">
          <el-form-item label="键:">
            <label>{{ dataForm.cd }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="值:">
            <label>{{ dataForm.val }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="排序:">
            <label>{{ dataForm.seq }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="备注:">
            <label>{{ dataForm.rmks }}</label>
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
      // 获取系统参数信息
      getInfo () {
        this.$http.get('/sys/param/info/' + this.id).then(({data: res}) => {
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
<style>
.mod-param-info .el-dialog{
  width: 45%
}
</style>