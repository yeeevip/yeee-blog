<template>
  <el-dialog :title="!id ? '新增' : '修改'" class="mod-label-edit"
             :close-on-click-modal="false"
             :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="名称" prop="name">
            <el-input v-model="dataForm.name" placeholder="名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="编码" prop="code">
            <el-input v-model="dataForm.code" placeholder="名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="排序" prop="sort">
            <el-input v-model="dataForm.sort" placeholder="排序"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="类型" prop="status">
            <el-select v-model="dataForm.type" clearable  placeholder="状态">
              <el-option label="普通" value='1' />
              <el-option label="业务" value='2' />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="状态" prop="status">
            <el-select v-model="dataForm.status" clearable  placeholder="状态">
              <el-option label="启用" value='1' />
              <el-option label="禁用" value='0' />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="说明" prop="remark">
            <el-input v-model="dataForm.remark" placeholder="说明"></el-input>
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
import { isEmail, isMobile } from '@/utils/validate'
export default {
  data () {
    var validateEmail = (rule, value, callback) => {
      if (value && !isEmail(value)) {
        callback(new Error('邮箱格式错误'))
      } else {
        callback()
      }
    }
    var validateMobile = (rule, value, callback) => {
      if (value && !isMobile(value)) {
        callback(new Error('手机号格式错误'))
      } else {
        callback()
      }
    }
    var validateFormDataUnique = (rule, value, callback) => {
      let qry = this.$query.new()
      this.$query.toW(qry, rule.field, value, 'EQ')
      this.$http.get('/manage/cf-user/exist?query=' + encodeURIComponent(this.$query.toJsonStr(qry))).then(({data: res}) => {
        if (res.code !== 200) {
          callback(new Error('服务器异常,校验失败'))
        }
        if (value !== this.dataFormOrigin[rule.field] && res.data) {
          callback(new Error('已被使用'))
        } else {
          callback()
        }
      }).catch(() => {
        callback(new Error('校验失败'))
      })
    }
    return {
      id: null,
      visible: false,
      otherParamsVisible: false,
      roleList: [],
      orgList: [],
      dataForm: {
        username: ''
      },
      dataFormOrigin: [],
      dataRule: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { validator: validateFormDataUnique, trigger: 'blur' }
        ],
        email: [
          { validator: validateEmail, trigger: 'blur' }
        ],
        mobile: [
          { validator: validateMobile, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = null
      this.dataFormOrigin = {}
      this.id = id
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.id) {
          this.getInfo()
        }
      })
    },
    // 获取信息
    getInfo () {
      this.$http.json().post('/manage/blog/label/info', {
        'id': this.id
      }).then(({data: res}) => {
        if (res.code !== 200) {
          return this.$message.error(res.msg)
        }
        this.dataForm = {
          ...this.dataForm,
          ...res.data
        }
        this.dataFormOrigin = {
          ...this.dataFormOrigin,
          ...res.data
        }
        this.dataForm.status = this.dataForm.status.toString()
        this.dataForm.type = this.dataForm.type.toString()
      }).catch(() => {})
    },
    // 表单提交
    dataFormSubmit: debounce(function () {
      // console.log(this.dataForm)
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http.json().post(!this.dataForm.id ? '/manage/blog/label/add' : '/manage/blog/label/upd', {...this.dataForm}).then(({data: res}) => {
            if (res && res.code === 200) {
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
              this.$message.error(res.message)
            }
          })
        }
      })
    }, 1000, { 'leading': true, 'trailing': false })
  }
}
</script>
<style lang="scss">
.mod-label-edit {
  .input-bar i {
    padding-top: 6px;
    font-size: 24px;
    color: #3a8ee6;
    transition-property: color;
    transition-duration: 0.15s;
    transition-timing-function: linear;
    transition-delay: initial;
  }
}
</style>
