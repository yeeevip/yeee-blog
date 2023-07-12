<template>
  <div>
    <div class="pagebg sh"></div>
    <div class="container">
<!--      <h1 class="t_nav">-->
<!--        <span>好咖啡要和朋友一起品尝，好“资料”也要和同样喜欢它的人一起分享。</span>-->
<!--        <a href="/" class="n1">网站首页</a>-->
<!--        <a href="/" class="n2">专题</a>-->
<!--      </h1>-->

      <div class="share">
        <ul>
          <li v-for="item in dataList" :key="item.id">
            <div class="shareli" @click="showSubjectItemList(item.id)">
              <a href="javascript:void(0);">
                <i>
                  <img v-if="item.coverImg" class="resImg" :src="item.coverImg" lazy>
                  <el-image class="resImg" v-else>
                    <div slot="error" class="image-slot">
                      <i class="el-icon-picture-outline"></i>
                    </div>
                  </el-image>
                </i>
                <h2>
                  <b>{{item.remark}}</b>
                </h2>
                <span>{{item.name}}</span>
              </a>
            </div>
          </li>
        </ul>
      </div>

      <!--分页-->
      <div class="block" style="text-align: center;">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="pageSize"
          layout="total, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </div>

    <SubjectItemList :visiable="visiable" @close="closeSubjectItemList" :subjectUid="selectSubjectUid"></SubjectItemList>

  </div>
</template>

<script>
import {getTopicList, getTopicItemList} from "../api/topic";
import SubjectItemList from "../components/SubjectItemList";
export default {
  name: "share",
  data() {
    return {
      dataList: [],
      currentPage: 1,
      pageSize: 15,
      total: 0, //总数量
      visiable: false, //是否显示专辑item侧边栏
      selectSubjectUid: "", // 选中的SubjectUid
    };
  },
  components: {
    //注册组件
    SubjectItemList
  },
  created() {
    this.subjectList()
  },
  methods: {
    subjectList() {
      var params = {
        pageNum: this.currentPage,
        pageSize: this.pageSize
      };
      getTopicList(JSON.stringify(params)).then(response => {
        console.log("得到的结果", response)
        if (response.code == this.$ECode.SUCCESS) {
          this.dataList = response.data.result;
          this.total = response.data.total;
          this.pageSize = response.data.pageSize;
          this.currentPage = response.data.pageNum;
        }
      });
    },
    //改变页码
    handleCurrentChange(val) {
      this.currentPage = val; //改变当前所指向的页数
      this.subjectList();
    },
    showSubjectItemList(selectSubjectUid) {
      this.selectSubjectUid = selectSubjectUid
      this.visiable = !this.visiable
    },
    closeSubjectItemList(selectSubjectUid) {
      this.visiable = !this.visiable
    }
  }
};
</script>

<style scoped>
.share {
  min-height: 550px;
}
.resImg {
  width: 100%;
  height: 160px;
  vertical-align: middle;
}
.el-drawer {
  min-width: 400px;
}
</style>
