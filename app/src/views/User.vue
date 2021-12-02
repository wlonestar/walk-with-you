<template>
  <div>
    <Nav></Nav>
    <div class="user" v-if="show===false">
      <div style="padding: 50px 50px 30px 50px;">
        <a @click="this.$router.push('/login')">请先登录</a>
      </div>
    </div>
    <div class="user" v-if="show===true">
      <div style="padding: 50px 50px 30px 50px;">
        <n-thing content-indented>
          <template #avatar v-if="avatar">
            <n-avatar>
              <div>
                <div class="UserAvatar">
                  <img :src='user.avatar' alt="" style="width: 160px;">
                </div>
                <div style="text-align: center; margin-top: 16px;">
                  <n-button color="#bb4b4b" @click="this.$router.push('/user/avatar')">修改头像</n-button>
                </div>
              </div>
            </n-avatar>
          </template>
          <template #header v-if="header">
            <div style="font-size: 28px;"> {{ user.username }} </div>
          </template>
          <template #header-extra v-if="headerExtra">
            <n-button color="#bb4b4b" size="medium" @click="this.$router.push('/user/edit')">编辑个人资料</n-button>
          </template>
          <template #description v-if="description">
            <n-row style="font-size: 18px; margin-top: 20px;">
              <n-col :span="2"><div class="message">个人描述</div></n-col>
              <n-col :span="12"><div> {{ user.description }} </div></n-col>
            </n-row>
          </template>
          <template #default style="margin-top: 20px;">
            <div style="font-size: 18px;">
              <n-row>
                <n-col :span="2"><div class="message">email</div></n-col>
                <n-col :span="12"><div> {{ user.email }} </div></n-col>
              </n-row>
              <n-row>
                <n-col :span="2"><div class="message">居住地</div></n-col>
                <n-col :span="12"><div> {{ user.location }} </div></n-col>
              </n-row>
              <n-row>
                <n-col :span="2"><div class="message">教育经历</div></n-col>
                <n-col :span="12"><div> {{ user.education }} </div></n-col>
              </n-row>
              <n-row>
                <n-col :span="2"><div class="message">个人简介</div></n-col>
                <n-col :span="12"><p> {{ user.profile }} </p></n-col>
              </n-row>
            </div>
          </template>
        </n-thing>
      </div>
    </div>
    <div class="user" v-if="show===true" style="padding-top: 30px;">
      <div style="margin-left: 5%; font-weight: 500; font-size: 20px;">最近发言</div>
      <RecentComments></RecentComments>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import Nav from '../components/Nav'
import Footer from '../components/Footer'
import RecentComments from '../components/RecentComments'
import { NThing, NButton, NRow, NCol } from 'naive-ui'
import { getUserByUsername } from '../api/user.ts'
import { check } from '../api/login'

export default defineComponent({
  name: 'User',
  components: {
    RecentComments,
    Footer,
    Nav,
    NThing,
    NButton,
    NRow,
    NCol
  },
  created () {
    this.load()
  },
  data () {
    return {
      show: false,
      user: {
        username: '',
        avatar: '',
        email: '',
        description: '',
        location: '',
        education: '',
        profile: ''
      },
      avatar: ref(true),
      header: ref(true),
      headerExtra: ref(true),
      description: ref(true),
      location: ref(true),
      action: ref(true)
    }
  },
  methods: {
    load () {
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
      const username = localStorage.getItem('username')
      getUserByUsername(username).then(response => {
        this.user = response.data
      })
    }
  }
})
</script>

<style scoped>
a:hover {
  cursor: pointer;
}

.UserAvatar {
  display: inline-block;
  overflow: hidden;
  vertical-align: top;
  background-color: #fff;
}
.user {
  position: relative;
  width: 1200px;
  height: 100%;
  margin: 15px auto 15px auto;
  padding-bottom: 30px;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
  box-shadow: 5px 5px 9px 3px #bfbfbf;
}
.message {
  font-size: 15px;
  font-weight: 500;
}
</style>
