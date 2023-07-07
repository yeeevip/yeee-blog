<template>
  <el-dialog :title="!id ? '新增' : '修改'"  class="mod-show-edit"
    :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
      <el-row :gutter="20">
        <el-col :span="11">
          <el-form-item label="编码" prop="cd">
            <el-input v-model="dataForm.cd" placeholder="编码"></el-input>
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
        <el-col :span="24">
          <el-form-item label="内容" prop="cont">
            <wangeditor ref="wangeditor" :getEditConts="getEditConts" :insertImg="insertImgHandle"></wangeditor>
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
  import wangeditor from '@/components/wangeditor'

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
          seq: '',
          rmks: ''
        },
        dataRule: {
          cd: [
            { required: true, message: '编码不能为空', trigger: 'blur' }
          ],
          nm: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          cont: [
            { required: true, message: '内容不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    components: {
      wangeditor
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
      // 获取系统展现信息
      getInfo () {
        this.$http.get('/sys/show/info/' + this.id).then(({data: res}) => {
          if (res.code !== 0) {
            return this.$message.error(res.msg)
          }
          this.dataForm = {
            ...this.dataForm,
            ...res.data
          }
          this.$refs.wangeditor.setEditorContent(res.data.cont)
        }).catch(() => {})
      },
      // 获取编辑器内容 格式为html
      getEditConts (value) {
        this.dataForm.cont = value
      },
      // 插入图片操作
      insertImgHandle (res, insertImg, editor) {
        if (res && res.code === 0) {
          insertImg(res.data)
        }
      },
      // 表单提交
      dataFormSubmit: debounce(function () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            let url = !this.id ? '/sys/show/add' : '/sys/show/upd'
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
