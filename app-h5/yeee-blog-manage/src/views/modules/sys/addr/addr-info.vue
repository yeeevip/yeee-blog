<template>
  <el-dialog title="详情"  class="mod-addr-info"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="地址名称:">
            <label>{{ dataForm.nm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地址用户:">
            <label>{{ dataForm.username }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="收货人:">
            <label>{{ dataForm.receNm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="收货手机:">
            <label>{{ dataForm.receMob }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="收货电话:">
            <label>{{ dataForm.recePhone }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地址编码:">
            <label>{{ dataForm.addrCd }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地址名称:">
            <label>{{ dataForm.addrNm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="详细地址:">
            <label>{{ dataForm.addrDtl }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="默认:">
            <label>{{ dataForm.hasDef === 0 ? '否' : '是'}}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
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
      // 获取系统地址信息
      getInfo () {
        this.$http.get('/sys/addr/info/' + this.id).then(({data: res}) => {
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
