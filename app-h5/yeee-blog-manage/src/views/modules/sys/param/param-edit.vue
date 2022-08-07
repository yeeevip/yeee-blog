<template>
  <el-dialog :title="!id ? '新增' : '修改'"  class="mod-param-edit"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-row :gutter="22">
        <el-col :span="22">
          <el-form-item label="键" prop="cd">
            <el-input v-model="dataForm.cd" placeholder="键"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="值" prop="val">
            <el-input v-model="dataForm.val" placeholder="值"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="排序" prop="seq">
            <el-input-number :min="1" v-model="dataForm.seq"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="22">
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

  export default {
    data () {
      return {
        id: null,
        visible: false,
        dataForm: {
          id: '',
          cd: '',
          val: '',
          seq: '',
          rmks: '',
          catCd: '20050.160'
        },
        dataRule: {
          cd: [
            { required: true, message: '键不能为空', trigger: 'blur' }
          ],
          val: [
            { required: true, message: '值不能为空', trigger: 'blur' }
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
      },
      // 表单提交
      dataFormSubmit: debounce(function () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let url = !this.id ? '/sys/param/add' : '/sys/param/upd'
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
<style>
.mod-param-edit .el-dialog{
  width: 45%
}
</style>