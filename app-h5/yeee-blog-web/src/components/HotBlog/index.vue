<template>
    <div class="tuijian" v-if="hotBlogData.length > 0">
      <h2 class="hometitle">人气排行</h2>
      <ul class="tjpic" v-if="hotBlogData[0]">
        <i v-if="hotBlogData[0].titleImg"><img style="cursor:pointer" :src="hotBlogData[0].titleImg" @click="goToInfo(hotBlogData[0])"></i>
        <p><a href="javascript:void(0);" @click="goToInfo(hotBlogData[0])">{{hotBlogData[0].title}}</a></p>
      </ul>
      <ul class="sidenews">
        <li v-for="(item, index) in sideNews" v-if="index != 0" :key="item.id">
          <i v-if="item.titleImg"><img style="cursor:pointer" :src="item.titleImg" @click="goToInfo(item)"></i>
          <p><a href="javascript:void(0);" @click="goToInfo(item)">{{item.title}}</a></p>
          <span>{{item.publishTime}}</span>
        </li>
      </ul>
    </div>
</template>

<script>
import { getHotBlog } from "../../api/index";
import {getBlogByUid} from "../../api/blogContent";
import {recordArticleStatsData} from "../../api/stats";
export default {
  name: "TagCloud",
  data() {
    return {
      hotBlogData: [] //热门博客列表
    };
  },
  created() {
    let params = {
      pageNum: 1,
      pageSize: 8
    }
    getHotBlog(JSON.stringify(params)).then(response => {
      if (response.code == this.$ECode.SUCCESS) {
        this.hotBlogData = response.data.result;
      }
    });
  },
  computed: {
    //添加一个计算属性用于简单过滤掉数组中第一个数据
    sideNews() {
      return this.hotBlogData.filter(blog =>
        this.hotBlogData.indexOf(blog) != 0
      )
    }
  },
  methods: {
    //跳转到文章详情【或推广链接】
    goToInfo(blog) {
      recordArticleStatsData('click', blog.id).then(response => {
        // 记录一下用户点击日志
      });
      if(blog.type == "0") {
        // let routeData = this.$router.resolve({
        //   path: "/info/" + blog.id
        // });
        // window.open(routeData.href, '_blank');
        this.$router.push({
          name: "info",
          params: {'blogId': blog.id}
        });
      } else if(blog.type == "1") {
        window.open(blog.linkUrl, '_blank');
      }
    },
  }
};
</script>

<style>

</style>
