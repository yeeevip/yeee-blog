<template>
  <el-dialog :title="!id ? '新增' : '修改'"  class="mod-wxMenu-edit"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="匹配规则" prop="hasMatchrule">
            <el-radio-group v-model="dataForm.hasMatchrule">
              <el-radio :label = 1>匹配</el-radio>
              <el-radio :label = 0>不匹配</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="名称" prop="nm">
            <el-input v-model="dataForm.nm" placeholder="名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="排序" prop="seq">
            <el-input-number :min="1" v-model="dataForm.seq"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="备注" prop="rmks">
            <el-input v-model="dataForm.rmks" placeholder="备注"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20" v-if="dataForm.hasMatchrule === 1">
        <el-col :span="11">
          <el-form-item label="用户标签的ID" prop="tagId">
            <el-input v-model="dataForm.tagId" placeholder="用户标签的ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label=" 性别" prop="sex">
            <!-- <el-input v-model="dataForm.sex" placeholder=" 性别：男（1）女（2）"></el-input> -->
            <el-select v-model="dataForm.sex" placeholder="请选择" clearable>
              <el-option label="男" value="1"/>
              <el-option label="女" value="2"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="客户端版本" prop="clientPlatformType">
            <!-- <el-input v-model="dataForm.clientPlatformType" placeholder="客户端版本，当前只具体到系统型号：IOS(1) ANDROID(2) OTHERS(3)"></el-input> -->
            <el-select v-model="dataForm.clientPlatformType" placeholder="请选择" clearable>
              <el-option label="IOS" value="1"/>
              <el-option label="ANDROID" value="2"/>
              <el-option label="OTHERS" value="3"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="国家信息" prop="country">
            <el-input v-model="dataForm.country" placeholder="国家信息"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="省份信息" prop="province">
            <el-input v-model="dataForm.province" placeholder="省份信息"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="城市信息" prop="city">
            <el-input v-model="dataForm.city" placeholder="城市信息"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="语言信息" prop="language">
            <el-input v-model="dataForm.language" placeholder="语言信息"></el-input>
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
          hasMatchrule: 1,
          tagId: '',
          sex: '',
          clientPlatformType: '',
          country: '',
          province: '',
          city: '',
          language: '',
          seq: '',
          rmks: ''
        },
        dataRule: {
          nm: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          hasMatchrule: [
            { required: true, message: '匹配规则不能为空', trigger: 'blur' }
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
      // 获取微信菜单信息
      getInfo () {
        this.$http.get('/sys/wxMenu/info/' + this.id).then(({data: res}) => {
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
            let url = !this.id ? '/sys/wxMenu/add' : '/sys/wxMenu/upd'
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
