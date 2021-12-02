<template>
  <div>
    <Nav></Nav>
    <div class="user">
      <div style="padding: 50px 50px 30px 50px;">
        <n-thing>
          <el-row :gutter="20" style="margin-top: 50px;">
            <el-col :span="5" style="margin-left: 30%">
              <el-upload
                class="avatar-uploader"
                action="http://localhost:8088/file/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar" alt="">
                <el-icon v-else class="avatar-uploader-icon"><plus /></el-icon>
              </el-upload>
            </el-col>
            <el-col :span="5">
              <div>
                <img :src='user.avatar' alt="" class="avatar" />
              </div>
            </el-col>
          </el-row>
          <el-row style="margin-left: 45%; margin-top: 50px;">
            <el-button :disabled="imageUrl === ''" @click="updateAvatar">更新头像</el-button>
          </el-row>
        </n-thing>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import Nav from './Nav'
import Footer from './Footer'
import { NThing } from 'naive-ui'
import { Plus } from '@element-plus/icons'
import { getUserByUsername, updateUserByUsername } from '../api/user.ts'

export default defineComponent({
  name: 'Avatar',
  components: {
    Nav,
    Footer,
    NThing,
    Plus
  },
  data () {
    return {
      imageUrl: '',
      user: {
        id: 0,
        username: '',
        avatar: ''
      }
    }
  },
  created () {
    this.load()
  },
  methods: {
    handleAvatarSuccess (res, file) {
      if (res.status === 200) {
        console.log(res)
        this.imageUrl = res.msg
        this.$message.success('success')
      } else {
        this.$message.error('fail')
      }
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        this.$message.error('Avatar picture must be JPG format!')
      }
      if (!isLt2M) {
        this.$message.error('Avatar picture size can not exceed 2MB!')
      }
      return isJPG && isLt2M
    },
    updateAvatar () {
      const user = this.user
      this.user.avatar = this.imageUrl
      updateUserByUsername(localStorage.getItem('username'), user).then(response => {
        console.log(response.data)
        this.$message.success('更新成功')
        this.$router.push('/user')
      })
    },
    load () {
      const username = localStorage.getItem('username')
      getUserByUsername(username).then(response => {
        if (response.status === 200) {
          this.user = response.data
        } else {
          this.$message.error('please login first')
        }
      })
    }
  }
})
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #000;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.avatar-uploader-icon svg {
  margin-top: 74px; /* (178px - 28px) / 2 - 1px */
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
input[type="file" i] {
  appearance: none;
  background-color: initial;
  cursor: default;
  align-items: baseline;
  color: inherit;
  text-overflow: ellipsis;
  white-space: pre;
  text-align: start !important;
  padding: initial;
  border-color: initial;
  overflow: hidden !important;
  border-radius: initial;
}

.name {
  font-size: 30px;
}
.user {
  position: relative;
  width: 1200px;
  height: 100%;
  margin: 15px auto;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
  box-shadow: 5px 5px 9px 3px #bfbfbf;
}
</style>
