<template>
  <div class="mod-smsLog">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-input v-model="dataForm.mob" placeholder="手机号码" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.内容" placeholder="内容" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="dataForm.sendTm"
          type="daterange"
          value-format="yyyy-MM-dd"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button size="small" @click="listData()">查询</el-button>
        <!-- <el-button size="small" type="primary" @click="editHandle()">新增</el-button> -->
        <el-button v-if="$hasPerm('sys:smsLog:del')" size="small" type="danger" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
        <!-- <el-button size="small" @click="exptHandle()">导出</el-button>
        <el-button size="small" @click="imptHandle()">导入</el-button> -->
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight" 
            @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="mob" label="手机号码" sortable="custom" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="cont" label="内容" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="sendTm" label="发送时间" sortable="custom" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="status" label="状态" sortable="custom" header-align="center" align="center" width="150">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" size="small">成功</el-tag>
          <el-tag v-else size="small" type="danger">失败</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('sys:smsLog:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('sys:smsLog:resend')" type="text" size="small" @click="resendHandle(scope.row.id)" icon="el-icon-message" title="重发"></el-button>
          <!-- <el-button type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button> -->
          <el-button v-if="$hasPerm('sys:smsLog:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
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
    <!-- 弹窗, 详情 -->
    <info v-if="infoVisible" ref="info"></info>
  </div>
</template>

<script>
  import info from './smsLog-info'
  import grid from '@/mixins/grid'
  export default {
    mixins: [grid],
    data () {
      return {
        module: '/sys/smsLog/',
        gridOptions: {},
        defOrders: [
          {k: 'sendTm', t: 'desc'}
        ],
        dataForm: {},
        dataMode: {
          sendTm: 'BT'
        }
      }
    },
    components: {
      info
    },
    methods: {
      resendHandle (id) {
        let config = {
          url: '/general/sms/resend/' + id,
          message: '重发短信'
        }
        this.baseHandle(config)
      }
    }
  }
</script>
