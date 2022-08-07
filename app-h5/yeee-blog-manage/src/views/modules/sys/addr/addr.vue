<template>
  <div class="mod-addr">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-input v-model="dataForm.nm" placeholder="地址名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.username" placeholder="地址用户" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.addrDtl" placeholder="地址详情" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" @click="listData()">查询</el-button>
        <el-button v-if="$hasPerm('sys:addr:add')" size="small" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="$hasPerm('sys:addr:del')" size="small" type="danger" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
        <el-button v-if="$hasPerm('sys:addr:expt')" size="small" @click="exptHandle()">导出</el-button>
        <el-button v-if="$hasPerm('sys:addr:impt')" size="small" @click="imptHandle()">导入</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight" 
            @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="nm" label="地址名称" sortable="custom" header-align="center" align="center" width="120"></el-table-column>
      <el-table-column prop="username" label="地址用户" sortable="custom" header-align="center" align="center" width="120"></el-table-column>
      <el-table-column prop="receNm" label="收货人" sortable="custom" header-align="center" align="center" width="100"></el-table-column>
      <el-table-column prop="receMob" label="收货手机" sortable="custom" header-align="center" align="center" width="120"></el-table-column>
      <!-- <el-table-column prop="recePhone" label="收货电话" sortable="custom" header-align="center" align="center"></el-table-column> -->
      <!-- <el-table-column prop="addrCd" label="地址编码" sortable="custom" header-align="center" align="center"></el-table-column> -->
      <el-table-column prop="addrNm" label="地区" sortable="custom" header-align="center" align="center" width="180"></el-table-column>
      <el-table-column prop="addrDtl" label="地址" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="hasDef" label="默认" sortable="custom" header-align="center" align="center" width="80">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.hasDef === 1" size="small" type="success">是</el-tag>
          <el-tag v-else size="small">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('sys:addr:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('sys:addr:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button>
          <el-button v-if="$hasPerm('sys:addr:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
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
    <!-- 弹窗, 新增 / 修改 -->
    <edit v-if="editVisible" ref="edit" @refreshDataList="listData"></edit>
    <!-- 弹窗, 导入 -->
    <impt v-if="imptVisible" ref="impt" @refreshDataList="listData"></impt>
  </div>
</template>

<script>
  import info from './addr-info'
  import edit from './addr-edit'
  import impt from './addr-impt'
  import grid from '@/mixins/grid'
  export default {
    mixins: [grid],
    data () {
      return {
        module: '/sys/addr/',
        gridOptions: {},
        dataForm: {}
      }
    },
    components: {
      info, edit, impt
    }
  }
</script>
