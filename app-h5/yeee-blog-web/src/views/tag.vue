<template>
  <div>
    <div class="pagebg tagPage"></div>
    <div class="container">
<!--      <h1 class="t_nav">-->
<!--        <span>内心没有阳光的人温暖不了别人。用心写博客，知识不是高谈阔论，是现实点点滴滴的积累。</span>-->
<!--        <a href="/" class="n1">网站首页</a>-->
<!--        <a href="javascript:void(0);" class="n2">标签</a>-->
<!--      </h1>-->

      <div class="sortBox">
        <div class="time">
          <div class="block">
            <div class="radio" style="margin-bottom:20px;"></div>
            <el-timeline :reverse="reverse">
              <el-timeline-item v-for="(activity, index) in activities" hide-timestamp :key="index">
                <span
                  @click="getBlogTagList(activity.id)"
                  :class="[activity.id == selectBlogUid ? 'sortBoxSpan sortBoxSpanSelect' : 'sortBoxSpan']"
                >{{activity.name}}</span>
              </el-timeline-item>
            </el-timeline>
          </div>
        </div>

        <div class="article">
          <div class="block" v-infinite-scroll="load">
            <el-timeline>
              <el-timeline-item
                v-for="item in itemByDate"
                :key="item.id"
                :timestamp="item.publishTime"
                placement="top"
              >
                <el-card>
                  <h4 @click="goToList('blogContent', item)" class="itemTitle">{{item.title}}</h4>
                  <br>
                  <el-tag class="elTag" v-if="item.oriFlag==1" type="danger">原创</el-tag>
                  <el-tag class="elTag" v-if="item.oriFlag==0" type="info">转载</el-tag>

                  <el-tag
                    class="elTag"
                    v-if="item.author"
                    @click="goToList('author', item)"
                  >{{item.author}}</el-tag>

                  <el-tag
                    class="elTag"
                    type="success"
                    v-if="item.classify != null"
                    @click="goToList('blogSort', item)"
                  >{{item.classify.name}}</el-tag>
                  <el-tag
                    class="elTag"
                    v-for="tagItem in item.labelList"
                    v-if="tagItem != null"
                    :key="tagItem.id"
                    style="margin-left: 3px;"
                    @click="goToList('tag', tagItem)"
                    type="warning"
                  >{{tagItem.name}}</el-tag>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { getTagList, getArticleByTagUid } from "../api/tag";
  export default {
    data() {
      return {
        selectBlogUid: "",
        reverse: false,
        activities: [],
        itemByDate: [],
        articleByDate: {},
        count: 0,
        currentPage: 1,
        pageSize: 10
      };
    },
    components: {
      //注册组件
    },
    mounted() { },
    created() {
      var that = this;
      let params = {
        pageNum: 1,
        pageSize: 15
      }
      getTagList(JSON.stringify(params)).then(response => {
        if (response.code == this.$ECode.SUCCESS) {
          var activities = response.data.result;
          var result = [];
          for (var a = 0; a < activities.length; a++) {
            var dataForDate = {
              name: activities[a].name,
              id: activities[a].id
            };
            result.push(dataForDate);
          }
          this.activities = result;

          // 默认选择第一个
          this.getBlogTagList(activities[0].id);
        }
      });

    },
    methods: {
      getBlogTagList(tagUid) {
        this.selectBlogUid = tagUid;
        var params = {
          pageNum: 1,
          pageSize: 5,
          labelId: this.selectBlogUid
        }
        getArticleByTagUid(JSON.stringify(params)).then(response => {
          if (response.code == this.$ECode.SUCCESS) {
            this.itemByDate = response.data.result;
            this.currentPage = response.data.pageNum;
            this.pageSize = response.data.pageSize;
          }
        });
      },
      load() {
        if (
          this.selectBlogUid == null ||
          this.selectBlogUid == "" ||
          this.selectBlogUid == undefined
        ) {
          return;
        }
        var params = {
          pageNum: this.currentPage + 1,
          pageSize: 5,
          labelId: this.selectBlogUid
        }
        getArticleByTagUid(JSON.stringify(params)).then(response => {
          if (response.code == this.$ECode.SUCCESS) {
            this.itemByDate = this.itemByDate.concat(response.data.result);
            this.currentPage = response.data.pageNum;
            this.pageSize = response.data.pageSize;
          }
        });
      },
      //跳转到搜索详情页
      goToList(type, entity) {
        switch (type) {
          case "tag":
          {
            // 标签uid
            let routeData = this.$router.resolve({
              path: "/list",
              query: { tagUid: entity.uid }
            });
            window.open(routeData.href, "_blank");
          }
            break;
          case "blogSort":
          {
            let routeData = this.$router.resolve({
              path: "/list",
              query: { sortUid: entity.blogSort.uid }
            });
            window.open(routeData.href, "_blank");
          }
            break;
          case "author":
          {
            let routeData = this.$router.resolve({
              path: "/list",
              query: { author: entity.author }
            });
            window.open(routeData.href, "_blank");
          }
            break;

          case "blogContent":
          {
            if(entity.type == "0") {
              let routeData = this.$router.resolve({
                path: "/info",
                query: { blogOid: entity.oid }
              });
              window.open(routeData.href, "_blank");
            } else if(entity.type == "1") {
              window.open(entity.outsideLink, '_blank');
            }
          }
            break;
        }
      }
    }
  };
</script>

<style>
  .sortBox {
    color: #555;
  }

  .sortBoxSpan {
    cursor: pointer;
  }
  .sortBoxSpan:hover {
    color: #409eff;
  }
  .sortBoxSpanSelect {
    color: #409eff;
  }

  .itemTitle {
    cursor: pointer;
  }
  .itemTitle:hover {
    color: #409eff;
  }
  .elTag {
    cursor: pointer;
  }
</style>
