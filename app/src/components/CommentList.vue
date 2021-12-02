<template>
  <div>
    <section class="article-content main">
      <n-thing v-for="comment in comments" :key="comment.id" class="thing">
        <el-row :gutter="20">
          <el-col span="4">
            <n-avatar round :size="48" :src="comment.avatar"/>
          </el-col>
          <el-col span="4">
            <div style="margin-top: 12px;">{{ comment.username }}</div>
          </el-col>
        </el-row>
        <p style="margin-top: 20px;">{{ comment.content }}</p>
        <p style="margin-top: 20px; margin-bottom: 20px;">
          {{ new Date(comment.createTime).toLocaleString() }}
        </p>
        <n-divider />
      </n-thing>
      <div class="block">
        <el-pagination
          v-model:currentPage="currentPage" layout="total, prev, pager, next"
          :page-size="pageSize" :total="total"
          @size-change="handleSizeChange" @current-change="handleCurrentChange">
        </el-pagination>
      </div>
    </section>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { NAvatar, NDivider, NThing } from 'naive-ui'
import { getCommentByPage } from '../api/comment.ts'

export default defineComponent({
  name: 'CommentList',
  components: {
    NThing, NAvatar, NDivider
  },
  data () {
    return {
      currentPage: 1,
      pageNum: 1,
      pageSize: 5,
      total: 0,
      totalPages: 0,
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
      const page = this.pageNum
      const size = this.pageSize
      getCommentByPage(page, size).then(response => {
        this.comments = response.data.content
        this.total = response.data.totalElements
      })
    },
    handleSizeChange (pageSize) {
      this.pageSize = pageSize
      console.log(this.pageSize)
      this.load()
    },
    handleCurrentChange (pageNum) {
      this.pageNum = pageNum
      console.log(this.pageNum)
      this.load()
    }
  }
})
</script>

<style scoped>
.main {
  margin-left: 5%;
  width: 90%;
  margin-top: 50px;
}
.thing {
  margin-bottom: 20px;
  border-radius: 10px;
}
</style>
