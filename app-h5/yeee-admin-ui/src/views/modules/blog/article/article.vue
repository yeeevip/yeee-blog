<template>
  <div class="mod-article">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-select v-model="dataForm.classifyId" placeholder="分类" clearable>
          <el-option
            v-for="item in classifyList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
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
      <el-table-column prop="title" label="标题" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-button type="text" @click="handleToEditTxtPage(scope.row)">{{ scope.row.title }}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="titleImg" label="标题图" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope" >
          <el-popover
            placement="right"
            width="400"
            trigger="click">
            <img  :src="scope.row.titleImg" width="400px" height="400px" >
            <img  slot="reference" :src="scope.row.titleImg" width="50px" height="50px" >
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="classifyName" label="分类" header-align="center" align="center"></el-table-column>
      <el-table-column prop="labelList" label="标签" header-align="center" align="center">
        <template slot-scope="scope" >
          <el-tag
            :key="label"
            v-for="label in scope.row.labelList"
            :disable-transitions="false">
            {{label}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="topicList" label="专题" header-align="center" align="center">
        <template slot-scope="scope" >
          <el-tag
            :key="topic"
            v-for="topic in scope.row.topicList"
            :disable-transitions="false">
            {{topic}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="oriFlag" label="是否原创" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.oriFlag === 1" size="small" type="success">是</el-tag>
          <el-tag v-else-if="scope.row.oriFlag === 0" size="small" type="danger">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="type" label="类型" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type === 0" size="small" type="success">原文</el-tag>
          <el-tag v-else-if="scope.row.type === 1" size="small" type="danger">外链</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="readNum" label="阅读量" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="likeNum" label="点赞量" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="sort" label="排序" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="status" label="发布状态" sortable="custom" header-align="center" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" size="small" type="success">已发布</el-tag>
          <el-tag v-else-if="scope.row.status === 0" size="small" type="danger">未发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="publishTime" label="发布时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="updateTime" label="修改时间" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('blog:article:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('blog:article:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="修改"></el-button>
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
import query from '@/utils/query'
import { mainRoutes } from '@/router'
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
      dataForm: {},
      classifyList: []
    }
  },
  mounted () {
    this.getClassifyList()
  },
  components: {
    edit, info, filePreview
  },
  methods: {
    getClassifyList () {
      let qry = query.new()
      query.toP(qry, 1, 100)
      this.$http.get('/manage/blog/classify/page' + '?query=' + encodeURIComponent(query.toJsonStr(qry)))
        .then(({ data: res }) => {
          if (res.code === 200) {
            this.classifyList = res.data.result
          }
        })
    },
    handleToEditTxtPage (row) {
      // 组装路由名称, 并判断是否已添加, 如是: 则直接跳转
      var routeName = `${this.$route.name}EditTxt__${row.id}`
      var route = window.SITE_CONFIG['dynamicRoutes'].filter(item => item.name === routeName)[0]
      var params = { 'id': row.id }
      if (route) {
        return this.$router.push({ name: routeName, params: params })
      }
      // 否则: 添加并全局变量保存, 再跳转
      route = {
        path: routeName,
        component: () => import(`@/views/modules/${this.$route.name.replace(/-/g, '/')}EditTxt`),
        name: routeName,
        meta: {
          ...window.SITE_CONFIG['contentTabDefault'],
          menuId: this.$route.meta.menuId,
          title: `编辑 - ${row.title}`
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
      this.$router.push({ name: route.name, params: params })
    }
  }
}
</script>
