<template>
  <div class="dowebok" id="dowebok">
    <div class="form-container sign-in-container">
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules"
               status-icon label-width="0px">
        <h1 style="margin-bottom: 10px;">注册</h1>
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
        <el-form-item prop="passwordRepeat">
          <el-input v-model="ruleForm.passwordRepeat" type="password" label-width="auto" style="width: 200px;"
                    placeholder="重复密码" autocomplete="off">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="goon" @click="submitForm('ruleForm')">注册</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-panel overlay-right">
          <h1>已有帐号？</h1>
          <p>请使用您的帐号进行登录</p>
          <button @click="this.$router.push('/login')" class="ghost" id="signIn">登录</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { register } from '../api/login.ts'

export default defineComponent({
  name: 'Register',
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
      } else if (value.length < 6 || value.length > 20) {
        callback(new Error('密码长度必须6-20位'))
      } else {
        callback()
      }
    }
    const validatePasswordRepeat = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        username: '',
        password: '',
        passwordRepeat: ''
      },
      rules: {
        username: [{ validator: validateUsername, trigger: 'blur' }],
        password: [{ validator: validatePassword, trigger: 'blur' }],
        passwordRepeat: [{ validator: validatePasswordRepeat, trigger: 'blur' }]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          register(this.ruleForm).then(response => {
            if (response.status === 200) {
              this.$message.success('注册成功')
              localStorage.setItem('tokenValue', response.data.tokenValue)
              localStorage.setItem('username', response.data.loginId)
              // this.$store.commit('setInfo', {
              //   username: response.data.loginId,
              //   tokenValue: response.data.tokenValue
              // })
              this.$router.push('/login')
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
