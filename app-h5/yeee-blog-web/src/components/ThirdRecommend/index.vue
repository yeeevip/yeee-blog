<template>
  <div class="zhuanti" v-if="thirdData.length > 0">
    <h2 class="hometitle">特别推荐</h2>
    <ul>
      <li  v-for="item in thirdData" :key="item.id" style="cursor: pointer" @click="goToInfo(item)"> <i><img v-if="item.titleImg" :src="item.titleImg"></i>
        <p @click="goToInfo(item)" style="cursor: pointer">{{splitStr(item.title, 30)}}<span><a href="javascript:void(0);">阅读</a></span> </p>
      </li>
    </ul>
  </div>
</template>

<script>
import { getBlogByLevel } from "../../api/index";
import {getBlogByUid} from "../../api/blogContent";
export default {
  name: 'ThirdRecommend',
    data() {
    	return {
        slideList: [],
	      thirdData: [], //；一级推荐数据
    	}
    },
    created() {
      var thirdParams = {
        "pageNum": 1,
        "pageSize": 1,
        "labelCode": 'tbtj'
      };
      getBlogByLevel(JSON.stringify(thirdParams)).then(response => {
        if (response.code == this.$ECode.SUCCESS) {
          this.thirdData = response.data.result;
        }
      });
    },
    methods: {
      //跳转到文章详情【或推广链接】
      goToInfo(blog) {
        if(blog.type == "0") {
          let routeData = this.$router.resolve({
            path: "/info",
            query: {blogId: blog.id}
          });
          window.open(routeData.href, '_blank');
        } else if(blog.type == "1") {
          var params = {
            id: blog.id
          }
          getBlogByUid(JSON.stringify(params)).then(response => {
            // 记录一下用户点击日志
          });
          window.open(blog.linkUrl, '_blank');
        }
      },
      splitStr(str, flagCount) {
        return this.$commonUtil.splitStr(str, flagCount)
      }
    },

}
</script>

<style>
</style>
