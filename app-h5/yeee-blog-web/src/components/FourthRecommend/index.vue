<template>
    <div class="tuijian" v-if="fourthData.length > 0">
      <h2 class="hometitle">推荐文章</h2>
      <ul class="tjpic" v-if="fourthData[0]">
        <i><img v-if="fourthData[0].titleImg" style="cursor:pointer"  @click="goToInfo(fourthData[0])" :src="fourthData[0].titleImg"></i>
        <p><a href="javascript:void(0);" @click="goToInfo(fourthData[0])">{{fourthData[0].title}}</a></p>
      </ul>

      <ul class="sidenews">
        <li v-for="item in sideNews" :key="item.id">
          <i v-if="item.titleImg"><img style="cursor:pointer" @click="goToInfo(fourthData[0])" :src="item.titleImg"></i>
          <p><a href="javascript:void(0);" @click="goToInfo(item)">{{item.title}}</a></p>
          <span>{{item.publishTime}}</span>
        </li>
      </ul>
    </div>
</template>

<script>
import { getBlogByLevel } from "../../api/index";
import {getBlogByUid} from "../../api/blogContent";
import {recordArticleStatsData} from "../../api/stats";
export default {
  name: 'FourthRecommend',
    data() {
    	return {
	      fourthData: [], //；四级推荐数据
    	}
    },
    created() {
      var fourthParams = {
        "pageNum": 1,
        "pageSize": 5
      };
      getBlogByLevel(JSON.stringify(fourthParams)).then(response => {
        if(response.code == this.$ECode.SUCCESS) {
          this.fourthData = response.data.result;
        }
      });
    },
    computed: {
      //添加一个计算属性用于简单过滤掉数组中第一个数据
      sideNews() {
        return this.fourthData.filter(blog =>
          this.fourthData.indexOf(blog) != 0
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
          var params = {
            id: blog.id
          }
          window.open(blog.linkUrl, '_blank');
        }
      },
    },


}
</script>

<style>
</style>
