<template>
  <div>
    <section class="article-content" style="margin-left: 5%; width: 90%; margin-top: 50px;">
      <n-thing v-for="comment in comments" :key="comment.id" style="margin-bottom: 20px; border-radius: 10px;">
        <el-row :gutter="20">
          <el-col span="4">
            <n-avatar round :size="48" :src="comment.avatar"/>
          </el-col>
          <el-col span="4">
            <div style="margin-top: 12px;">{{ comment.username }}</div>
          </el-col>
        </el-row>
        <p style="margin-top: 20px;">{{ comment.content }}</p>
        <p style="margin-top: 20px; margin-bottom: 20px;">{{ new Date(comment.createTime).toLocaleString() }}</p>
        <n-divider />
      </n-thing>
    </section>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { NAvatar, NDivider, NThing } from 'naive-ui'
import { getCommentsByName } from '../api/comment.ts'

export default defineComponent({
  name: 'RecentComments',
  components: {
    NThing, NAvatar, NDivider
  },
  data () {
    return {
      comments: [
        {
          id: 0,
          authorId: 0,
          username: '',
          content: '',
          avatar: '',
          createTime: ''
        }
      ]
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      const name = localStorage.getItem('username')
      getCommentsByName(name).then(response => {
        console.log(response)
        this.comments = response.data
      })
    }
  }
})
</script>

<style scoped>

</style>
