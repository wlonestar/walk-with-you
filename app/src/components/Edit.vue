<template>
  <div>
    <Nav></Nav>
    <div class="user">
      <div style="padding: 50px 50px 50px 50px;">
        <el-form ref="form" :model="form" label-width="80px" style="margin-left: 5%; width: 90%;">
          <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="个人描述">
            <el-input v-model="form.description" type="textarea"></el-input>
          </el-form-item>
          <el-form-item label="居住地">
            <el-input v-model="form.location"></el-input>
          </el-form-item>
          <el-form-item label="教育经历">
            <el-input v-model="form.education"></el-input>
          </el-form-item>
          <el-form-item label="个人简介">
            <el-input v-model="form.profile" type="textarea"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="goon" @click="onSubmit">保存修改</el-button>
            <el-button @click="this.$router.push('/user')">取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import Nav from './Nav'
import Footer from './Footer'
import { getUserByUsername, updateUserByUsername } from '../api/user.ts'

export default defineComponent({
  name: 'Edit',
  components: {
    Footer,
    Nav
  },
  created () {
    this.load()
  },
  data () {
    return {
      form: {
        username: '',
        avatar: '',
        email: '',
        description: '',
        location: '',
        education: '',
        profile: ''
      },
      user: {
        username: '',
        avatar: '',
        email: '',
        description: '',
        location: '',
        education: '',
        profile: ''
      }
    }
  },
  methods: {
    load () {
      const username = localStorage.getItem('username')
      getUserByUsername(username).then(response => {
        this.user = response.data
        this.form = response.data
      })
    },
    onSubmit () {
      const username = localStorage.getItem('username')
      const user = this.form
      updateUserByUsername(username, user).then(response => {
        console.log(response.msg)
        this.$message.success('修改成功')
        this.$router.push('/user')
      })
    }
  }
})
</script>

<style scoped>
.user {
  position: relative;
  width: 1200px;
  height: 100%;
  margin: 15px auto 15px auto;
  padding-bottom: 20px;
  padding-top: 20px;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
  box-shadow: 5px 5px 9px 3px #bfbfbf;
}
.el-button--goon.is-active,
.el-button--goon:active {
  background: #bb4b4b;
  border-color: #bb4b4b;
  color: #fff;
}
.el-button--goon:focus,
.el-button--goon:hover {
  background: #bb4b4b;
  border-color: #bb4b4b;
  color: #fff;
}
.el-button--goon {
  color: #fff;
  background-color: #bb4b4b;
  border-color: #bb4b4b;
}
</style>
