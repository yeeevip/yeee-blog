<template>
  <div class="mod-sms">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-input v-model="dataForm.cd" placeholder="编码" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.nm" placeholder="名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" @click="listData()">查询</el-button>
        <el-button v-if="$hasPerm('sys:sms:add')" size="small" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="$hasPerm('sys:sms:config')" size="small" type="primary" @click="configHandle()">短信配置</el-button>
        <el-button v-if="$hasPerm('sys:sms:del')" size="small" type="danger" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
        <!-- <el-button size="small" @click="exptHandle()">导出</el-button>
        <el-button size="small" @click="imptHandle()">导入</el-button> -->
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight" 
            @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="cd" label="编码" sortable="custom" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="nm" label="名称" sortable="custom" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="cont" label="内容" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="seq" label="排序" sortable="custom" header-align="center" align="center" width="80"></el-table-column>
      <el-table-column prop="type" label="类型" sortable="custom" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type === 0" size="small" type="success">验证码</el-tag>
          <el-tag v-else size="small" type="info">短信通知</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="120">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('sys:sms:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('sys:sms:send')" type="text" size="small" @click="sendHandle(scope.row.id)" icon="el-icon-message" title="发送"></el-button>
          <el-button v-if="$hasPerm('sys:sms:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button>
          <el-button v-if="$hasPerm('sys:sms:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination v-if="this.gridOptions.isPage"
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageNo"
      :page-sizes="pageSizes"
      :page-size="pageSize"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <edit v-if="editVisible" ref="edit" @refreshDataList="listData"></edit>
    <!-- 弹窗, 详情 -->
    <info v-if="infoVisible" ref="info"></info>
    <!-- 弹窗, 导入 -->
    <impt v-if="imptVisible" ref="impt" @refreshDataList="listData"></impt>
    <!-- 弹窗, 发送 -->
    <send v-if="sendVisible" ref="send"></send>
    <!-- 弹窗, 配置 -->
    <config v-if="configVisible" ref="config"></config>
  </div>
</template>

<script>
  import edit from './sms-edit'
  import info from './sms-info'
  import impt from './sms-impt'
  import send from './sms-send'
  import config from './sms-config'
  import grid from '@/mixins/grid'
  export default {
    mixins: [grid],
    data () {
      return {
        sendVisible: false,
        configVisible: false,
        module: '/sys/sms/',
        gridOptions: {},
        defOrders: [
          {k: 'seq', t: 'asc'}
        ],
        dataForm: {}
      }
    },
    components: {
      edit, info, impt, send, config
    },
    methods: {
      sendHandle (id) {
        this.sendVisible = true
        this.$nextTick(() => {
          this.$refs.send.init(id)
        })
      },
      configHandle (id) {
        this.configVisible = true
        this.$nextTick(() => {
          this.$refs.config.init(id)
        })
      }
    }
  }
</script>
