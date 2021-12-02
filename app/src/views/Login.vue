<template>
  <div>
    <div class="dowebok" id="dowebok">
      <div class="form-container sign-in-container">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules"
                 status-icon label-width="0px">
          <h1 style="margin-bottom: 10px;">登录</h1>
          <el-form-item prop="username">
            <el-input v-model="ruleForm.username" type="text" label-width="auto" style="width: 200px;"
                      placeholder="用户名" autocomplete="off">
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="ruleForm.password" type="password" label-width="auto" style="width: 200px;"
                      placeholder="密码" autocomplete="off">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="goon" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-right">
            <h1>没有帐号？</h1>
            <p>立即注册加入我们，和我们一起开始旅程吧</p>
            <button @click="this.$router.push('/register')" class="ghost" id="signUp">注册</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { login } from '../api/login.ts'

export default defineComponent({
  name: 'Login',
  data () {
    const validateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'))
      } else {
        if (this.ruleForm.username !== '') {
          this.$refs.ruleForm.validateField('username')
        }
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{ validator: validateUsername, trigger: 'blur' }],
        password: [{ validator: validatePassword, trigger: 'blur' }]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          login(this.ruleForm).then(response => {
            if (response.status === 200) {
              console.log(response.data)
              this.$message.success('登录成功')
              localStorage.setItem('username', response.data.loginId)
              localStorage.setItem('tokenName', response.data.tokenName)
              localStorage.setItem('tokenValue', response.data.tokenValue)
              this.$store.commit('setInfo', {
                username: response.data.loginId,
                tokenName: response.data.tokenName,
                tokenValue: response.data.tokenValue
              })
              this.$router.push('/user')
            } else {
              this.$message.error(response.msg)
            }
          })
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
@import "../assets/css/register.css";
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
