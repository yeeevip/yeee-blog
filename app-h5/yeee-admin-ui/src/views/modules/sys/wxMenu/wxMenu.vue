<template>
  <div class="mod-wxMenu">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-input v-model="dataForm.nm" placeholder="名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" @click="listData()">查询</el-button>
        <el-button v-if="$hasPerm('sys:wxMenu:add')" size="small" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="$hasPerm('sys:wxMenu:del')" size="small" type="danger" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
        <!-- <el-button size="small" @click="exptHandle()">导出</el-button>
        <el-button size="small" @click="imptHandle()">导入</el-button> -->
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight" 
            @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="nm" label="名称" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button type="text" @click="itmHandle(scope.row)">{{ scope.row.nm }}</el-button>
          <!-- <label>{{ scope.row.nm }}</label> -->
        </template>
      </el-table-column>
      <el-table-column prop="hasMatchrule" label="匹配规则" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.hasMatchrule === 0" size="small" type="info">不匹配</el-tag>
          <el-tag v-else size="small" type="warning">匹配</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="tagId" label="用户标签的ID" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="seq" label="排序" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('sys:wxMenu:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('sys:wxMenu:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button>
          <el-button v-if="$hasPerm('sys:wxMenu:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
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
  </div>
</template>

<script>
  import edit from './wxMenu-edit'
  import info from './wxMenu-info'
  import impt from './wxMenu-impt'
  import grid from '@/mixins/grid'
  import { mainRoutes } from '@/router'
  export default {
    mixins: [grid],
    data () {
      return {
        module: '/sys/wxMenu/',
        gridOptions: {},
        defOrders: [
          {k: 'seq', t: 'asc'}
        ],
        dataForm: {}
      }
    },
    components: {
      edit, info, impt
    },
    methods: {
      itmHandle (row) {
        // 组装路由名称, 并判断是否已添加, 如是: 则直接跳转
        var routeName = `${this.$route.name}Itm__${row.id}_${row.nm}`
        var route = window.SITE_CONFIG['dynamicRoutes'].filter(item => item.name === routeName)[0]
        if (route) {
          return this.$router.push({ name: routeName, params: { 'menuId': row.id } })
        }
        // 否则: 添加并全局变量保存, 再跳转
        route = {
          path: routeName,
          component: () => import(`@/views/modules/${this.$route.name.replace(/-/g, '/')}Itm`),
          name: routeName,
          meta: {
            ...window.SITE_CONFIG['contentTabDefault'],
            menuId: this.$route.meta.menuId,
            title: `${this.$route.meta.title} - ${row.nm}`
          }
        }
        this.$router.addRoutes([
          {
            ...mainRoutes,
            name: `main-dynamic__${route.name}`,
            children: [route]
          }
        ])
        window.SITE_CONFIG['dynamicRoutes'].push(route)
        this.$router.push({ name: route.name, params: { 'menuId': row.id } })
      }
    }
  }
</script>
