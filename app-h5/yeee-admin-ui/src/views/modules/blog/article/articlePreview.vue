<template>
  <article>
    <div class="infosbox">
      <div class="newsview">
        <h3 class="news_title" v-if="dataForm.title">{{dataForm.title}}</h3>
        <div class="news_about">
          <strong>版权</strong>
          <span v-html="dataForm.copyright">
            {{dataForm.copyright}}
          </span>
        </div>
        <div
          class="news_con ck-content"
          v-html="dataForm.content"
          v-highlight
        >{{dataForm.content}}</div>
      </div>
    </div>
  </article>
</template>

<script>
export default {
  data () {
    return {
      dataForm: {}
    }
  },
  activated () {
    this.init(this.$route.params.id)
  },
  methods: {
    init (id) {
      this.getInfo(id)
    },
    // 获取信息
    getInfo (id) {
      this.$http.json().post('/manage/blog/article/info', {
        'id': id
      }).then(({data: res}) => {
        if (res.code !== 200) {
          return this.$message.error(res.msg)
        }
        this.dataForm = {
          ...this.dataForm,
          ...res.data
        }
      }).catch(() => {})
    }
  }
}
</script>

<style>
  .newsview {
    background-image: linear-gradient(90deg,rgba(50,0,0,.05) 3%,transparent 0),linear-gradient(1turn,rgba(50,0,0,.05) 3%,transparent 0);
    background-size: 20px 20px;
    background-position: 50%;
  }
</style>
