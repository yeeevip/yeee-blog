<template>
  <el-dialog :title="!id ? '新增' : '修改'"  class="mod-tenant-edit"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="名称" prop="nm">
            <el-input v-model="dataForm.nm" placeholder="名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="有效开始时间" prop="vldFrTm">
            <el-date-picker v-model="dataForm.vldFrTm" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="有效开始时间"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="状态" prop="status">
            <el-radio-group v-model="dataForm.status">
              <el-radio label="0">有效</el-radio>
              <el-radio label="1">失效</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="有效结束时间" prop="vldToTm">
            <el-date-picker v-model="dataForm.vldToTm" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="有效结束时间"></el-date-picker>
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
  export default {
    data () {
      return {
        id: null,
        visible: false,
        dataForm: {
          id: '',
          nm: '',
          vldFrTm: '',
          vldToTm: '',
          status: '0'
        },
        dataRule: {
          nm: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          vldFrTm: [
            { required: true, message: '有效开始时间不能为空', trigger: 'blur' }
          ],
          vldToTm: [
            { required: true, message: '有效结束时间不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ]
        }
      }
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
      // 获取系统租户信息
      getInfo () {
        this.$http.get('/sys/tenant/info/' + this.id).then(({data: res}) => {
          if (res.code !== 0) {
            return this.$message.error(res.msg)
          }
          this.dataForm = {
            ...this.dataForm,
            ...res.data
          }
        }).catch(() => {})
      },
      // 表单提交
      dataFormSubmit: debounce(function () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let url = !this.id ? '/sys/tenant/add' : '/sys/tenant/upd'
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
