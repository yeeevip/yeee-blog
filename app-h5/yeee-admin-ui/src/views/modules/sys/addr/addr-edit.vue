<template>
  <el-dialog :title="!id ? '新增' : '修改'"  class="mod-addr-edit"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="地址名称" prop="nm">
            <el-input v-model="dataForm.nm" placeholder="地址名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="地址用户" prop="userId">
            <el-select v-model="dataForm.userId" filterable remote clearable
              placeholder="请输入用户名关键词查询" :remote-method="listUser" :loading="userLoading">
              <el-option
                v-for="item in users"
                :key="item.id"
                :label="item.username"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="收货人" prop="receNm">
            <el-input v-model="dataForm.receNm" placeholder="姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="收货手机" prop="receMob">
            <el-input v-model="dataForm.receMob" placeholder="手机"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="区域" prop="addrCd">
            <region ref="region" :codes="dataForm.addrCd" @modify="regionHandle"></region>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="收货电话" prop="recePhone">
            <el-input v-model="dataForm.recePhone" placeholder="电话"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="22">
          <el-form-item label="详细地址" prop="addrDtl">
            <el-input v-model="dataForm.addrDtl" placeholder="详细地址"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="默认" prop="hasDef">
            <el-radio-group v-model="dataForm.hasDef">
              <el-radio label="0">否</el-radio>
              <el-radio label="1">是</el-radio>
            </el-radio-group>
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
  import region from '@/components/region'
  export default {
    data () {
      return {
        id: null,
        visible: false,
        userLoading: false,
        users: [],
        dataForm: {
          id: '',
          nm: '',
          userId: '',
          username: '',
          receNm: '',
          receMob: '',
          recePhone: '',
          addrCd: '',
          addrNm: '',
          addrDtl: '',
          hasDef: '0',
          rmks: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '地址用户主键不能为空', trigger: 'blur' }
          ],
          receNm: [
            { required: true, message: '收货人不能为空', trigger: 'blur' }
          ],
          receMob: [
            { required: true, message: '收货手机不能为空', trigger: 'blur' }
          ],
          addrCd: [
            { required: true, message: '地址编码不能为空', trigger: 'blur' }
          ],
          addrDtl: [
            { required: true, message: '详细地址不能为空', trigger: 'blur' }
          ],
          hasDef: [
            { required: true, message: '是否默认不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    components: {
      region
    },
    methods: {
      init (id) {
        this.id = id
        this.visible = true
        this.userLoading = false
        this.dataForm.userId = ''
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.id) {
            this.getInfo()
          }
        })
      },
      regionHandle (cdVal, nmVal) {
        this.$set(this.dataForm, 'addrCd', cdVal)
        this.$set(this.dataForm, 'addrNm', nmVal)
      },
      listUser (val) {
        if (val !== '') {
          this.userLoading = true
          let qry = this.$query.new()
          this.$query.toW(qry, 'username', val)
          this.$http.get('/sys/user/list?query=' + this.$query.toEncode(qry)).then(({data: res}) => {
            this.userLoading = false
            if (res.code !== 0) {
              return this.$message.error(res.msg)
            }
            this.users = res.data.list
          }).catch(() => {})
        }
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
          this.listUser(res.data.username)
        }).catch(() => {})
      },
      // 表单提交
      dataFormSubmit: debounce(function () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let url = !this.id ? '/sys/addr/add' : '/sys/addr/upd'
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
