<template>
  <div class="homepage-hero-module">
    <div class="filter"></div>
    <video
      autoplay
      loop
      class="fillWidth"
      src="../assets/video2_1.mp4"
      v-on:canplay="canplay"
      muted="muted"
      width="100%"
    ></video>
    <el-row class="box-card">
      <el-col :span="18" :offset="3">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="账号登录" name="first">
            <el-form
              :model="dataForm"
              :rules="dataRule"
              ref="dataForm"
              @keyup.enter.native="login()"
              status-icon
            >
              <el-form-item prop="userName">
                <el-input
                  v-model="dataForm.userName"
                  placeholder="帐号"
                ></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input
                  v-model="dataForm.password"
                  type="password"
                  placeholder="密码"
                ></el-input>
              </el-form-item>
              <router-link to="/user/forget" class="link">忘记密码</router-link>
              <router-link to="/user/register" class="link"
                >注册账号</router-link
              >
              <el-form-item>
                <el-button
                  class="login-btn-submit"
                  type="primary"
                  @click="login()"
                  >登录</el-button
                >
              </el-form-item>
            </el-form>
          </el-tab-pane>

          <el-tab-pane label="邮箱登录" name="second">
            <el-form
              :model="emailDataForm"
              :rules="dataRule"
              ref="emailDataForm"
              @keyup.enter.native="login()"
              status-icon
            >
              <el-form-item prop="email">
                <el-input
                  v-model="emailDataForm.email"
                  type="email"
                  placeholder="邮箱"
                ></el-input>
              </el-form-item>

              <el-form-item prop="emailCode" :inline="true">
                <el-input
                  v-model="emailDataForm.emailCode"
                  placeholder="验证码"
                  style="width: 230px"
                ></el-input>
                <el-button
                  :disabled="disabled"
                  @click="sendCode"
                  class="sendcode"
                  style="width: 125px"
                  >{{ btnTxt }}
                </el-button>
              </el-form-item>
              <router-link to="/user/forget" class="link">忘记密码</router-link>
              <router-link to="/user/register" class="link"
                >注册账号</router-link
              >
              <el-form-item>
                <el-button
                  class="login-btn-submit"
                  type="primary"
                  @click="login()"
                  >登录</el-button
                >
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
    <b class="slogn" style="color: white; font-size: 24px"
      >生活就像海洋，只有意志坚强的人才能到达彼岸——鲁迅</b
    >
  </div>
</template>
<script>
import { alertSuccess, alertError } from '../utils/message'
export default {
  inject: ['reload'],
  data() {
    return {
      vedioCanPlay: false,
      dataForm: {
        userName: '',
        password: '',
      },
      emailDataForm: {
        email: '',
        emailCode: '',
      },
      dataRule: {
        userName: [
          {
            required: true,
            message: '帐号不能为空',
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: '密码不能为空',
            trigger: 'blur',
          },
        ],
        email: [
          {
            required: true,
            message: '邮箱不能为空',
            trigger: 'blur',
          },
        ],
        emailCode: [
          {
            required: true,
            message: '验证码不能为空',
            trigger: 'blur',
          },
        ],
      },
      activeName: 'first',
      disabled: false,
      time: 30,
      btnTxt: '发送验证码',
      type: '0',
    }
  },
  name: 'login',
  methods: {
    canplay() {
      this.vedioCanPlay = true
    },
    login() {
      if (this.type === '0') {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$API
              .p_Login({
                username: this.dataForm.userName,
                password: this.dataForm.password,
                loginType: 'password',
              })
              .then((data) => {
                if (data.state === '0') {
                  alertError('违规用户无法登录')
                } else {
                  let redirect
                  alertSuccess('登录成功')
                  this.$store.commit('login', data)
                  this.reload()
                  if (data.state === '1') {
                    redirect = decodeURIComponent(
                      this.$route.query.redirect || '/MainPage'
                    )
                  } else {
                    redirect = decodeURIComponent(
                      this.$route.query.redirect || '/Manage'
                    )
                  }
                  this.$router.push({
                    path: redirect,
                  })
                }
              })
              .catch((err) => {})
          } else {
            return false
          }
        })
      } else {
        this.$refs['emailDataForm'].validate((valid) => {
          if (valid) {
            this.$API
              .p_Login({
                mail: this.emailDataForm.email,
                verifyCode: this.emailDataForm.emailCode,
                loginType: 'mail',
              })
              .then((data) => {
                if (data.state === '0') {
                  alertError('违规用户无法登录')
                } else {
                  let redirect
                  alertSuccess('登录成功')
                  this.$store.commit('login', data)
                  this.reload()
                  if (data.state === '1') {
                    redirect = decodeURIComponent(
                      this.$route.query.redirect || '/MainPage'
                    )
                  } else {
                    redirect = decodeURIComponent(
                      this.$route.query.redirect || '/Manage'
                    )
                  }
                  this.$router.push({
                    path: redirect,
                  })
                }
              })
              .catch(() => {})
          } else {
            return false
          }
        })
      }
    },
    //切换不同登录方式
    handleClick(tab, event) {
      this.type = tab.index
    },
    //发送邮箱验证码，30秒后重新发送
    sendCode() {
      this.$refs.emailDataForm.validateField('email', (error) => {
        if (!error) {
          this.time = 30
          this.timer()
          this.$API
            .p_SendCode({
              mail: this.emailDataForm.email,
            })
            .then((res) => {
              alertSuccess('发送成功')
            })
        }
      })
    },
    //发送手机验证码倒计时
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
  width: 100%;
  top: 0;
  left: 0;
}
.filter {
  z-index: 1;
  position: absolute;
  /* background: rgba(0, 0, 0, 0.4); */
  height: 100%;
  width: 100%;
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
  /*border: 2px solid rgba(114, 130, 208, 0.5);*/
  border-radius: 0.2em;
  /*padding-bottom: 35px;*/
  /*padding-top: 40px;*/
}

.title {
  z-index: 5;
  position: absolute;
  top: 12%;
  left: 26.5%;
  width: 700px;
}
.demo-ruleForm {
  font-size: large;
}
.slogn {
  z-index: 5;
  position: absolute;
  top: 86%;
  left: 30.5%;
}
.login-content {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  height: 300px;
  width: 400px;
  background-color: #112234;
  opacity: 0.8;
}
.login-main {
  color: beige;
  padding: 20px 20px 10px 20px;
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

.login-btn-submit {
  margin-top: 10px;
}
.fillWidth {
  object-fit: fill;
}
.link {
  color: black;
}
</style>
