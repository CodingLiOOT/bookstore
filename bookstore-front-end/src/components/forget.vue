<template>
  <div class="homepage-hero-module">
    <div  class="filter"></div>
    <video  autoplay loop class="fillWidth" src="../assets/video2_1.mp4" v-on:canplay="canplay" muted="muted" width="100%">
    </video>
    <el-row class="box-card">
      <el-col :span="18" :offset="3">
        <div class="forgetPassword-content">
          <div class="forgetPassword-main">
            <router-link to="/user/login" style="float: right; color: black">返回登录</router-link>
            <div class="forgetPassword-main-title" align="left">忘记密码</div>
            <el-form
                :model="ForgetForm"
                :rules="ForgetRule"
                ref="ForgetForm"
                @keyup.enter.native="forgetPassword()"
                status-icon>
              <el-form-item prop="email">
                <el-input v-model="ForgetForm.email" placeholder="邮箱"></el-input>
              </el-form-item>
              <el-form-item prop="newPassword">
                <el-input
                    v-model="ForgetForm.newPassword"
                    type="password"
                    placeholder="新密码"
                ></el-input>
              </el-form-item>
              <el-form-item prop="confirmNewPassword">
                <el-input
                    v-model="ForgetForm.confirmNewPassword"
                    type="password"
                    placeholder="确认新密码"
                ></el-input>
              </el-form-item>
              <el-form-item prop="emailCode" :inline="true">
                <el-input
                    v-model="ForgetForm.emailCode"
                    placeholder="验证码"
                    style="width: 230px"
                ></el-input>
                <el-button
                    :disabled="disabled"
                    @click="sendCode"
                    class="sendcode"
                    style="width: 125px"
                >{{ btnTxt }}</el-button>
              </el-form-item>
              <el-form-item>
                <el-button
                    class="forgetPassword-btn-submit"
                    type="primary"
                    @click="forgetPassword()"
                >提交</el-button
                >
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { alertSuccess } from '../utils/message'
export default {
  data() {
    /**
     * 验证用户名
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'))
      } else {
        const regPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/
        if (!regPass.test(value)) {
          callback(
              new Error('至少八位字符，包含大小写字母和数字，不含特殊字符')
          )
        }
        callback()
      }
    }

    /**
     * 验证密码是否为空
     * @param rule [规则名字]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      }
      const regPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/

      if (this.ForgetForm.confirmNewPassword !== '') {
        this.$refs.ForgetForm.validateField('confirmNewPassword')
      }
      if (!regPass.test(value)) {
        callback(new Error('至少八位字符，包含大小写字母和数字，不含特殊字符'))
      }
      callback()
    }

    /**
     * 二次验证密码
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validConfirmPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ForgetForm.newPassword) {
        callback(new Error('两次输入密码不一致！'))
      } else {
        callback()
      }
    }

    /**
     * 验证邮箱
     * @param rule [规则名称]
     * @param value [输入框值]
     * @param callback [回调函数]
     */
    let validEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'))
      } else {
        const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
        if (regEmail.test(value)) {
          callback()
        }
        callback(new Error('请输入合法邮箱'))
      }
    }

    return {
      vedioCanPlay:false,
      ForgetForm: {
        userName: '',
        newPassword: '',
        confirmNewPassword: '',
        email: '',
        emailCode: '',
      },
      ForgetRule: {
        userName: [
          {
            validator: validateUsername,
            trigger: 'change',
          },
          {
            required: true,
            message: '请输入账号',
            trigger: 'blur',
          },
        ],
        newPassword: [
          {
            validator: validatePass,
            trigger: 'change',
          },
          {
            validator: validatePass,
            trigger: 'blur',
          },
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },
        ],
        confirmNewPassword: [
          {
            validator: validConfirmPass,
            trigger: 'change',
          },
          {
            validator: validConfirmPass,
            trigger: 'blur',
          },
          {
            required: true,
            message: '请再次输入密码',
            trigger: 'blur',
          },
        ],
        email: [
          {
            validator: validEmail,
            trigger: 'change',
          },
          {
            validator: validEmail,
            trigger: 'blur',
          },
          {
            required: true,
            message: '请输入邮箱',
            trigger: 'blur',
          },
        ],
        emailCode: [
          {
            required: true,
            message: '请输入验证码',
            trigger: 'blur',
          },
        ],
      },
      disabled: false,
      time: 30,
      btnTxt: '发送验证码',
    }
  },
  name: 'Forget',

  methods: {
    canplay() {
      this.vedioCanPlay = true
    },
    forgetPassword() {
      this.$refs.ForgetForm.validate((valid) => {
        if (valid) {
          this.$API
              .p_Forget({
                newPassword: this.ForgetForm.newPassword,
                mail: this.ForgetForm.email,
                verifyCode: this.ForgetForm.emailCode,
              })
              .then((res) => {
                alertSuccess('修改成功')
                this.$router.replace('/login')
              })
              .catch({})
        } else {
          return false
        }
      })
    },

    //发送邮箱验证码，30秒后重新发送
    sendCode() {
      this.$refs.ForgetForm.validateField('email', (valid) => {
        if (!valid) {
          this.time = 30
          this.timer()
          this.$API
              .p_SendCode({
                mail: this.ForgetForm.email,
              })
              .then((data) => {
                alertSuccess()
              })
        }
      })
    },
    //发送邮箱验证码倒计时
    timer() {
      if (this.time > 0) {
        this.disabled = true
        this.time--
        this.btnTxt = this.time + 's后重新发送'
        setTimeout(this.timer, 1000)
      } else {
        this.time = 0
        this.btnTxt = '发送验证码'
        this.disabled = false
      }
    },
  },
}
</script>

<style scoped>
.homepage-hero-module,
.video-container .poster img,
.video-container video {
  z-index: 0;
  position: absolute;
  height: 100%;
  width : 100%;
  top: 0;
  left: 0;
}
.filter {
  z-index: 1;
  position: absolute;
//background: rgba(0, 0, 0, 0.4);
  height: 100%;
  width : 100%;
}

.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  z-index: 10;
  width: 480px;
  position: absolute;
  top: 30%;
  left: 35%;
  font-size: 50px;
  background-color: rgba(236, 189, 157, 0.5);
  border-radius: 0.2em;
}
h3 {
  color: #0babeab8;
  font-size: 24px;
}

hr {
  background-color: #444;
  margin: 20px auto;
}

a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}

a:hover {
  color: coral;
}

h3 {
  color: #0babeab8;
  font-size: 24px;
}

hr {
  background-color: #444;
  margin: 20px auto;
}

a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}

a:hover {
  color: coral;
}
.forgetPassword-main-title{
  margin-top: 10%;
  font-size: 30px;
  color: black;
}
</style>
