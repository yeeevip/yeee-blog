<template>
  <div class="cloud" v-if="hotTagData.length > 0">
    <h2 class="hometitle">热门标签</h2>
    <ul>
      <a v-for="item in hotTagData" :key="item.id" href="javascript:void(0);" @click="goToList(item.id)">{{item.name}}</a>
    </ul>
  </div>
</template>

<script>
import { getHotTag } from "../../api/index";
export default {
  name: "TagCloud",
  data() {
    return {
      hotTagData: [] //标签列表
    };
  },
  created() {
    let params = {
      "pageNum": 1,
      "pageSize": 15
    };
    getHotTag(JSON.stringify(params)).then(response => {
      if (response.code == this.$ECode.SUCCESS) {
        this.hotTagData = response.data.result;
      }
    });
  },
  methods: {
    //跳转到搜索详情页
    goToList(uid) {
      let routeData = this.$router.resolve({ path: "/list", query: { tagUid: uid } });
      window.open(routeData.href, '_blank');
    },
  }
};
</script>

<style>
</style>
