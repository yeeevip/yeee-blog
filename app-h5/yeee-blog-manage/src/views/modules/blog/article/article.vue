<template>
  <div class="mod-article">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-input v-model="dataForm.classifyId" placeholder="分类ID" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.title" placeholder="标题" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="dataForm.oriFlag" clearable  placeholder="是否原创">
          <el-option label="是" value=1 />
          <el-option label="否" value=0 />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="dataForm.status" clearable  placeholder="发布状态">
          <el-option label="已发布" value=1 />
          <el-option label="未发布" value=0 />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" @click="listData()">查询</el-button>
        <el-button v-if="$hasPerm('blog:article:add')" type="primary" size="small" @click="editHandle()">新增</el-button>
        <el-button v-if="$hasPerm('blog:article:del')" type="danger" size="small" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight"
              @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="classifyId" label="分类" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="title" label="标题" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="titleImg" label="标题图" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <filePreview :files="scope.row.titleImg" :imageShow=true></filePreview>
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="oriFlag" label="是否原创" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.oriFlag === 1" size="small" type="success">是</el-tag>
          <el-tag v-else-if="scope.row.oriFlag === 0" size="small" type="danger">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="排序" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="status" label="发布状态" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" size="small" type="success">已发布</el-tag>
          <el-tag v-else-if="scope.row.status === 0" size="small" type="danger">未发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="publishTime" label="发布时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="remark" label="说明" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('blog:article:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('blog:article:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button>
          <el-button v-if="$hasPerm('blog:article:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
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
import edit from './article-edit'
import info from './article-info'
import grid from '@/mixins/grid'
import filePreview from '@/components/filePreview'
export default {
  mixins: [grid],
  data () {
    return {
      module: '/manage/blog/article/',
      gridOptions: {
        isQuery: true
      },
      defOrders: [
        {k: 'createTime', t: 'desc'}
      ],
      dataMode: {
        title: 'LK'
      },
      dataForm: {}
    }
  },
  components: {
    edit, info, filePreview
  },
  methods: {
  }
}
</script>
