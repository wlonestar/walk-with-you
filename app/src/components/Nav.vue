<template>
  <div>
    <header class="header">
      <img src="../assets/img/首页.jpg" alt="">
    </header>
    <div class="menu">
      <div class="menu-content">
        <div class="menu-left" style="margin-left: 100px;">
          <ul>
            <li><a href="/">首页</a></li>
            <li><a href="/event">红色记忆</a></li>
            <li><a href="/people">革命英雄</a></li>
            <li><a href="/video">经典影视</a></li>
            <li><a href="/music">红色歌曲</a></li>
            <li><a href="/comment">留言</a></li>
          </ul>
        </div>
        <div></div>
        <div class="menu-left" style="margin-right: 350px;">
          <ul style="float: right;">
            <li><a @click="logout" v-if="show===true">注销</a></li>
            <li><a href="/user" v-if="show===true">个人中心</a></li>
            <li><a href="/login" v-if="show===false">登录</a></li>
            <li><a href="/register" v-if="show===false">注册</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { check, logout } from '@/api/login.ts'

export default defineComponent({
  name: 'Nav',
  data () {
    return {
      show: false
    }
  },
  created () {
    this.isLogin()
  },
  methods: {
    isLogin () {
      const user = localStorage.getItem('username')
      const token = localStorage.getItem('tokenValue')
      check(user, token).then(response => {
        if (response.status === 200) {
          console.log(response)
          this.show = true
        } else {
          console.log(response)
          this.show = false
        }
      })
    },
    logout () {
      const token = localStorage.getItem('tokenValue')
      logout(token).then(response => {
        console.log(response)
        if (response.status === 200) {
          this.$message.success('success')
          this.$router.push('/login')
          localStorage.removeItem('username')
          localStorage.removeItem('tokenName')
          localStorage.removeItem('tokenValue')
        } else {
          this.$message.error('failed')
        }
      })
    }
  }
})
</script>

<style scoped>

</style>
