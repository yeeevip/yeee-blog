<template>
  <el-dialog :title="!id ? '新增' : '修改'"  class="mod-ad-edit"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="源地址" prop="srcUrl">
            <imgUpload :files = "dataForm.srcUrl" @modify="imgUploadHandle" :limit="limit"></imgUpload>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="名称" prop="nm">
            <el-input v-model="dataForm.nm" placeholder="名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="编码" prop="cd">
            <el-input v-model="dataForm.cd" placeholder="编码"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="排序" prop="seq">
            <el-input-number :min="1" v-model="dataForm.seq"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="目标地址" prop="tgtUrl">
            <el-input v-model="dataForm.tgtUrl" placeholder="目标地址"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="备注" prop="rmks">
            <el-input v-model="dataForm.rmks" placeholder="备注"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import debounce from 'lodash/debounce'
  import imgUpload from '@/components/imgUpload'

  export default {
    data () {
      return {
        id: null,
        limit: 1,
        visible: false,
        dataForm: {
          id: '',
          cd: '',
          nm: '',
          srcUrl: '',
          tgtUrl: '',
          seq: '',
          rmks: ''
        },
        dataRule: {
          srcUrl: [
            { required: true, message: '源地址不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    components: {
      imgUpload
    },
    methods: {
      init (id) {
        this.id = id
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
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
      },
      imgUploadHandle (val) {
        this.$set(this.dataForm, 'srcUrl', val)
      },
      // 表单提交
      dataFormSubmit: debounce(function () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let url = !this.id ? '/sys/ad/add' : '/sys/ad/upd'
            this.$http.post(url, {...this.dataForm}).then(({data: res}) => {
              if (res && res.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(res.msg)
              }
            })
          }
        })
      }, 1000, { 'leading': true, 'trailing': false })
    }
  }
</script>
