<template>
  <div class="mod-inte">
    <el-form>
      <el-form-item>
        <el-button size="small" @click="getInfo()">刷新</el-button>
        <el-button v-if="$hasPerm('sys:param:config')" size="small" type="primary" @click="configHandle()">接口配置</el-button>
      </el-form-item>
    </el-form>
    <el-form :inline="true" v-for="conf in vals" :key="conf.port" label-width="80px">
      <el-row :gutter="20">
        <el-card>
          <el-col :span="8">
            <el-form-item label="名称:">
              <label>{{ conf.nm }}</label>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="url:">
              <label>{{ domain }}:{{ conf.port }}</label>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="接口文档:">
              <el-button type="primary" plain @click="openInte('swagger-ui', conf.port)">swagger-ui</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="info" plain @click="openInte('doc', conf.port)">doc</el-button>
            </el-form-item>
          </el-col>
        </el-card>
      </el-row>
    </el-form>
    <!-- 弹窗, 配置 -->
    <config v-if="configVisible" ref="config" @refreshDataList="getInfo"></config>
  </div>
</template>

<script>
  // import { baseUrl } from '@/utils/index'
  import config from './inte-config'
  export default {
    data () {
      return {
        configVisible: false,
        domain: window.SITE_CONFIG.baseUrl,
        cd: 'sys-inte',
        vals: []
      }
    },
    components: {
      config
    },
    mounted () {
      this.getInfo()
    },
    methods: {
      configHandle () {
        this.configVisible = true
        this.$nextTick(() => {
          this.$refs.config.init()
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
      openInte (html, port) {
        window.open(this.domain + ':' + port + '/' + html + '.html')
      }
    }
  }
</script>
<style>
.mod-inte .el-row {
  padding-bottom: 10px;
}
.mod-inte .el-card {
  height: 54px;
}
.mod-inte .el-form-item {
  margin-top: -10px
}
</style>

