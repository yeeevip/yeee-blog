<template>
  <div class="mod-label">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-input v-model="dataForm.name" placeholder="名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="dataForm.status" clearable  placeholder="状态">
          <el-option label="启用" value=1 />
          <el-option label="禁用" value=0 />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" @click="listData()">查询</el-button>
        <el-button v-if="$hasPerm('blog:label:add')" type="primary" size="small" @click="editHandle()">新增</el-button>
        <el-button v-if="$hasPerm('blog:label:del')" type="danger" size="small" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight"
              @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="name" label="名称" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="code" label="编码" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="sort" label="排序" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="remark" label="说明" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="type" label="类型" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type === 1" size="small" type="success">普通</el-tag>
          <el-tag v-else-if="scope.row.type === 2" size="small" type="danger">业务</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" size="small" type="success">启用</el-tag>
          <el-tag v-else-if="scope.row.status === 0" size="small" type="danger">禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('blog:label:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('blog:label:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button>
          <el-button v-if="$hasPerm('blog:label:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
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
  </div>
</template>

<script>
import edit from './label-edit'
import info from './label-info'
import grid from '@/mixins/grid'
export default {
  mixins: [grid],
  data () {
    return {
      module: '/manage/blog/label/',
      gridOptions: {
        isQuery: true
      },
      defOrders: [
        {k: 'createTime', t: 'desc'}
      ],
      dataMode: {
        name: 'LK'
      },
      dataForm: {}
    }
  },
  components: {
    edit, info
  },
  methods: {
  }
}
</script>
