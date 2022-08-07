<template>
  <el-dialog title="接口配置" class="mod-sms-config" :close-on-click-modal="false" :visible.sync="visible">
    <el-form label-width="120px" @keyup.enter.native="dataFormSubmit()">
      <el-row :gutter="20">
        <el-col :span="8" > 
          <el-form-item label=" ">
            <el-button size="small" type="primary" @click="appendInteHandle()">继续添加接口文档</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20" v-for="item in vals" :key="item.port">
        <el-col :span="8" > 
          <el-form-item label="名称">
            <el-input v-model="item.nm" placeholder="名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6" > 
          <el-form-item label="端口">
            <el-input-number :min="1" v-model="item.port"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="2"> 
          <el-form-item>
            <el-button size="small" type="danger" icon="el-icon-delete" title="删除" @click="deleteInteHandle(item.nm, item.port)"></el-button>
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
        visible: false,
        domain: window.SITE_CONFIG.baseUrl,
        cd: 'sys-inte',
        vals: [],
        dataForm: {
          cd: 'sys-inte',
          type: 0,
          val: ''
        },
        dataRule: []
      }
    },
    methods: {
      init (id) {
        this.visible = true
        this.$nextTick(() => {
          this.getInfo()
        })
      },
      appendInteHandle () {
        let val = {'nm': '', port: 0}
        this.vals.push(val)
      },
      deleteInteHandle (nm, port) {
        this.$confirm(`确定删除?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          for (var ind in this.vals) {
            let item = this.vals[ind]
            if (nm === item.nm && port === item.port) {
              this.vals.splice(ind, 1)
            }
          }
        })
      },
      // 获取系统日志信息
      getInfo () {
        this.$http.get(`/sys/param/getValByKey?key=${this.cd}`).then(({data: res}) => {
          if (res.code !== 0) {
            return this.$message.error(res.msg)
          }
          if (res.data) {
            this.vals = JSON.parse(res.data)
          }
        }).catch(() => {})
      },
      // 表单提交
      dataFormSubmit: debounce(function () {
        this.dataForm.val = JSON.stringify(this.vals)
        // console.log(this.dataForm)
        this.$http.post('/sys/param/config', {...this.dataForm}).then(({data: res}) => {
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
      }, 1000, { 'leading': true, 'trailing': false })
    }
  }
</script>

