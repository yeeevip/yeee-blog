<template>
  <div class="mod-news">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="listData()">
      <el-form-item>
        <el-input v-model="dataForm.name" placeholder="名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.author" placeholder="作者" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button size="small" @click="listData()">查询</el-button>
        <el-button v-if="$hasPerm('sys:news:add')" size="small" type="primary" @click="editHandle()">新增</el-button>
        <el-button v-if="$hasPerm('sys:news:del')" size="small" type="danger" @click="delHandle()" :disabled="dataListSelections.length <= 0">删除</el-button>
        <!-- <el-button size="small" @click="exptHandle()">导出</el-button>
        <el-button size="small" @click="imptHandle()">导入</el-button> -->
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border stripe v-loading="dataListLoading" :max-height="tableHeight"
            @selection-change="selectionChangeHandle" @sort-change="sortChangeHandle" style="width: 100%;">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="name" label="名称" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="author" label="作者" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="readQty" label="阅读数量" sortable="custom" header-align="center" align="center"></el-table-column>
      <el-table-column prop="recordTime" label="记录时间" sortable="custom" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="releaseTime" label="发布时间" sortable="custom" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="imgUrl" label="图片" header-align="center" align="center" width="200">
        <template slot-scope="scope">
          <imgPreivew :files="scope.row.imgUrl"></imgPreivew>
          <!-- <img :src="scope.row.imgUrl" width="50px" @click="showImage(scope.row.imgUrl)"/> -->
        </template>
      </el-table-column>
      <!-- <el-table-column prop="statNm" label="是否发布" sortable="custom" header-align="center" align="center"></el-table-column> -->
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="100">
        <template slot-scope="scope">
          <el-button v-if="$hasPerm('sys:news:info')" type="text" size="small" @click="infoHandle(scope.row.id)" icon="el-icon-document" title="详情"></el-button>
          <el-button v-if="$hasPerm('sys:news:upd')" type="text" size="small" @click="editHandle(scope.row.id)" icon="el-icon-edit" title="编辑"></el-button>
          <el-button v-if="$hasPerm('sys:news:del')" type="text" size="small" @click="delHandle(scope.row.id)" icon="el-icon-delete" title="删除"></el-button>
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
  import edit from './news-edit'
  import info from './news-info'
  import impt from './news-impt'
  import grid from '@/mixins/grid'
  import Viewer from 'viewerjs'
  import 'viewerjs/dist/viewer.min.css'
  import imgPreivew from '@/components/imgPreview'
  export default {
    mixins: [grid],
    data () {
      return {
        module: '/manage/sys-news/',
        gridOptions: {},
        defOrders: [
          {k: 'createTime', t: 'desc'}
        ],
        dataForm: {}
      }
    },
    components: {
      edit, info, impt, imgPreivew
    },
    methods: {
      // 图片预览
      showImage (url) {
        let divNode = document.createElement('div')
        divNode.style.display = 'none'
        let imageNode = document.createElement('img')
        imageNode.setAttribute('src', url)
        imageNode.setAttribute('alt', '图片')
        divNode.appendChild(imageNode)
        document.body.appendChild(divNode)
        let viewer = new Viewer(divNode, {
          zIndex: 2099,
          hidden () {
            viewer.destroy()
            divNode.remove()
          }
        })
        imageNode.click()
      }
    }
  }
</script>
