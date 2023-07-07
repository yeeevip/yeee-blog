<template>
  <el-dialog title="发送信息"  class="mod-sms-send"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="120px">
      <el-row :gutter="22">
        <el-col :span="11">
          <el-form-item label="编码">
            <label>{{ dataForm.cd }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="名称">
            <label>{{ dataForm.nm }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="内容">
            <label>{{ dataForm.cont }}</label>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="电话" prop="phones">
            <el-input type="textarea" v-model="dataForm.phones" placeholder="多个电话逗号隔开，如：18858088888, 19959099999"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="参数" prop="params">
            <el-input type="textarea" v-model="dataForm.params" placeholder="如：{'code': 'hello'}"></el-input>
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
          nm: '',
          cont: '',
          phones: '',
          params: ''
        },
        dataRule: {
          phones: [
            { required: true, message: '电话不能为空', trigger: 'blur' }
          ],
          params: [
            { required: true, message: '参数不能为空', trigger: 'blur' }
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
      // 获取系统短信信息
      getInfo () {
        this.$http.get('/sys/sms/info/' + this.id).then(({data: res}) => {
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
            this.$http.post('/general/sms/send', {...this.dataForm}).then(({data: res}) => {
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
