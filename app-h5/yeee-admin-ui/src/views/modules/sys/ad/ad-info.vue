<template>
  <el-dialog title="详情"  class="mod-ad-info"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" ref="dataForm" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="名称:">
            <label>{{ dataForm.nm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="编码:">
            <label>{{ dataForm.cd }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="源地址:">
            <imgPreivew :files="dataForm.srcUrl" fileWidth="148px"></imgPreivew>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="目标地址:">
            <label>{{ dataForm.tgtUrl }}</label>
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
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import imgPreivew from '@/components/imgPreview'
  export default {
    data () {
      return {
        id: null,
        visible: false,
        dataForm: {}
      }
    },
    components: {
      imgPreivew
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
      // 获取系统广告信息
      getInfo () {
        this.$http.get('/sys/ad/info/' + this.id).then(({data: res}) => {
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
