<template>
  <div class="mod-wxMenuItm">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <!-- <el-form-item>
        <el-input v-model="dataForm.id" placeholder="主键" clearable></el-input>
      </el-form-item> -->
      <el-form-item>
        <!-- <el-button size="small" @click="listData()">查询</el-button> -->
        <el-button v-if="$hasPerm('sys:wxMenuItm:add')" size="small" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="$hasPerm('sys:wxMenuItm:del')" size="small" type="danger" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
        <el-button size="small" @click="menuCreateHandle()">创建菜单</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight" 
            @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <table-tree-column prop="nm" 
        treeKey="id"
        parentKey="pid"
        levelKey="level"
        childKey="children"
        label="标题" header-align="center" align="left"></table-tree-column>
      <el-table-column prop="type" label="响应动作类型" header-align="center" align="center">
        <template slot-scope="scope">
          <div v-if="scope.row.type === 'click'">点击推事件</div>
          <div v-else-if="scope.row.type === 'view'">跳转URL</div>
          <div v-else-if="scope.row.type === 'miniprogram'">跳转到小程序</div>
          <div v-else-if="scope.row.type === 'scancode_push'">扫码推事件</div>
          <div v-else-if="scope.row.type === 'scancode_waitmsg'">扫码推事件且弹出“消息接收中”提示框</div>
          <div v-else-if="scope.row.type === 'pic_sysphoto'">弹出系统拍照发图</div>
          <div v-else-if="scope.row.type === 'pic_photo_or_album'">弹出拍照或者相册发图</div>
          <div v-else-if="scope.row.type === 'pic_weixin'">弹出微信相册发图器</div>
          <div v-else-if="scope.row.type === 'location_select'">弹出地理位置选择器</div>
          <div v-else-if="scope.row.type === 'media_id'">下发消息（除文本消息）</div>
          <div v-else-if="scope.row.type === 'view_limited'">跳转图文消息URL</div>
          <div v-else>{{ scope.row.type }}</div>
        </template>
      </el-table-column>
      <!-- <el-table-column prop="key" label="菜单KEY值" header-align="center" align="center"></el-table-column> -->
      <el-table-column prop="hasOauth" label="是否oauth" header-align="center" align="center">
        <template slot-scope="scope">
          <div v-if="scope.row.hasOauth === 0">否</div>
          <div v-else>是</div>
        </template>
      </el-table-column>
      <el-table-column prop="seq" label="排序" header-align="center" align="center"></el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('sys:wxMenuItm:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('sys:wxMenuItm:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button>
          <el-button v-if="$hasPerm('sys:wxMenuItm:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
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
  import edit from './wxMenuItm-edit'
  import info from './wxMenuItm-info'
  import grid from '@/mixins/grid'
  import tableTreeColumn from '@/components/table-tree-column'
  export default {
    mixins: [grid],
    data () {
      return {
        module: '/sys/wxMenuItm/',
        gridOptions: {
          isAct: false,
          isQuery: false,
          isPage: false,
          listUrl: '/sys/wxMenuItm/listTreeNode'
        },
        dataForm: {},
        menuId: null,
        params: {
          menuId: null
        }
      }
    },
    activated () {
      // menuId, 控制列表请求操作
      if (!this.menuId) {
        this.menuId = this.$route.params.menuId || null
        this.params.menuId = this.menuId
      }
      this.listData()
    },
    components: {
      edit, info, tableTreeColumn
    },
    methods: {
      // 新增 / 修改
      editHandle (id) {
        this.editVisible = true
        this.$nextTick(() => {
          this.$refs.edit.dataForm.menuId = this.menuId
          this.$refs.edit.init(id)
        })
      },
      menuCreateHandle () {
        let config = {
          url: '/wxMp/menu/create',
          message: '创建菜单',
          params: {
            wxMenuId: this.menuId
          }
        }
        this.baseHandle(config)
      }
    }
  }
</script>
