<template>
  <div>
    <Nav></nav>
    <div>
      <div class="comment">
        <div style="margin-left: 5%; width: 90%;">
          <div style="padding-top: 50px; font-size: 20px; font-family: SansSerif, serif;">我想对党说：</div>
          <el-form ref="ruleForm" :model="ruleForm" :rules="rules" style="padding-top: 30px;"
                   status-icon label-width="0px" class="demo-ruleForm">
            <el-form-item label="" prop="content">
              <el-input v-model.number="ruleForm.content" type="textarea" rows="7"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="goon" :disabled="show===false" @click="submitForm('ruleForm')">留言</el-button>
              <el-button :disabled="show===false" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
          <CommentList></CommentList>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { addComment } from '../api/comment.ts'
import CommentList from '../components/CommentList'
import Nav from '../components/Nav'
import Footer from '../components/Footer'
import { check } from '../api/login'

export default defineComponent({
  name: 'Comment',
  components: {
    Footer,
    Nav,
    CommentList
  },
  data () {
    const checkComment = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('留言不能为空哦'))
      }
      setTimeout(() => {
        if (String(value).length < 2) {
          callback(new Error('多写一点啦'))
        } else {
          if (value.length < 2) {
            callback(new Error('多写一点啦'))
          } else if (value.length > 500) {
            callback(new Error('太多啦'))
          } else {
            callback()
          }
        }
      }, 10)
    }
    return {
      show: false,
      ruleForm: {
        content: ''
      },
      rules: {
        content: [{ validator: checkComment, trigger: 'blur' }]
      }
    }
  },
  created () {
    this.load()
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
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const comment = {
            content: this.ruleForm.content,
            username: localStorage.getItem('username')
          }
          addComment(comment).then(response => {
            if (response.status === 200) {
              alert('留言成功')
              window.location.reload()
            } else {
              console.log(response.msg)
              alert('留言失败')
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
})
</script>

<style scoped>
.el-button--goon.is-active,
.el-button--goon:active {
  background: #bb4b4b;
  border-color: #bb4b4b;
  color: #fff;
}
.el-button--goon.is-disabled,
.el-button--goon:disabled {
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
.comment {
  position: relative;
  width: 1200px;
  height: 100%;
  margin: 15px auto 15px auto;
  padding-bottom: 30px;
  background-repeat: no-repeat;
  background-size: cover; /*477.6px 100% 505px*/
  border-radius: 10px;
  box-shadow: 5px 5px 9px 3px #bfbfbf;
}
</style>
